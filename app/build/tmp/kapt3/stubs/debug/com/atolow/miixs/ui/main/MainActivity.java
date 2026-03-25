package com.atolow.miixs.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.atolow.miixs.R;
import com.atolow.miixs.data.local.TokenManager;
import com.atolow.miixs.data.repository.UserRepository;
import com.atolow.miixs.databinding.ActivityMainBinding;
import com.atolow.miixs.service.ChatNotificationService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import com.atolow.miixs.data.network.AuthInterceptor;
import com.atolow.miixs.ui.auth.LoginActivity;
import com.atolow.miixs.ui.auth.OAuthAdditionalInfoActivity;
import com.atolow.miixs.util.AuthUtil;
import com.atolow.miixs.util.ChatNotificationManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\u000e\u0010\f\u001a\u00020\u000bH\u0082@\u00a2\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000bH\u0002J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u000bH\u0016J\u0012\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u000bH\u0014J\b\u0010\u0017\u001a\u00020\u000bH\u0014J-\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u00042\u000e\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016\u00a2\u0006\u0002\u0010\u001eJ\b\u0010\u001f\u001a\u00020\u000bH\u0014J\b\u0010 \u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/atolow/miixs/ui/main/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "REQUEST_CODE_NOTIFICATION_PERMISSION", "", "binding", "Lcom/atolow/miixs/databinding/ActivityMainBinding;", "tokenExpiredReceiver", "Landroid/content/BroadcastReceiver;", "userBannedReceiver", "checkAndRequestNotificationPermission", "", "checkProfileAndNavigate", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleTokenExpiration", "handleUserBanned", "banMessage", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "startForegroundService", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.Nullable()
    private android.content.BroadcastReceiver tokenExpiredReceiver;
    @org.jetbrains.annotations.Nullable()
    private android.content.BroadcastReceiver userBannedReceiver;
    private com.atolow.miixs.databinding.ActivityMainBinding binding;
    private final int REQUEST_CODE_NOTIFICATION_PERMISSION = 1001;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void handleTokenExpiration() {
    }
    
    private final void handleUserBanned(java.lang.String banMessage) {
    }
    
    private final java.lang.Object checkProfileAndNavigate(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void checkAndRequestNotificationPermission() {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    private final void startForegroundService() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
}