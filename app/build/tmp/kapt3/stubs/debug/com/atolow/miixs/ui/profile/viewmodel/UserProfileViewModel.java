package com.atolow.miixs.ui.profile.viewmodel;

import androidx.lifecycle.ViewModel;
import com.atolow.miixs.data.model.block.BlockResponseDto;
import com.atolow.miixs.data.model.chat.CreateChatRoomResponseDto;
import com.atolow.miixs.data.model.post.PostResponseDto;
import com.atolow.miixs.data.model.report.ReportResponseDto;
import com.atolow.miixs.data.model.user.ProfileResponseDto;
import com.atolow.miixs.data.repository.BlockRepository;
import com.atolow.miixs.data.repository.ChatRepository;
import com.atolow.miixs.data.repository.PostRepository;
import com.atolow.miixs.data.repository.ReportRepository;
import com.atolow.miixs.data.repository.UserRepository;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010(\u001a\u00020)J\u0006\u0010*\u001a\u00020)J\u0006\u0010+\u001a\u00020)J\u0006\u0010,\u001a\u00020)J\u0006\u0010-\u001a\u00020)J\u000e\u0010.\u001a\u00020)2\u0006\u0010/\u001a\u000200J\u000e\u00101\u001a\u00020)2\u0006\u00102\u001a\u000200J\u000e\u00103\u001a\u00020)2\u0006\u00104\u001a\u00020\nR\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010\u0013\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00050\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010\u0019\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00050\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0019\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u000e\u0010\"\u001a\u00020#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010$\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00050\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0016R\u000e\u0010&\u001a\u00020\'X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lcom/atolow/miixs/ui/profile/viewmodel/UserProfileViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_blockResult", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlin/Result;", "Lcom/atolow/miixs/data/model/block/BlockResponseDto;", "_createChatRoomResult", "Lcom/atolow/miixs/data/model/chat/CreateChatRoomResponseDto;", "_error", "", "_isLoading", "", "_profile", "Lcom/atolow/miixs/data/model/user/ProfileResponseDto;", "_reportResult", "Lcom/atolow/miixs/data/model/report/ReportResponseDto;", "blockRepository", "Lcom/atolow/miixs/data/repository/BlockRepository;", "blockResult", "Lkotlinx/coroutines/flow/StateFlow;", "getBlockResult", "()Lkotlinx/coroutines/flow/StateFlow;", "chatRepository", "Lcom/atolow/miixs/data/repository/ChatRepository;", "createChatRoomResult", "getCreateChatRoomResult", "error", "getError", "isLoading", "postRepository", "Lcom/atolow/miixs/data/repository/PostRepository;", "profile", "getProfile", "reportRepository", "Lcom/atolow/miixs/data/repository/ReportRepository;", "reportResult", "getReportResult", "userRepository", "Lcom/atolow/miixs/data/repository/UserRepository;", "blockUser", "", "clearBlockResult", "clearCreateChatRoomResult", "clearError", "clearReportResult", "createChatRoom", "otherUserId", "", "loadUserProfile", "userId", "reportUser", "reason", "app_debug"})
public final class UserProfileViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.BlockRepository blockRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.ReportRepository reportRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.PostRepository postRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.ChatRepository chatRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.atolow.miixs.data.model.user.ProfileResponseDto> _profile = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.atolow.miixs.data.model.user.ProfileResponseDto> profile = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> error = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<kotlin.Result<com.atolow.miixs.data.model.block.BlockResponseDto>> _blockResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.block.BlockResponseDto>> blockResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<kotlin.Result<com.atolow.miixs.data.model.report.ReportResponseDto>> _reportResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.report.ReportResponseDto>> reportResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<kotlin.Result<com.atolow.miixs.data.model.chat.CreateChatRoomResponseDto>> _createChatRoomResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.chat.CreateChatRoomResponseDto>> createChatRoomResult = null;
    
    public UserProfileViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.atolow.miixs.data.model.user.ProfileResponseDto> getProfile() {
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
    public final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.block.BlockResponseDto>> getBlockResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.report.ReportResponseDto>> getReportResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.chat.CreateChatRoomResponseDto>> getCreateChatRoomResult() {
        return null;
    }
    
    public final void loadUserProfile(long userId) {
    }
    
    public final void blockUser() {
    }
    
    public final void reportUser(@org.jetbrains.annotations.NotNull()
    java.lang.String reason) {
    }
    
    public final void clearBlockResult() {
    }
    
    public final void clearReportResult() {
    }
    
    public final void createChatRoom(long otherUserId) {
    }
    
    public final void clearCreateChatRoomResult() {
    }
    
    public final void clearError() {
    }
}