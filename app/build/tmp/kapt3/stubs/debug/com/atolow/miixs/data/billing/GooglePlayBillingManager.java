package com.atolow.miixs.data.billing;

import android.app.Activity;
import android.util.Log;
import com.android.billingclient.api.*;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\u0011J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0014\u0010\u0016\u001a\u00020\u00112\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u0018J\u000e\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u0011J\u0006\u0010\u001d\u001a\u00020\u0011R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/atolow/miixs/data/billing/GooglePlayBillingManager;", "", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "_purchaseState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/atolow/miixs/data/billing/PurchaseState;", "billingClient", "Lcom/android/billingclient/api/BillingClient;", "purchaseState", "Lkotlinx/coroutines/flow/StateFlow;", "getPurchaseState", "()Lkotlinx/coroutines/flow/StateFlow;", "purchasesUpdatedListener", "Lcom/android/billingclient/api/PurchasesUpdatedListener;", "acknowledgePurchase", "", "purchase", "Lcom/android/billingclient/api/Purchase;", "endConnection", "handlePurchase", "initialize", "onReady", "Lkotlin/Function0;", "launchBillingFlow", "productId", "", "queryPurchases", "resetPurchaseState", "Companion", "app_debug"})
public final class GooglePlayBillingManager {
    @org.jetbrains.annotations.NotNull()
    private final android.app.Activity activity = null;
    @org.jetbrains.annotations.Nullable()
    private com.android.billingclient.api.BillingClient billingClient;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.atolow.miixs.data.billing.PurchaseState> _purchaseState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.atolow.miixs.data.billing.PurchaseState> purchaseState = null;
    @org.jetbrains.annotations.NotNull()
    private final com.android.billingclient.api.PurchasesUpdatedListener purchasesUpdatedListener = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "GooglePlayBillingManager";
    @org.jetbrains.annotations.NotNull()
    public static final com.atolow.miixs.data.billing.GooglePlayBillingManager.Companion Companion = null;
    
    public GooglePlayBillingManager(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.atolow.miixs.data.billing.PurchaseState> getPurchaseState() {
        return null;
    }
    
    public final void initialize(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onReady) {
    }
    
    public final void launchBillingFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String productId) {
    }
    
    private final void handlePurchase(com.android.billingclient.api.Purchase purchase) {
    }
    
    private final void acknowledgePurchase(com.android.billingclient.api.Purchase purchase) {
    }
    
    public final void queryPurchases() {
    }
    
    public final void resetPurchaseState() {
    }
    
    public final void endConnection() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/atolow/miixs/data/billing/GooglePlayBillingManager$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}