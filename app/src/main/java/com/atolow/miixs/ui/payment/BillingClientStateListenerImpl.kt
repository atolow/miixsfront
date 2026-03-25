package com.atolow.miixs.ui.payment

import android.util.Log
import android.widget.Toast
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingResult

/**
 * BillingClientStateListener 구현 클래스
 * ProGuard가 제거하지 않도록 별도 파일로 분리
 * 
 * 중요: 이 클래스는 ProGuard에 의해 제거되면 안 됩니다!
 */
class BillingClientStateListenerImpl(
    private val activity: PaymentActivity
) : BillingClientStateListener {
    
    override fun onBillingSetupFinished(billingResult: BillingResult) {
        val responseCode = billingResult.responseCode
        val debugMessage = billingResult.debugMessage
        
        // 메인 스레드에서 실행되도록 보장
        android.os.Handler(android.os.Looper.getMainLooper()).post {
            if (responseCode == BillingClient.BillingResponseCode.OK) {
                activity.isBillingClientReady = true
            } else {
                activity.isBillingClientReady = false
                val errorMessage = when (responseCode) {
                    BillingClient.BillingResponseCode.BILLING_UNAVAILABLE -> "Billing 서비스를 사용할 수 없습니다."
                    BillingClient.BillingResponseCode.SERVICE_UNAVAILABLE -> "Billing 서비스를 사용할 수 없습니다. 네트워크 연결을 확인해주세요."
                    BillingClient.BillingResponseCode.DEVELOPER_ERROR -> "개발자 오류가 발생했습니다."
                    BillingClient.BillingResponseCode.ERROR -> "알 수 없는 오류가 발생했습니다."
                    else -> "Billing 연결 실패"
                }
                Log.e("PaymentActivity", "$errorMessage (debugMessage: $debugMessage)")
            }
        }
    }
    
    override fun onBillingServiceDisconnected() {
        android.os.Handler(android.os.Looper.getMainLooper()).post {
            activity.isBillingClientReady = false
        }
    }
}
