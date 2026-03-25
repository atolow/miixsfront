package com.atolow.miixs.ui.payment

import android.util.Log
import android.widget.Toast
import com.android.billingclient.api.*
import com.atolow.miixs.data.model.payment.ProductResponseDto

/**
 * ProductDetailsResponseListener 구현 클래스
 * ProGuard가 제거하지 않도록 별도 파일로 분리
 * 
 * 중요: 이 클래스는 ProGuard에 의해 제거되면 안 됩니다!
 */
class ProductDetailsResponseListenerImpl(
    private val activity: PaymentActivity,
    private val product: ProductResponseDto
) : ProductDetailsResponseListener {
    
    override fun onProductDetailsResponse(
        billingResult: BillingResult,
        productDetailsResult: QueryProductDetailsResult
    ) {
        val responseCode = billingResult.responseCode
        val debugMessage = billingResult.debugMessage
        val productDetailsList = productDetailsResult.productDetailsList
        
        // 메인 스레드에서 실행되도록 보장
        android.os.Handler(android.os.Looper.getMainLooper()).post {
            if (responseCode != BillingClient.BillingResponseCode.OK) {
                val errorMessage = when (responseCode) {
                    BillingClient.BillingResponseCode.BILLING_UNAVAILABLE -> "Billing 서비스를 사용할 수 없습니다."
                    BillingClient.BillingResponseCode.SERVICE_UNAVAILABLE -> "Billing 서비스를 사용할 수 없습니다. 네트워크를 확인해주세요."
                    BillingClient.BillingResponseCode.DEVELOPER_ERROR -> "개발자 오류: Google Play Console에서 인앱 상품(${product.productId})이 등록되어 있는지 확인해주세요."
                    else -> "상품 정보를 불러올 수 없습니다."
                }
                Log.e("PaymentActivity", "$errorMessage (debugMessage: $debugMessage)")
                Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show()
                return@post
            }
            
            if (productDetailsList.isEmpty()) {
                Log.e("PaymentActivity", "상품을 찾을 수 없습니다: ${product.productId}")
                Toast.makeText(activity, "상품을 찾을 수 없습니다.", Toast.LENGTH_LONG).show()
                return@post
            }
            
            val productDetails = productDetailsList[0]
            val productDetailsParamsList = listOf(
                BillingFlowParams.ProductDetailsParams.newBuilder()
                    .setProductDetails(productDetails)
                    .build()
            )
            
            val billingFlowParams = BillingFlowParams.newBuilder()
                .setProductDetailsParamsList(productDetailsParamsList)
                .build()
            
            val response = activity.billingClient.launchBillingFlow(activity, billingFlowParams)
            
            // 디버그: 응답 코드와 메시지 로그 출력
            Log.d("PaymentActivity", "launchBillingFlow 응답: responseCode=${response.responseCode}, debugMessage='${response.debugMessage}'")
            
            if (response.responseCode != BillingClient.BillingResponseCode.OK) {
                if (response.responseCode == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
                    // 이미 소유하고 있는 경우, 기존 구매를 소비하고 다시 시도
                    Log.w("PaymentActivity", "ITEM_ALREADY_OWNED 오류 발생: ${product.productId}, debugMessage='${response.debugMessage}'")
                    // Google Play에서 반환한 메시지를 사용자에게 표시
                    val message = response.debugMessage ?: "이미 보유하고 있는 아이템입니다."
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                    
                    // 기존 구매 조회 및 소비 처리
                    val params = QueryPurchasesParams.newBuilder()
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build()
                    
                    activity.billingClient.queryPurchasesAsync(params) { queryResult, purchases ->
                        if (queryResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
                            val existingPurchase = purchases.find { 
                                it.products.contains(product.productId) && 
                                it.purchaseState == Purchase.PurchaseState.PURCHASED 
                            }
                            
                            if (existingPurchase != null) {
                                // 소비 처리
                                val consumeParams = ConsumeParams.newBuilder()
                                    .setPurchaseToken(existingPurchase.purchaseToken)
                                    .build()
                                
                                activity.billingClient.consumeAsync(consumeParams) { consumeResult, _ ->
                                    if (consumeResult.responseCode == BillingClient.BillingResponseCode.OK) {
                                        Log.d("PaymentActivity", "기존 구매 소비 완료. 잠시 후 다시 구매를 시도합니다.")
                                        // 소비 완료 후 약간의 지연을 두고 다시 구매 시도
                                        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                                            activity.startGooglePlayPayment(product)
                                        }, 1000) // 1초 대기
                                    } else {
                                        Log.e("PaymentActivity", "구매 소비 실패: ${consumeResult.debugMessage}")
                                        Toast.makeText(activity, "구매 처리 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_LONG).show()
                                    }
                                }
                            } else {
                                Log.w("PaymentActivity", "소유한 구매를 찾을 수 없습니다. 다시 시도합니다.")
                                // 구매를 찾을 수 없어도 다시 시도
                                android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                                    activity.startGooglePlayPayment(product)
                                }, 1000)
                            }
                        } else {
                            Log.e("PaymentActivity", "구매 내역 조회 실패: ${queryResult.debugMessage}")
                            Toast.makeText(activity, "구매 처리 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    val errorMessage = when (response.responseCode) {
                        BillingClient.BillingResponseCode.BILLING_UNAVAILABLE -> "이 버전의 애플리케이션에서는 Google Play를 통한 결제를 사용할 수 없습니다."
                        BillingClient.BillingResponseCode.SERVICE_UNAVAILABLE -> "Billing 서비스를 사용할 수 없습니다."
                        BillingClient.BillingResponseCode.DEVELOPER_ERROR -> "개발자 오류가 발생했습니다."
                        BillingClient.BillingResponseCode.ITEM_UNAVAILABLE -> "상품을 사용할 수 없습니다."
                        else -> "결제를 시작할 수 없습니다."
                    }
                    Log.e("PaymentActivity", "$errorMessage (debugMessage: ${response.debugMessage})")
                    Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
