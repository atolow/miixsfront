package com.atolow.miixs.ui.auth;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.atolow.miixs.R;
import com.atolow.miixs.data.model.Location;
import com.atolow.miixs.data.repository.AuthRepository;
import com.atolow.miixs.data.repository.UserRepository;
import com.atolow.miixs.databinding.ActivityOauthAdditionalInfoBinding;
import com.atolow.miixs.ui.main.MainActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J\u0010\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J\b\u0010\u001c\u001a\u00020\u0016H\u0016J\u0012\u0010\u001d\u001a\u00020\u00162\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\b\u0010 \u001a\u00020\u0016H\u0014J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\tH\u0002J\b\u0010#\u001a\u00020\u0016H\u0002J\b\u0010$\u001a\u00020\u0016H\u0002J\b\u0010%\u001a\u00020\u0016H\u0002J\b\u0010&\u001a\u00020\u0016H\u0002J\u0018\u0010\'\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\t2\u0006\u0010(\u001a\u00020\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/atolow/miixs/ui/auth/OAuthAdditionalInfoActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "authRepository", "Lcom/atolow/miixs/data/repository/AuthRepository;", "binding", "Lcom/atolow/miixs/databinding/ActivityOauthAdditionalInfoBinding;", "imageLauncher1", "Landroidx/activity/result/ActivityResultLauncher;", "", "imageLauncher2", "imageLauncher3", "isPhoneVerified", "", "selectedImageUris", "", "Landroid/net/Uri;", "selectedLocation", "Lcom/atolow/miixs/data/model/Location;", "userRepository", "Lcom/atolow/miixs/data/repository/UserRepository;", "completeAdditionalInfo", "", "copyUriToTempFile", "uri", "getFileNameFromUri", "getRealPathFromURI", "isValidImageFormat", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "sendVerificationCode", "phoneNumber", "setupClickListeners", "setupImageLaunchers", "showLocationDialog", "updateProfileImageDisplay", "verifyCode", "code", "app_release"})
public final class OAuthAdditionalInfoActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.atolow.miixs.databinding.ActivityOauthAdditionalInfoBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<android.net.Uri> selectedImageUris = null;
    private androidx.activity.result.ActivityResultLauncher<java.lang.String> imageLauncher1;
    private androidx.activity.result.ActivityResultLauncher<java.lang.String> imageLauncher2;
    private androidx.activity.result.ActivityResultLauncher<java.lang.String> imageLauncher3;
    @org.jetbrains.annotations.Nullable()
    private com.atolow.miixs.data.model.Location selectedLocation;
    private boolean isPhoneVerified = false;
    
    public OAuthAdditionalInfoActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void setupImageLaunchers() {
    }
    
    private final boolean isValidImageFormat(android.net.Uri uri) {
        return false;
    }
    
    private final java.lang.String getFileNameFromUri(android.net.Uri uri) {
        return null;
    }
    
    private final void updateProfileImageDisplay() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void showLocationDialog() {
    }
    
    private final void sendVerificationCode(java.lang.String phoneNumber) {
    }
    
    private final void verifyCode(java.lang.String phoneNumber, java.lang.String code) {
    }
    
    private final void completeAdditionalInfo() {
    }
    
    private final java.lang.String getRealPathFromURI(android.net.Uri uri) {
        return null;
    }
    
    private final java.lang.String copyUriToTempFile(android.net.Uri uri) {
        return null;
    }
}