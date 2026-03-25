package com.atolow.miixs.ui.payment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.android.billingclient.api.*;
import com.atolow.miixs.R;
import com.atolow.miixs.data.local.TokenManager;
import com.atolow.miixs.data.model.payment.CreatePaymentRequestDto;
import com.atolow.miixs.data.model.payment.ProductResponseDto;
import com.atolow.miixs.data.model.payment.VerifyGooglePlayPaymentRequestDto;
import com.atolow.miixs.data.repository.PaymentRepository;
import com.atolow.miixs.data.repository.UserRepository;
import com.atolow.miixs.databinding.ActivityPaymentBinding;
import com.atolow.miixs.ui.auth.LoginActivity;
import com.atolow.miixs.ui.payment.adapter.ProductAdapter;
import com.atolow.miixs.ui.payment.viewmodel.PaymentViewModel;
import java.text.NumberFormat;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\"\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010$H\u0002J\u001c\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020\'2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020 0$J \u0010(\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010$J\u0010\u0010)\u001a\u00020\'2\u0006\u0010*\u001a\u00020+H\u0002J\u0012\u0010,\u001a\u00020 2\b\u0010-\u001a\u0004\u0018\u00010.H\u0002J\u0010\u0010/\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002J\u000e\u00100\u001a\u00020 2\u0006\u00101\u001a\u00020\u0016J\b\u00102\u001a\u00020 H\u0002J\u0012\u00103\u001a\u00020 2\b\u00104\u001a\u0004\u0018\u000105H\u0014J\b\u00106\u001a\u00020 H\u0014J\u0012\u00107\u001a\u00020 2\b\u0010-\u001a\u0004\u0018\u00010.H\u0014J \u00108\u001a\u00020 2\u0006\u00109\u001a\u00020:2\u000e\u0010;\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010<H\u0016J\b\u0010=\u001a\u00020 H\u0014J\u0006\u0010>\u001a\u00020 J\b\u0010?\u001a\u00020 H\u0002J\b\u0010@\u001a\u00020 H\u0002J\b\u0010A\u001a\u00020 H\u0002J\b\u0010B\u001a\u00020 H\u0002J\b\u0010C\u001a\u00020 H\u0002J\u000e\u0010D\u001a\u00020 2\u0006\u00101\u001a\u00020\u0016J(\u0010E\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0016\b\u0002\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020 \u0018\u00010FH\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006G"}, d2 = {"Lcom/atolow/miixs/ui/payment/PaymentActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/android/billingclient/api/PurchasesUpdatedListener;", "()V", "billingClient", "Lcom/android/billingclient/api/BillingClient;", "getBillingClient", "()Lcom/android/billingclient/api/BillingClient;", "setBillingClient", "(Lcom/android/billingclient/api/BillingClient;)V", "binding", "Lcom/atolow/miixs/databinding/ActivityPaymentBinding;", "isBillingClientReady", "", "()Z", "setBillingClientReady", "(Z)V", "paymentRepository", "Lcom/atolow/miixs/data/repository/PaymentRepository;", "productAdapter", "Lcom/atolow/miixs/ui/payment/adapter/ProductAdapter;", "selectedProduct", "Lcom/atolow/miixs/data/model/payment/ProductResponseDto;", "userRepository", "Lcom/atolow/miixs/data/repository/UserRepository;", "viewModel", "Lcom/atolow/miixs/ui/payment/viewmodel/PaymentViewModel;", "getViewModel", "()Lcom/atolow/miixs/ui/payment/viewmodel/PaymentViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "acknowledgePurchase", "", "purchase", "Lcom/android/billingclient/api/Purchase;", "onComplete", "Lkotlin/Function0;", "checkAndConsumeExistingPurchase", "productId", "", "consumePurchaseIfNeeded", "formatNumber", "number", "", "handleDeepLink", "intent", "Landroid/content/Intent;", "handlePurchase", "launchPurchaseFlow", "product", "loadCurrentCash", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNewIntent", "onPurchasesUpdated", "billingResult", "Lcom/android/billingclient/api/BillingResult;", "purchases", "", "onResume", "queryPurchases", "setupBillingClient", "setupClickListeners", "setupObservers", "setupRecyclerView", "setupToolbar", "startGooglePlayPayment", "verifyPurchaseWithServer", "Lkotlin/Function1;", "app_debug"})
public final class PaymentActivity extends androidx.appcompat.app.AppCompatActivity implements com.android.billingclient.api.PurchasesUpdatedListener {
    private com.atolow.miixs.databinding.ActivityPaymentBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.atolow.miixs.ui.payment.adapter.ProductAdapter productAdapter;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.PaymentRepository paymentRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.Nullable()
    private com.atolow.miixs.data.model.payment.ProductResponseDto selectedProduct;
    public com.android.billingclient.api.BillingClient billingClient;
    private boolean isBillingClientReady = false;
    
    public PaymentActivity() {
        super();
    }
    
    private final com.atolow.miixs.ui.payment.viewmodel.PaymentViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.android.billingclient.api.BillingClient getBillingClient() {
        return null;
    }
    
    public final void setBillingClient(@org.jetbrains.annotations.NotNull()
    com.android.billingclient.api.BillingClient p0) {
    }
    
    public final boolean isBillingClientReady() {
        return false;
    }
    
    public final void setBillingClientReady(boolean p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void setupBillingClient() {
    }
    
    private final void setupObservers() {
    }
    
    private final void loadCurrentCash() {
    }
    
    @java.lang.Override()
    protected void onNewIntent(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public final void startGooglePlayPayment(@org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.payment.ProductResponseDto product) {
    }
    
    /**
     * 이미 소유하고 있는 구매가 있는지 확인하고 소비 처리
     * 일회성 제품을 여러 번 구매할 수 있도록 하기 위함
     */
    public final void checkAndConsumeExistingPurchase(@org.jetbrains.annotations.NotNull()
    java.lang.String productId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onComplete) {
    }
    
    /**
     * 실제 구매 플로우 시작
     */
    public final void launchPurchaseFlow(@org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.payment.ProductResponseDto product) {
    }
    
    @java.lang.Override()
    public void onPurchasesUpdated(@org.jetbrains.annotations.NotNull()
    com.android.billingclient.api.BillingResult billingResult, @org.jetbrains.annotations.Nullable()
    java.util.List<com.android.billingclient.api.Purchase> purchases) {
    }
    
    private final void handlePurchase(com.android.billingclient.api.Purchase purchase) {
    }
    
    private final void acknowledgePurchase(com.android.billingclient.api.Purchase purchase, kotlin.jvm.functions.Function0<kotlin.Unit> onComplete) {
    }
    
    private final void verifyPurchaseWithServer(com.android.billingclient.api.Purchase purchase, kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onComplete) {
    }
    
    /**
     * 구매를 소비 처리 (재구매 가능하도록)
     * 캐시 충전 상품은 여러 번 구매할 수 있어야 하므로 소비 처리가 필요합니다.
     */
    public final void consumePurchaseIfNeeded(@org.jetbrains.annotations.NotNull()
    com.android.billingclient.api.Purchase purchase, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onComplete) {
    }
    
    public final void queryPurchases() {
    }
    
    private final void handleDeepLink(android.content.Intent intent) {
    }
    
    private final java.lang.String formatNumber(long number) {
        return null;
    }
}