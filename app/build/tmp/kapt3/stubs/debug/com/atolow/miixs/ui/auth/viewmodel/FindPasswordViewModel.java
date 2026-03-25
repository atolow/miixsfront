package com.atolow.miixs.ui.auth.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.atolow.miixs.data.model.auth.FindPasswordResponseDto;
import com.atolow.miixs.data.model.auth.ResetPasswordResponseDto;
import com.atolow.miixs.data.model.auth.VerifyPasswordResetCodeResponseDto;
import com.atolow.miixs.data.repository.AuthRepository;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u0016\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bJ\u0016\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u001bR\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00050\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010\u0014\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00050\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u001f\u0010\u0016\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00050\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010\u00a8\u0006 "}, d2 = {"Lcom/atolow/miixs/ui/auth/viewmodel/FindPasswordViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_findPasswordResult", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlin/Result;", "Lcom/atolow/miixs/data/model/auth/FindPasswordResponseDto;", "_isLoading", "", "_resetPasswordResult", "Lcom/atolow/miixs/data/model/auth/ResetPasswordResponseDto;", "_verifyCodeResult", "Lcom/atolow/miixs/data/model/auth/VerifyPasswordResetCodeResponseDto;", "findPasswordResult", "Lkotlinx/coroutines/flow/StateFlow;", "getFindPasswordResult", "()Lkotlinx/coroutines/flow/StateFlow;", "isLoading", "repository", "Lcom/atolow/miixs/data/repository/AuthRepository;", "resetPasswordResult", "getResetPasswordResult", "verifyCodeResult", "getVerifyCodeResult", "findPassword", "", "email", "", "resetPassword", "newPassword", "verifyPasswordResetCode", "code", "app_debug"})
public final class FindPasswordViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.AuthRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<kotlin.Result<com.atolow.miixs.data.model.auth.FindPasswordResponseDto>> _findPasswordResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.auth.FindPasswordResponseDto>> findPasswordResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<kotlin.Result<com.atolow.miixs.data.model.auth.VerifyPasswordResetCodeResponseDto>> _verifyCodeResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.auth.VerifyPasswordResetCodeResponseDto>> verifyCodeResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<kotlin.Result<com.atolow.miixs.data.model.auth.ResetPasswordResponseDto>> _resetPasswordResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.auth.ResetPasswordResponseDto>> resetPasswordResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    
    public FindPasswordViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.auth.FindPasswordResponseDto>> getFindPasswordResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.auth.VerifyPasswordResetCodeResponseDto>> getVerifyCodeResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.auth.ResetPasswordResponseDto>> getResetPasswordResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    public final void findPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
    }
    
    public final void verifyPasswordResetCode(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String code) {
    }
    
    public final void resetPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String newPassword) {
    }
}