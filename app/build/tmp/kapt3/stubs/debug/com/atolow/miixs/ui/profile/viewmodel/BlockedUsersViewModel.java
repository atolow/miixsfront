package com.atolow.miixs.ui.profile.viewmodel;

import androidx.lifecycle.ViewModel;
import com.atolow.miixs.data.model.user.BlockedUserDto;
import com.atolow.miixs.data.repository.BlockRepository;
import com.atolow.miixs.data.repository.UserRepository;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0014\u001a\u00020\tJ\u0006\u0010\u0015\u001a\u00020\tJ\u000e\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0018R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001f\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/atolow/miixs/ui/profile/viewmodel/BlockedUsersViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_blockedUsers", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/atolow/miixs/data/model/user/BlockedUserDto;", "_unblockResult", "Lkotlin/Result;", "", "blockRepository", "Lcom/atolow/miixs/data/repository/BlockRepository;", "blockedUsers", "Lkotlinx/coroutines/flow/StateFlow;", "getBlockedUsers", "()Lkotlinx/coroutines/flow/StateFlow;", "unblockResult", "getUnblockResult", "userRepository", "Lcom/atolow/miixs/data/repository/UserRepository;", "clearUnblockResult", "loadBlockedUsers", "unblockUser", "blockedUserId", "", "app_debug"})
public final class BlockedUsersViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.BlockRepository blockRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.atolow.miixs.data.model.user.BlockedUserDto>> _blockedUsers = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.atolow.miixs.data.model.user.BlockedUserDto>> blockedUsers = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<kotlin.Result<kotlin.Unit>> _unblockResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<kotlin.Result<kotlin.Unit>> unblockResult = null;
    
    public BlockedUsersViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.atolow.miixs.data.model.user.BlockedUserDto>> getBlockedUsers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<kotlin.Result<kotlin.Unit>> getUnblockResult() {
        return null;
    }
    
    public final void loadBlockedUsers() {
    }
    
    public final void unblockUser(long blockedUserId) {
    }
    
    public final void clearUnblockResult() {
    }
}