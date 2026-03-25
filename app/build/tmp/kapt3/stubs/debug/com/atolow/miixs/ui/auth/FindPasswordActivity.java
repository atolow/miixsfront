package com.atolow.miixs.ui.auth;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.atolow.miixs.databinding.ActivityFindPasswordBinding;
import com.atolow.miixs.ui.auth.viewmodel.FindPasswordViewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0012\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0012H\u0014J\b\u0010\u0017\u001a\u00020\u0012H\u0002J\b\u0010\u0018\u001a\u00020\u0012H\u0002J\b\u0010\u0019\u001a\u00020\u0012H\u0002J\b\u0010\u001a\u001a\u00020\u0012H\u0002J\b\u0010\u001b\u001a\u00020\u0012H\u0002J\b\u0010\u001c\u001a\u00020\u0012H\u0002J\b\u0010\u001d\u001a\u00020\u0012H\u0002J\u0010\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e\u00a8\u0006$"}, d2 = {"Lcom/atolow/miixs/ui/auth/FindPasswordActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/atolow/miixs/databinding/ActivityFindPasswordBinding;", "countDownTimer", "Landroid/os/CountDownTimer;", "currentStep", "", "verifiedEmail", "", "viewModel", "Lcom/atolow/miixs/ui/auth/viewmodel/FindPasswordViewModel;", "getViewModel", "()Lcom/atolow/miixs/ui/auth/viewmodel/FindPasswordViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "checkPasswordMatch", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setupClickListeners", "setupObservers", "setupPasswordValidation", "setupToolbar", "showStep1", "showStep2", "showStep3", "startResendTimer", "seconds", "", "validatePassword", "", "password", "app_debug"})
public final class FindPasswordActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.atolow.miixs.databinding.ActivityFindPasswordBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private android.os.CountDownTimer countDownTimer;
    private int currentStep = 1;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String verifiedEmail;
    
    public FindPasswordActivity() {
        super();
    }
    
    private final com.atolow.miixs.ui.auth.viewmodel.FindPasswordViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void setupObservers() {
    }
    
    private final void setupPasswordValidation() {
    }
    
    private final void checkPasswordMatch() {
    }
    
    private final boolean validatePassword(java.lang.String password) {
        return false;
    }
    
    private final void showStep1() {
    }
    
    private final void showStep2() {
    }
    
    private final void showStep3() {
    }
    
    private final void startResendTimer(long seconds) {
    }
}