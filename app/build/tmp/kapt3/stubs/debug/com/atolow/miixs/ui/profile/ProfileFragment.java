package com.atolow.miixs.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.atolow.miixs.R;
import com.atolow.miixs.data.local.NotificationSettings;
import com.atolow.miixs.data.local.TokenManager;
import com.atolow.miixs.databinding.FragmentProfileBinding;
import com.atolow.miixs.ui.auth.LoginActivity;
import com.atolow.miixs.ui.profile.PrivacyPolicyActivity;
import com.atolow.miixs.ui.profile.TermsOfServiceActivity;
import com.atolow.miixs.ui.profile.viewmodel.ProfileViewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\u001a\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u001b\u001a\u00020\u0017H\u0002J\b\u0010\u001c\u001a\u00020\u0017H\u0002J\b\u0010\u001d\u001a\u00020\u0017H\u0002J\b\u0010\u001e\u001a\u00020\u0017H\u0002J\b\u0010\u001f\u001a\u00020\u0017H\u0002J\b\u0010 \u001a\u00020\u0017H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b\u00a8\u0006!"}, d2 = {"Lcom/atolow/miixs/ui/profile/ProfileFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/atolow/miixs/databinding/FragmentProfileBinding;", "binding", "getBinding", "()Lcom/atolow/miixs/databinding/FragmentProfileBinding;", "viewModel", "Lcom/atolow/miixs/ui/profile/viewmodel/ProfileViewModel;", "getViewModel", "()Lcom/atolow/miixs/ui/profile/viewmodel/ProfileViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onResume", "onViewCreated", "view", "sendEmail", "setupClickListeners", "setupObservers", "setupPushNotificationToggle", "showDeactivateDialog", "showLogoutDialog", "app_debug"})
public final class ProfileFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.atolow.miixs.databinding.FragmentProfileBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    
    public ProfileFragment() {
        super();
    }
    
    private final com.atolow.miixs.databinding.FragmentProfileBinding getBinding() {
        return null;
    }
    
    private final com.atolow.miixs.ui.profile.viewmodel.ProfileViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void setupPushNotificationToggle() {
    }
    
    private final void setupObservers() {
    }
    
    private final void showLogoutDialog() {
    }
    
    private final void showDeactivateDialog() {
    }
    
    private final void sendEmail() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}