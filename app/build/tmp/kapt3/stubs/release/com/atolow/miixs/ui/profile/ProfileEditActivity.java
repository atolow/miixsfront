package com.atolow.miixs.ui.profile;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.atolow.miixs.R;
import com.atolow.miixs.data.model.Location;
import com.atolow.miixs.databinding.ActivityProfileEditBinding;
import com.atolow.miixs.ui.profile.viewmodel.ProfileEditViewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0018\u001a\u00020\u0007H\u0002J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\u0010\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u001aH\u0002J\b\u0010!\u001a\u00020\u001aH\u0002J\b\u0010\"\u001a\u00020\u001aH\u0002J\b\u0010#\u001a\u00020\u001aH\u0002J\b\u0010$\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006%"}, d2 = {"Lcom/atolow/miixs/ui/profile/ProfileEditActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/atolow/miixs/databinding/ActivityProfileEditBinding;", "cameraLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "imageLauncher1", "", "imageLauncher2", "imageLauncher3", "selectedImageUris", "", "selectedLocation", "Lcom/atolow/miixs/data/model/Location;", "viewModel", "Lcom/atolow/miixs/ui/profile/viewmodel/ProfileEditViewModel;", "getViewModel", "()Lcom/atolow/miixs/ui/profile/viewmodel/ProfileEditViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getRealPathFromURI", "uri", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "pickImageFromGallery", "index", "", "setupClickListeners", "setupObservers", "setupToolbar", "showLocationDialog", "updateProfileImageDisplay", "app_release"})
public final class ProfileEditActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.atolow.miixs.databinding.ActivityProfileEditBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<android.net.Uri> selectedImageUris = null;
    private androidx.activity.result.ActivityResultLauncher<java.lang.String> imageLauncher1;
    private androidx.activity.result.ActivityResultLauncher<java.lang.String> imageLauncher2;
    private androidx.activity.result.ActivityResultLauncher<java.lang.String> imageLauncher3;
    @org.jetbrains.annotations.Nullable()
    private com.atolow.miixs.data.model.Location selectedLocation;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<android.net.Uri> cameraLauncher = null;
    
    public ProfileEditActivity() {
        super();
    }
    
    private final com.atolow.miixs.ui.profile.viewmodel.ProfileEditViewModel getViewModel() {
        return null;
    }
    
    private final void updateProfileImageDisplay() {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupObservers() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void pickImageFromGallery(int index) {
    }
    
    private final void showLocationDialog() {
    }
    
    private final java.lang.String getRealPathFromURI(android.net.Uri uri) {
        return null;
    }
}