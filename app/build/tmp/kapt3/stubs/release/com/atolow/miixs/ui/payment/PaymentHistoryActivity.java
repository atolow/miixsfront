package com.atolow.miixs.ui.payment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.atolow.miixs.data.local.TokenManager;
import com.atolow.miixs.databinding.ActivityPaymentHistoryBinding;
import com.atolow.miixs.ui.auth.LoginActivity;
import com.atolow.miixs.ui.payment.adapter.PaymentHistoryAdapter;
import com.atolow.miixs.ui.payment.viewmodel.PaymentHistoryViewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u000eH\u0002J\b\u0010\u0012\u001a\u00020\u000eH\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0002J\b\u0010\u0015\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0016"}, d2 = {"Lcom/atolow/miixs/ui/payment/PaymentHistoryActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/atolow/miixs/databinding/ActivityPaymentHistoryBinding;", "paymentHistoryAdapter", "Lcom/atolow/miixs/ui/payment/adapter/PaymentHistoryAdapter;", "viewModel", "Lcom/atolow/miixs/ui/payment/viewmodel/PaymentHistoryViewModel;", "getViewModel", "()Lcom/atolow/miixs/ui/payment/viewmodel/PaymentHistoryViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupObservers", "setupRecyclerView", "setupScrollListener", "setupSwipeRefresh", "setupToolbar", "app_release"})
public final class PaymentHistoryActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.atolow.miixs.databinding.ActivityPaymentHistoryBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.atolow.miixs.ui.payment.adapter.PaymentHistoryAdapter paymentHistoryAdapter;
    
    public PaymentHistoryActivity() {
        super();
    }
    
    private final com.atolow.miixs.ui.payment.viewmodel.PaymentHistoryViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void setupSwipeRefresh() {
    }
    
    private final void setupScrollListener() {
    }
    
    private final void setupObservers() {
    }
}