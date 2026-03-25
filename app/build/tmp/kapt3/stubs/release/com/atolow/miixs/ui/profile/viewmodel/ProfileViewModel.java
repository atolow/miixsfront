package com.atolow.miixs.ui.profile.viewmodel;

import androidx.lifecycle.ViewModel;
import com.atolow.miixs.data.model.auth.LogoutResponseDto;
import com.atolow.miixs.data.model.user.DashboardResponseDto;
import com.atolow.miixs.data.repository.AuthRepository;
import com.atolow.miixs.data.repository.UserRepository;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u001e\u001a\u00020\bJ\u0006\u0010\u001f\u001a\u00020\bJ\u0006\u0010 \u001a\u00020\bJ\u0006\u0010!\u001a\u00020\bJ\u0006\u0010\"\u001a\u00020\bJ\u000e\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020\fR\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001f\u0010\u0015\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u001f\u0010\u001a\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00070\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/atolow/miixs/ui/profile/viewmodel/ProfileViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_dashboard", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/atolow/miixs/data/model/user/DashboardResponseDto;", "_deactivateResult", "Lkotlin/Result;", "", "_error", "", "_isLoading", "", "_logoutResult", "Lcom/atolow/miixs/data/model/auth/LogoutResponseDto;", "authRepository", "Lcom/atolow/miixs/data/repository/AuthRepository;", "dashboard", "Lkotlinx/coroutines/flow/StateFlow;", "getDashboard", "()Lkotlinx/coroutines/flow/StateFlow;", "deactivateResult", "getDeactivateResult", "error", "getError", "isLoading", "logoutResult", "getLogoutResult", "repository", "Lcom/atolow/miixs/data/repository/UserRepository;", "clearDeactivateResult", "clearLogoutResult", "deactivate", "loadDashboard", "logout", "updateBlockNewChats", "blockNewChats", "app_release"})
public final class ProfileViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.UserRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.atolow.miixs.data.model.user.DashboardResponseDto> _dashboard = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.atolow.miixs.data.model.user.DashboardResponseDto> dashboard = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> error = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<kotlin.Result<com.atolow.miixs.data.model.auth.LogoutResponseDto>> _logoutResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.auth.LogoutResponseDto>> logoutResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<kotlin.Result<kotlin.Unit>> _deactivateResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<kotlin.Result<kotlin.Unit>> deactivateResult = null;
    
    public ProfileViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.atolow.miixs.data.model.user.DashboardResponseDto> getDashboard() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.auth.LogoutResponseDto>> getLogoutResult() {
        return null;
    }
    
    public final void loadDashboard() {
    }
    
    public final void logout() {
    }
    
    public final void clearLogoutResult() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<kotlin.Result<kotlin.Unit>> getDeactivateResult() {
        return null;
    }
    
    public final void deactivate() {
    }
    
    public final void clearDeactivateResult() {
    }
    
    public final void updateBlockNewChats(boolean blockNewChats) {
    }
}