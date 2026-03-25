package com.atolow.miixs.ui.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import com.atolow.miixs.data.network.AuthInterceptor;
import com.atolow.miixs.ui.auth.LoginActivity;
import com.atolow.miixs.util.AuthUtil;
import com.atolow.miixs.util.ErrorHandler;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\tH\u0002J\u0018\u0010\u000e\u001a\u00020\t2\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0010H\u0004J\u0018\u0010\u0011\u001a\u00020\t2\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0010H\u0004J\u0016\u0010\u0014\u001a\u00020\t2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0010H\u0004J\u0012\u0010\u0017\u001a\u00020\t2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\tH\u0014J\b\u0010\u001b\u001a\u00020\tH\u0014J\u0012\u0010\u001c\u001a\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/atolow/miixs/ui/common/BaseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "loadingDialog", "Lcom/atolow/miixs/ui/common/LoadingDialog;", "tokenExpiredReceiver", "Landroid/content/BroadcastReceiver;", "userBannedReceiver", "handleTokenExpiration", "", "handleUserBanned", "banMessage", "", "hideLoading", "observeError", "error", "Lkotlinx/coroutines/flow/StateFlow;", "observeErrorFlow", "errorFlow", "", "observeLoading", "isLoading", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "showLoading", "message", "app_debug"})
public abstract class BaseActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.Nullable()
    private com.atolow.miixs.ui.common.LoadingDialog loadingDialog;
    @org.jetbrains.annotations.Nullable()
    private android.content.BroadcastReceiver tokenExpiredReceiver;
    @org.jetbrains.annotations.Nullable()
    private android.content.BroadcastReceiver userBannedReceiver;
    
    public BaseActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void handleTokenExpiration() {
    }
    
    private final void handleUserBanned(java.lang.String banMessage) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    protected final void observeLoading(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading) {
    }
    
    protected final void observeError(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.StateFlow<java.lang.String> error) {
    }
    
    protected final void observeErrorFlow(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.StateFlow<? extends java.lang.Throwable> errorFlow) {
    }
    
    private final void showLoading(java.lang.String message) {
    }
    
    private final void hideLoading() {
    }
}