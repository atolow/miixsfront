package com.atolow.miixs.ui.profile;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import com.bumptech.glide.Glide;
import com.atolow.miixs.R;
import com.atolow.miixs.data.local.TokenManager;
import com.atolow.miixs.databinding.ActivityUserProfileBinding;
import com.atolow.miixs.ui.chat.ChatRoomActivity;
import com.atolow.miixs.ui.profile.adapter.ProfileImagePagerAdapter;
import com.atolow.miixs.ui.profile.viewmodel.UserProfileViewModel;
import com.atolow.miixs.util.ChatNotificationManager;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0013H\u0002J\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0013H\u0002J\b\u0010\u001b\u001a\u00020\u0013H\u0002J\b\u0010\u001c\u001a\u00020\u0013H\u0002J\b\u0010\u001d\u001a\u00020\u0013H\u0002J\b\u0010\u001e\u001a\u00020\u0013H\u0002J\b\u0010\u001f\u001a\u00020\u0013H\u0002J\b\u0010 \u001a\u00020\u0013H\u0002J\u0010\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u0019H\u0002J\u0010\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020%H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r\u00a8\u0006&"}, d2 = {"Lcom/atolow/miixs/ui/profile/UserProfileActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/atolow/miixs/databinding/ActivityUserProfileBinding;", "imagePagerAdapter", "Lcom/atolow/miixs/ui/profile/adapter/ProfileImagePagerAdapter;", "indicators", "", "Landroid/widget/ImageView;", "viewModel", "Lcom/atolow/miixs/ui/profile/viewmodel/UserProfileViewModel;", "getViewModel", "()Lcom/atolow/miixs/ui/profile/viewmodel/UserProfileViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "isOwnProfile", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupClickListeners", "setupIndicators", "count", "", "setupObservers", "setupSwipeArea", "setupToolbar", "setupViewPager", "showBlockConfirmDialog", "showMoreMenuDialog", "showReportConfirmDialog", "updateIndicators", "selectedPosition", "updateProfileUI", "profile", "Lcom/atolow/miixs/data/model/user/ProfileResponseDto;", "app_release"})
public final class UserProfileActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.atolow.miixs.databinding.ActivityUserProfileBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.atolow.miixs.ui.profile.adapter.ProfileImagePagerAdapter imagePagerAdapter;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<android.widget.ImageView> indicators = null;
    
    public UserProfileActivity() {
        super();
    }
    
    private final com.atolow.miixs.ui.profile.viewmodel.UserProfileViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupViewPager() {
    }
    
    private final void setupSwipeArea() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void showMoreMenuDialog() {
    }
    
    private final boolean isOwnProfile() {
        return false;
    }
    
    private final void showBlockConfirmDialog() {
    }
    
    private final void showReportConfirmDialog() {
    }
    
    private final void setupObservers() {
    }
    
    private final void updateProfileUI(com.atolow.miixs.data.model.user.ProfileResponseDto profile) {
    }
    
    private final void setupIndicators(int count) {
    }
    
    private final void updateIndicators(int selectedPosition) {
    }
}