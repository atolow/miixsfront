package com.atolow.miixs.ui.common;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import com.atolow.miixs.util.ErrorHandler;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0004J\u0018\u0010\b\u001a\u00020\u00042\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0006H\u0004J\u001e\u0010\u000b\u001a\u00020\u00042\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0004J\u001a\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016\u00a8\u0006\u0014"}, d2 = {"Lcom/atolow/miixs/ui/common/BaseFragment;", "Landroidx/fragment/app/Fragment;", "()V", "observeError", "", "error", "Lkotlinx/coroutines/flow/StateFlow;", "", "observeErrorFlow", "errorFlow", "", "observeLoading", "isLoading", "", "progressBar", "Landroid/view/View;", "onViewCreated", "view", "savedInstanceState", "Landroid/os/Bundle;", "app_release"})
public abstract class BaseFragment extends androidx.fragment.app.Fragment {
    
    public BaseFragment() {
        super();
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    protected final void observeLoading(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading, @org.jetbrains.annotations.NotNull()
    android.view.View progressBar) {
    }
    
    protected final void observeError(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.StateFlow<java.lang.String> error) {
    }
    
    protected final void observeErrorFlow(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.StateFlow<? extends java.lang.Throwable> errorFlow) {
    }
}