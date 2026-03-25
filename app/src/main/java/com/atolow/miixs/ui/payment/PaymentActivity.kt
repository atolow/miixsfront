package com.atolow.miixs.ui.payment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.billingclient.api.*
import com.atolow.miixs.R
import com.atolow.miixs.data.local.TokenManager
import com.atolow.miixs.data.model.payment.CreatePaymentRequestDto
import com.atolow.miixs.data.model.payment.ProductResponseDto
import com.atolow.miixs.data.model.payment.VerifyGooglePlayPaymentRequestDto
import com.atolow.miixs.data.repository.PaymentRepository
import com.atolow.miixs.data.repository.UserRepository
import com.atolow.miixs.databinding.ActivityPaymentBinding
import com.atolow.miixs.ui.auth.LoginActivity
import com.atolow.miixs.ui.payment.adapter.ProductAdapter
import com.atolow.miixs.ui.payment.viewmodel.PaymentViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class PaymentActivity : AppCompatActivity(), PurchasesUpdatedListener {
    private lateinit var binding: ActivityPaymentBinding
    private val viewModel: PaymentViewModel by viewModels()
    private lateinit var productAdapter: ProductAdapter
    private val paymentRepository = PaymentRepository()
    private val userRepository = UserRepository()
    private var selectedProduct: ProductResponseDto? = null
    
    // Google Play Billing
    lateinit var billingClient: BillingClient  // ProductDetailsResponseListenerImpl에서 접근 가능하도록 public으로 변경
    var isBillingClientReady = false  // BillingClientStateListenerImpl에서 접근 가능하도록 public으로 변경

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 토큰이 없으면 로그인 화면으로 이동
        if (!TokenManager.isLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        setupObservers()
        setupClickListeners()
        loadCurrentCash()
        viewModel.loadProducts()
        // BillingClient는 다른 작업이 완료된 후 초기화
        setupBillingClient()
        
        // 딥링크로 들어온 경우 처리
        handleDeepLink(intent)
    }
    
    private fun setupClickListeners() {
        binding.tvPaymentHistory.setOnClickListener {
            val intent = Intent(this, PaymentHistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter { product ->
            selectedProduct = product
            startGooglePlayPayment(product)
        }

        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.adapter = productAdapter
    }
    
    private fun setupBillingClient() {
        try {
            val pendingPurchasesParams = PendingPurchasesParams.newBuilder()
                .enableOneTimeProducts()
                .build()
            
            billingClient = BillingClient.newBuilder(this)
                .setListener(this)
                .enablePendingPurchases(pendingPurchasesParams)
                .build()
            
            // 명시적인 BillingClientStateListener를 별도 클래스로 분리 (ProGuard 대응)
            val stateListener = BillingClientStateListenerImpl(this)
            
            billingClient.startConnection(stateListener)
            
            // 콜백이 실행되지 않는 경우를 대비해 일정 시간 후 상태 확인
            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                if (!isBillingClientReady) {
                    // 재연결 시도
                    if (::billingClient.isInitialized) {
                        billingClient.endConnection()
                    }
                    setupBillingClient()
                }
            }, 5000) // 5초 후 확인
            
        } catch (e: Exception) {
            android.util.Log.e("PaymentActivity", "BillingClient 설정 중 오류", e)
        }
    }
    
    

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.products.collect { products ->
                productAdapter.submitList(products)
            }
        }

        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading) android.view.View.VISIBLE else android.view.View.GONE
            }
        }

        lifecycleScope.launch {
            viewModel.error.collect { error ->
                error?.let {
                    Toast.makeText(this@PaymentActivity, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loadCurrentCash() {
        lifecycleScope.launch {
            userRepository.getProfile().fold(
                onSuccess = { profile ->
                    val cashAmount = profile.miixsCash ?: 0L
                    binding.tvCurrentCash.text = "${formatNumber(cashAmount)} 믹시즈"
                },
                onFailure = {
                    // 프로필 로드 실패 시 기본값 표시
                    binding.tvCurrentCash.text = "0 믹시즈"
                }
            )
        }
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleDeepLink(intent)
    }

    override fun onResume() {
        super.onResume()
        // 결제 완료 후 돌아왔을 때 현재 보유 포인트 새로고침
        loadCurrentCash()
        
        // Google Play 미확인 구매 확인
        if (isBillingClientReady) {
            queryPurchases()
        } else {
            // BillingClient가 준비되지 않았으면 재연결 시도
            setupBillingClient()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        if (::billingClient.isInitialized) {
            billingClient.endConnection()
        }
    }
    
    // Google Play Billing 구현
    fun startGooglePlayPayment(product: ProductResponseDto) {
        if (!isBillingClientReady) {
            Toast.makeText(this, "결제 서비스를 준비 중입니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_LONG).show()
            // BillingClient 재연결 시도
            setupBillingClient()
            return
        }
        
        // 구매 전에 이미 소유하고 있는지 확인
        checkAndConsumeExistingPurchase(product.productId) {
            // 소비 완료 후 또는 소유하지 않은 경우 구매 진행
            launchPurchaseFlow(product)
        }
    }
    
    /**
     * 이미 소유하고 있는 구매가 있는지 확인하고 소비 처리
     * 일회성 제품을 여러 번 구매할 수 있도록 하기 위함
     */
    fun checkAndConsumeExistingPurchase(productId: String, onComplete: () -> Unit) {
        val params = QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.INAPP)
            .build()
        
        billingClient.queryPurchasesAsync(params) { billingResult, purchases ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
                // 해당 제품을 이미 소유하고 있는지 확인
                val existingPurchase = purchases.find { 
                    it.products.contains(productId) && 
                    it.purchaseState == Purchase.PurchaseState.PURCHASED 
                }
                
                if (existingPurchase != null) {
                    // 이미 소유하고 있으면 먼저 소비 처리
                    android.util.Log.d("PaymentActivity", "이미 소유한 제품 발견: $productId, 소비 처리 중...")
                    Toast.makeText(this@PaymentActivity, "기존 구매를 처리하는 중...", Toast.LENGTH_SHORT).show()
                    consumePurchaseIfNeeded(existingPurchase) {
                        // 소비 완료 후 Google Play 서버에 반영될 시간을 주기 위해 약간의 지연
                        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                            android.util.Log.d("PaymentActivity", "소비 완료. 구매 진행합니다.")
                            onComplete()
                        }, 1500) // 1.5초 대기
                    }
                } else {
                    // 소유하지 않은 경우 바로 구매 진행
                    onComplete()
                }
            } else {
                // 쿼리 실패 시에도 구매 진행 시도
                android.util.Log.w("PaymentActivity", "구매 내역 조회 실패, 구매 진행: ${billingResult.debugMessage}")
                onComplete()
            }
        }
    }
    
    /**
     * 실제 구매 플로우 시작
     */
    fun launchPurchaseFlow(product: ProductResponseDto) {
        val productList = listOf(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(product.productId)
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        )
        
        val params = QueryProductDetailsParams.newBuilder()
            .setProductList(productList)
            .build()
        
        // 명시적인 ProductDetailsResponseListener를 별도 파일로 분리 (ProGuard 대응)
        val listener = ProductDetailsResponseListenerImpl(this, product)
        
        try {
            billingClient.queryProductDetailsAsync(params, listener)
        } catch (e: Exception) {
            android.util.Log.e("PaymentActivity", "queryProductDetailsAsync 호출 중 오류", e)
            Toast.makeText(this, "결제를 시작할 수 없습니다.", Toast.LENGTH_LONG).show()
        }
    }
    
    // PurchasesUpdatedListener 구현
    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: MutableList<Purchase>?) {
        val responseCode = billingResult.responseCode
        val debugMessage = billingResult.debugMessage
        
        if (responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                handlePurchase(purchase)
            }
        } else if (responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
            Toast.makeText(this, "결제가 취소되었습니다", Toast.LENGTH_SHORT).show()
        } else if (responseCode == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
            // 이미 소유하고 있는 아이템인 경우, 기존 구매를 소비하고 다시 시도
            android.util.Log.w("PaymentActivity", "이미 소유한 아이템입니다. 기존 구매를 소비 처리합니다.")
            Toast.makeText(this, "기존 구매를 처리한 후 다시 시도합니다...", Toast.LENGTH_SHORT).show()
            
            // 기존 구매 내역 조회 및 소비 처리
            val params = QueryPurchasesParams.newBuilder()
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
            
            billingClient.queryPurchasesAsync(params) { queryResult, existingPurchases ->
                if (queryResult.responseCode == BillingClient.BillingResponseCode.OK && existingPurchases != null) {
                    for (purchase in existingPurchases) {
                        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                            consumePurchaseIfNeeded(purchase)
                        }
                    }
                    Toast.makeText(this, "처리 완료. 다시 구매를 시도해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            val errorMessage = when (responseCode) {
                BillingClient.BillingResponseCode.BILLING_UNAVAILABLE -> "Billing 서비스를 사용할 수 없습니다."
                BillingClient.BillingResponseCode.SERVICE_UNAVAILABLE -> "Billing 서비스를 사용할 수 없습니다."
                BillingClient.BillingResponseCode.DEVELOPER_ERROR -> "개발자 오류가 발생했습니다."
                BillingClient.BillingResponseCode.ERROR -> "결제 처리 중 오류가 발생했습니다."
                else -> "결제에 실패했습니다. (오류 코드: $responseCode)"
            }
            android.util.Log.e("PaymentActivity", "$errorMessage (debugMessage: $debugMessage)")
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }
    
    private fun handlePurchase(purchase: Purchase) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
            // 서버에 검증 요청 (먼저 검증 후 소비 처리)
            verifyPurchaseWithServer(purchase) { success ->
                if (success) {
                    // 서버 검증 성공 후 구매 확인 및 소비 처리
                    if (!purchase.isAcknowledged) {
                        acknowledgePurchase(purchase) {
                            // acknowledge 완료 후 소비 처리
                            consumePurchaseIfNeeded(purchase)
                        }
                    } else {
                        // 이미 acknowledge된 경우 바로 소비 처리
                        consumePurchaseIfNeeded(purchase)
                    }
                }
            }
        }
    }
    
    private fun acknowledgePurchase(purchase: Purchase, onComplete: (() -> Unit)? = null) {
        val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()
        
        billingClient.acknowledgePurchase(acknowledgePurchaseParams) { billingResult ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                onComplete?.invoke()
            } else {
                android.util.Log.e("PaymentActivity", "구매 확인 실패: ${billingResult.responseCode}")
            }
        }
    }
    
    private fun verifyPurchaseWithServer(purchase: Purchase, onComplete: ((Boolean) -> Unit)? = null) {
        lifecycleScope.launch {
            try {
                val productId = purchase.products.firstOrNull()
                if (productId == null) {
                    android.util.Log.e("PaymentActivity", "구매에 상품 ID가 없습니다")
                    onComplete?.invoke(false)
                    return@launch
                }
                
                // orderId는 Purchase 객체에서 가져오거나, 없으면 purchaseToken 사용
                // 백엔드에서 실제 Google Play API로 orderId를 조회하므로 임시로 purchaseToken 사용
                val orderId = purchase.orderId ?: purchase.purchaseToken
                
                val request = VerifyGooglePlayPaymentRequestDto(
                    purchaseToken = purchase.purchaseToken,
                    productId = productId,
                    orderId = orderId
                )
                
                paymentRepository.verifyGooglePlayPayment(request).fold(
                    onSuccess = { paymentResponse ->
                        Toast.makeText(
                            this@PaymentActivity,
                            "결제가 완료되었습니다. ${formatNumber(paymentResponse.cashAmount)} 믹시즈가 충전되었습니다.",
                            Toast.LENGTH_LONG
                        ).show()
                        
                        // 현재 보유 포인트 새로고침
                        loadCurrentCash()
                        onComplete?.invoke(true)
                    },
                    onFailure = { exception ->
                        android.util.Log.e("PaymentActivity", "서버 검증 실패", exception)
                        android.util.Log.e("PaymentActivity", "에러 메시지: ${exception.message}")
                        android.util.Log.e("PaymentActivity", "에러 스택: ${exception.stackTraceToString()}")
                        Toast.makeText(
                            this@PaymentActivity,
                            exception.message ?: "결제 검증에 실패했습니다",
                            Toast.LENGTH_LONG
                        ).show()
                        onComplete?.invoke(false)
                    }
                )
            } catch (e: Exception) {
                android.util.Log.e("PaymentActivity", "결제 검증 중 오류 발생", e)
                android.util.Log.e("PaymentActivity", "에러 스택: ${e.stackTraceToString()}")
                Toast.makeText(
                    this@PaymentActivity,
                    "결제 검증 중 오류가 발생했습니다: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
                onComplete?.invoke(false)
            }
        }
    }
    
    /**
     * 구매를 소비 처리 (재구매 가능하도록)
     * 캐시 충전 상품은 여러 번 구매할 수 있어야 하므로 소비 처리가 필요합니다.
     */
    fun consumePurchaseIfNeeded(purchase: Purchase, onComplete: (() -> Unit)? = null) {
        android.util.Log.d("PaymentActivity", "소비 처리 시작: productId=${purchase.products.firstOrNull()}, purchaseToken=${purchase.purchaseToken}")
        
        val consumeParams = ConsumeParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()
        
        billingClient.consumeAsync(consumeParams) { billingResult, purchaseToken ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                android.util.Log.d("PaymentActivity", "구매 소비 성공: purchaseToken=$purchaseToken")
                onComplete?.invoke()
            } else {
                // 소비 실패해도 이미 서버 검증은 완료되었으므로 사용자에게는 에러를 표시하지 않음
                android.util.Log.e("PaymentActivity", "구매 소비 실패: responseCode=${billingResult.responseCode}, debugMessage=${billingResult.debugMessage}")
                // 실패해도 콜백 호출 (구매 진행)
                onComplete?.invoke()
            }
        }
    }
    
    fun queryPurchases() {
        val params = QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.INAPP)
            .build()
        
        billingClient.queryPurchasesAsync(params) { billingResult, purchases ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
                for (purchase in purchases) {
                    // PURCHASED 상태인 모든 구매를 처리
                    if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                        // 이미 서버 검증이 완료된 구매는 바로 소비 처리
                        // 서버 검증이 안 된 구매는 handlePurchase에서 처리
                        if (purchase.isAcknowledged) {
                            // 이미 acknowledge된 구매는 바로 소비 처리 (재구매 가능하도록)
                            consumePurchaseIfNeeded(purchase)
                        } else {
                            // 아직 acknowledge되지 않은 구매는 서버 검증 후 처리
                            handlePurchase(purchase)
                        }
                    }
                }
            }
        }
    }

    private fun handleDeepLink(intent: Intent?) {
        intent?.data?.let { uri ->
            when (uri.host) {
                "payment" -> {
                    when (uri.path) {
                        "/success" -> {
                            val cashAmount = uri.getQueryParameter("cashAmount")?.toLongOrNull() ?: 0L
                            Toast.makeText(
                                this,
                                "결제가 완료되었습니다. ${formatNumber(cashAmount)} 믹시즈가 충전되었습니다.",
                                Toast.LENGTH_LONG
                            ).show()
                            loadCurrentCash()
                        }
                        "/fail" -> {
                            val message = uri.getQueryParameter("message") ?: "결제에 실패했습니다"
                            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        }
                        "/cancel" -> {
                            Toast.makeText(this, "결제가 취소되었습니다", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }


    private fun formatNumber(number: Long): String {
        return NumberFormat.getNumberInstance(Locale.KOREA).format(number)
    }
}

