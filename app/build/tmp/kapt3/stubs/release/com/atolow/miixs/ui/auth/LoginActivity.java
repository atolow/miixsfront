package com.atolow.miixs.ui.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import com.atolow.miixs.R;
import com.atolow.miixs.databinding.ActivityLoginBinding;
import com.atolow.miixs.ui.main.MainActivity;
import com.atolow.miixs.ui.auth.viewmodel.LoginViewModel;
import com.atolow.miixs.ui.profile.PrivacyPolicyActivity;
import com.atolow.miixs.ui.profile.TermsOfServiceActivity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000  2\u00020\u0001:\u0001 B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0012\u001a\u00020\u0010H\u0002J\u0012\u0010\u0013\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u0012\u0010\u0016\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0010H\u0014J\b\u0010\u001a\u001a\u00020\u0010H\u0002J\b\u0010\u001b\u001a\u00020\u0010H\u0002J\b\u0010\u001c\u001a\u00020\u0010H\u0002J\b\u0010\u001d\u001a\u00020\u0010H\u0002J\b\u0010\u001e\u001a\u00020\u0010H\u0002J\b\u0010\u001f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f\u00a8\u0006!"}, d2 = {"Lcom/atolow/miixs/ui/auth/LoginActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/atolow/miixs/databinding/ActivityLoginBinding;", "oauthCallbackProcessed", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "viewModel", "Lcom/atolow/miixs/ui/auth/viewmodel/LoginViewModel;", "getViewModel", "()Lcom/atolow/miixs/ui/auth/viewmodel/LoginViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "checkAgreementStatus", "", "handleErrorMessage", "handleOAuthCallback", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", "intent", "Landroid/content/Intent;", "onResume", "setupClickListeners", "setupObservers", "showAgreementScreen", "showLoginForm", "startKakaoOAuthLogin", "startNaverOAuthLogin", "Companion", "app_release"})
public final class LoginActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.atolow.miixs.databinding.ActivityLoginBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private android.content.SharedPreferences sharedPreferences;
    private boolean oauthCallbackProcessed = false;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "MiixsPrefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_AGREEMENT_ACCEPTED = "agreement_accepted";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String BASE_URL = "https://miixs.com";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String NAVER_OAUTH_URL = "https://miixs.com/oauth2/authorization/naver";
    @org.jetbrains.annotations.NotNull()
    public static final com.atolow.miixs.ui.auth.LoginActivity.Companion Companion = null;
    
    public LoginActivity() {
        super();
    }
    
    private final com.atolow.miixs.ui.auth.viewmodel.LoginViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void handleErrorMessage() {
    }
    
    private final void checkAgreementStatus() {
    }
    
    private final void showAgreementScreen() {
    }
    
    private final void showLoginForm() {
    }
    
    private final void setupObservers() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void startNaverOAuthLogin() {
    }
    
    private final void startKakaoOAuthLogin() {
    }
    
    private final void handleOAuthCallback() {
    }
    
    @java.lang.Override()
    protected void onNewIntent(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/atolow/miixs/ui/auth/LoginActivity$Companion;", "", "()V", "BASE_URL", "", "KEY_AGREEMENT_ACCEPTED", "NAVER_OAUTH_URL", "PREFS_NAME", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}