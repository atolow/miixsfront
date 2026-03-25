package com.atolow.miixs.ui.payment;

import android.util.Log;
import android.widget.Toast;
import com.android.billingclient.api.*;
import com.atolow.miixs.data.model.payment.ProductResponseDto;

/**
 * ProductDetailsResponseListener 구현 클래스
 * ProGuard가 제거하지 않도록 별도 파일로 분리
 *
 * 중요: 이 클래스는 ProGuard에 의해 제거되면 안 됩니다!
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/atolow/miixs/ui/payment/ProductDetailsResponseListenerImpl;", "Lcom/android/billingclient/api/ProductDetailsResponseListener;", "activity", "Lcom/atolow/miixs/ui/payment/PaymentActivity;", "product", "Lcom/atolow/miixs/data/model/payment/ProductResponseDto;", "(Lcom/atolow/miixs/ui/payment/PaymentActivity;Lcom/atolow/miixs/data/model/payment/ProductResponseDto;)V", "onProductDetailsResponse", "", "billingResult", "Lcom/android/billingclient/api/BillingResult;", "productDetailsResult", "Lcom/android/billingclient/api/QueryProductDetailsResult;", "app_debug"})
public final class ProductDetailsResponseListenerImpl implements com.android.billingclient.api.ProductDetailsResponseListener {
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.ui.payment.PaymentActivity activity = null;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.model.payment.ProductResponseDto product = null;
    
    public ProductDetailsResponseListenerImpl(@org.jetbrains.annotations.NotNull()
    com.atolow.miixs.ui.payment.PaymentActivity activity, @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.payment.ProductResponseDto product) {
        super();
    }
    
    @java.lang.Override()
    public void onProductDetailsResponse(@org.jetbrains.annotations.NotNull()
    com.android.billingclient.api.BillingResult billingResult, @org.jetbrains.annotations.NotNull()
    com.android.billingclient.api.QueryProductDetailsResult productDetailsResult) {
    }
}