package com.atolow.miixs.data.billing

import android.app.Activity
import android.util.Log
import com.android.billingclient.api.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GooglePlayBillingManager(
    private val activity: Activity
) {
    private var billingClient: BillingClient? = null
    private val _purchaseState = MutableStateFlow<PurchaseState>(PurchaseState.Idle)
    val purchaseState: StateFlow<PurchaseState> = _purchaseState.asStateFlow()

    private val purchasesUpdatedListener = PurchasesUpdatedListener { billingResult, purchases ->
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                handlePurchase(purchase)
            }
        } else if (billingResult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
            Log.d(TAG, "결제가 취소되었습니다")
            _purchaseState.value = PurchaseState.Cancelled
        } else {
            Log.e(TAG, "결제 오류: ${billingResult.debugMessage}")
            _purchaseState.value = PurchaseState.Error(billingResult.debugMessage ?: "알 수 없는 오류")
        }
    }

    fun initialize(onReady: () -> Unit) {
        val pendingPurchasesParams = PendingPurchasesParams.newBuilder()
            .enableOneTimeProducts()
            .build()
        
        billingClient = BillingClient.newBuilder(activity)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases(pendingPurchasesParams)
            .build()

        billingClient?.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    Log.d(TAG, "BillingClient 초기화 완료")
                    onReady()
                } else {
                    Log.e(TAG, "BillingClient 초기화 실패: ${billingResult.debugMessage}")
                }
            }

            override fun onBillingServiceDisconnected() {
                Log.w(TAG, "BillingClient 연결 끊김")
            }
        })
    }

    fun launchBillingFlow(productId: String) {
        val billingClient = billingClient ?: run {
            Log.e(TAG, "BillingClient가 초기화되지 않았습니다.")
            _purchaseState.value = PurchaseState.Error("결제 서비스를 사용할 수 없습니다.")
            return
        }

        val params = QueryProductDetailsParams.newBuilder()
            .setProductList(
                listOf(
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(productId)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build()
                )
            )
            .build()
        
        billingClient.queryProductDetailsAsync(params, object : ProductDetailsResponseListener {
            override fun onProductDetailsResponse(
                billingResult: BillingResult,
                productDetailsResult: QueryProductDetailsResult
            ) {
                val productDetailsList = productDetailsResult.productDetailsList
                Log.d(TAG, "상품 정보 조회 완료: responseCode=${billingResult.responseCode}, productCount=${productDetailsList.size}, productId=$productId")
                
                when (billingResult.responseCode) {
                    BillingClient.BillingResponseCode.OK -> {
                        if (productDetailsList.isNotEmpty()) {
                            val productDetails = productDetailsList[0]
                            Log.d(TAG, "상품 정보 조회 성공: ${productDetails.productId}, ${productDetails.title}")
                            
                            val productDetailsParamsList = listOf(
                                BillingFlowParams.ProductDetailsParams.newBuilder()
                                    .setProductDetails(productDetails)
                                    .build()
                            )

                            val billingFlowParams = BillingFlowParams.newBuilder()
                                .setProductDetailsParamsList(productDetailsParamsList)
                                .build()

                            val responseCode = billingClient.launchBillingFlow(activity, billingFlowParams)
                            if (responseCode.responseCode != BillingClient.BillingResponseCode.OK) {
                                val errorMsg = "결제 플로우 시작 실패 (응답 코드 ${responseCode.responseCode}): ${responseCode.debugMessage}"
                                Log.e(TAG, errorMsg)
                                _purchaseState.value = PurchaseState.Error(errorMsg)
                            } else {
                                Log.d(TAG, "결제 플로우 시작 성공")
                            }
                        } else {
                            val errorMsg = "상품을 찾을 수 없습니다. Google Play Console에서 상품 ID '$productId'가 등록되어 있는지 확인해주세요."
                            Log.e(TAG, errorMsg)
                            _purchaseState.value = PurchaseState.Error(errorMsg)
                        }
                    }
                    BillingClient.BillingResponseCode.SERVICE_UNAVAILABLE -> {
                        val errorMsg = "Google Play 서비스를 사용할 수 없습니다. 네트워크 연결을 확인해주세요."
                        Log.e(TAG, errorMsg)
                        _purchaseState.value = PurchaseState.Error(errorMsg)
                    }
                    BillingClient.BillingResponseCode.BILLING_UNAVAILABLE -> {
                        val errorMsg = "결제 서비스를 사용할 수 없습니다. Google Play가 설치되어 있는지 확인해주세요."
                        Log.e(TAG, errorMsg)
                        _purchaseState.value = PurchaseState.Error(errorMsg)
                    }
                    BillingClient.BillingResponseCode.ITEM_UNAVAILABLE -> {
                        val errorMsg = "상품 ID '$productId'를 사용할 수 없습니다. Google Play Console에서 상품이 활성화되어 있는지 확인해주세요."
                        Log.e(TAG, errorMsg)
                        _purchaseState.value = PurchaseState.Error(errorMsg)
                    }
                    BillingClient.BillingResponseCode.DEVELOPER_ERROR -> {
                        val errorMsg = "개발자 오류: 상품 설정을 확인해주세요. (상세: ${billingResult.debugMessage})"
                        Log.e(TAG, errorMsg)
                        _purchaseState.value = PurchaseState.Error(errorMsg)
                    }
                    else -> {
                        val errorMsg = "상품 정보 조회 실패 (응답 코드 ${billingResult.responseCode}): ${billingResult.debugMessage ?: "알 수 없는 오류"}"
                        Log.e(TAG, errorMsg)
                        _purchaseState.value = PurchaseState.Error(errorMsg)
                    }
                }
            }
        })
    }

    private fun handlePurchase(purchase: Purchase) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
            if (!purchase.isAcknowledged) {
                // 구매 확인 처리
                acknowledgePurchase(purchase)
            }
            // Purchase 객체에서 orderId를 가져오거나, 없으면 purchaseToken을 orderId로 사용
            // Google Play Billing Library 5.0+에서는 purchaseToken을 사용하는 것이 권장됩니다
            val orderId = purchase.purchaseToken
            Log.d(TAG, "구매 완료: productId=${purchase.products[0]}, purchaseToken=${purchase.purchaseToken}, orderId=$orderId")
            _purchaseState.value = PurchaseState.Purchased(
                productId = purchase.products[0],
                purchaseToken = purchase.purchaseToken,
                orderId = orderId
            )
        } else if (purchase.purchaseState == Purchase.PurchaseState.PENDING) {
            Log.d(TAG, "구매 대기 중: productId=${purchase.products[0]}")
            _purchaseState.value = PurchaseState.Pending
        }
    }

    private fun acknowledgePurchase(purchase: Purchase) {
        val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()

        billingClient?.acknowledgePurchase(acknowledgePurchaseParams) { billingResult ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                Log.d(TAG, "구매 확인 완료")
            } else {
                Log.e(TAG, "구매 확인 실패: ${billingResult.debugMessage}")
            }
        }
    }

    fun queryPurchases() {
        val billingClient = billingClient ?: return

        billingClient.queryPurchasesAsync(
            QueryPurchasesParams.newBuilder()
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        ) { billingResult, purchases ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                for (purchase in purchases) {
                    if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged) {
                        acknowledgePurchase(purchase)
                    }
                }
            }
        }
    }

    fun resetPurchaseState() {
        _purchaseState.value = PurchaseState.Idle
    }

    fun endConnection() {
        billingClient?.endConnection()
        billingClient = null
    }

    companion object {
        private const val TAG = "GooglePlayBillingManager"
    }
}

sealed class PurchaseState {
    object Idle : PurchaseState()
    object Pending : PurchaseState()
    data class Purchased(val productId: String, val purchaseToken: String, val orderId: String) : PurchaseState()
    object Cancelled : PurchaseState()
    data class Error(val message: String) : PurchaseState()
}
