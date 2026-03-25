package com.atolow.miixs.ui.payment;

import android.util.Log;
import android.widget.Toast;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;

/**
 * BillingClientStateListener 구현 클래스
 * ProGuard가 제거하지 않도록 별도 파일로 분리
 *
 * 중요: 이 클래스는 ProGuard에 의해 제거되면 안 됩니다!
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/atolow/miixs/ui/payment/BillingClientStateListenerImpl;", "Lcom/android/billingclient/api/BillingClientStateListener;", "activity", "Lcom/atolow/miixs/ui/payment/PaymentActivity;", "(Lcom/atolow/miixs/ui/payment/PaymentActivity;)V", "onBillingServiceDisconnected", "", "onBillingSetupFinished", "billingResult", "Lcom/android/billingclient/api/BillingResult;", "app_debug"})
public final class BillingClientStateListenerImpl implements com.android.billingclient.api.BillingClientStateListener {
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.ui.payment.PaymentActivity activity = null;
    
    public BillingClientStateListenerImpl(@org.jetbrains.annotations.NotNull()
    com.atolow.miixs.ui.payment.PaymentActivity activity) {
        super();
    }
    
    @java.lang.Override()
    public void onBillingSetupFinished(@org.jetbrains.annotations.NotNull()
    com.android.billingclient.api.BillingResult billingResult) {
    }
    
    @java.lang.Override()
    public void onBillingServiceDisconnected() {
    }
}