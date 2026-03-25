package com.atolow.miixs.ui.chat.viewmodel;

import androidx.lifecycle.ViewModel;
import com.atolow.miixs.data.local.TokenManager;
import com.atolow.miixs.data.model.chat.ChatRoomResponseDto;
import com.atolow.miixs.data.model.chat.MessageResponseDto;
import com.atolow.miixs.data.model.user.ProfileResponseDto;
import com.atolow.miixs.data.repository.BlockRepository;
import com.atolow.miixs.data.repository.ChatRepository;
import com.atolow.miixs.data.repository.UserRepository;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u000eJ\u0010\u0010\'\u001a\u00020%2\u0006\u0010(\u001a\u00020)H\u0002J\u0006\u0010*\u001a\u00020)J(\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020)2\u0018\u0010-\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0/\u0012\u0004\u0012\u00020%0.J\u000e\u00100\u001a\u00020%2\u0006\u0010,\u001a\u00020)J\u000e\u00101\u001a\u00020%2\u0006\u0010,\u001a\u00020)J\u0010\u00102\u001a\u00020%2\u0006\u0010(\u001a\u00020)H\u0002J\u000e\u00103\u001a\u00020%2\u0006\u0010,\u001a\u00020)J0\u00104\u001a\u00020%2\u0006\u0010,\u001a\u00020)2\u0006\u00105\u001a\u00020\u00072\u0018\u0010-\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0/\u0012\u0004\u0012\u00020%0.J\u0016\u00106\u001a\u00020%2\u0006\u0010,\u001a\u00020)2\u0006\u00107\u001a\u00020\u0007J\u0018\u00108\u001a\u00020%2\u0006\u0010,\u001a\u00020)2\u0006\u00107\u001a\u00020\u0007H\u0002J\"\u00109\u001a\u00020%2\u0006\u0010,\u001a\u00020)2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020%0.R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0019\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018R\u000e\u0010\"\u001a\u00020#X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006:"}, d2 = {"Lcom/atolow/miixs/ui/chat/viewmodel/ChatRoomViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_chatRoomInfo", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/atolow/miixs/data/model/chat/ChatRoomResponseDto;", "_error", "", "_isFirstMessage", "", "_isLoading", "_isOtherUserLeft", "_messages", "", "Lcom/atolow/miixs/data/model/chat/MessageResponseDto;", "_otherUserProfile", "Lcom/atolow/miixs/data/model/user/ProfileResponseDto;", "blockRepository", "Lcom/atolow/miixs/data/repository/BlockRepository;", "chatRepository", "Lcom/atolow/miixs/data/repository/ChatRepository;", "chatRoomInfo", "Lkotlinx/coroutines/flow/StateFlow;", "getChatRoomInfo", "()Lkotlinx/coroutines/flow/StateFlow;", "error", "getError", "isFirstMessage", "isLoading", "isOtherUserLeft", "messages", "getMessages", "otherUserProfile", "getOtherUserProfile", "userRepository", "Lcom/atolow/miixs/data/repository/UserRepository;", "addMessage", "", "message", "checkBlockRelationship", "otherUserId", "", "getCurrentUserId", "leaveChatRoom", "chatRoomId", "onResult", "Lkotlin/Function1;", "Lkotlin/Result;", "loadChatRoomInfo", "loadMessages", "loadOtherUserProfile", "markAllMessagesAsRead", "sendImageMessage", "imagePath", "sendMessage", "content", "sendMessageInternal", "toggleFavorite", "app_release"})
public final class ChatRoomViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.ChatRepository chatRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.BlockRepository blockRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.atolow.miixs.data.model.chat.MessageResponseDto>> _messages = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.atolow.miixs.data.model.chat.MessageResponseDto>> messages = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.atolow.miixs.data.model.user.ProfileResponseDto> _otherUserProfile = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.atolow.miixs.data.model.user.ProfileResponseDto> otherUserProfile = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.atolow.miixs.data.model.chat.ChatRoomResponseDto> _chatRoomInfo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.atolow.miixs.data.model.chat.ChatRoomResponseDto> chatRoomInfo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isFirstMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isFirstMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isOtherUserLeft = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isOtherUserLeft = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> error = null;
    
    public ChatRoomViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.atolow.miixs.data.model.chat.MessageResponseDto>> getMessages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.atolow.miixs.data.model.user.ProfileResponseDto> getOtherUserProfile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.atolow.miixs.data.model.chat.ChatRoomResponseDto> getChatRoomInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isFirstMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isOtherUserLeft() {
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
    
    public final long getCurrentUserId() {
        return 0L;
    }
    
    public final void loadChatRoomInfo(long chatRoomId) {
    }
    
    private final void checkBlockRelationship(long otherUserId) {
    }
    
    public final void toggleFavorite(long chatRoomId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onResult) {
    }
    
    public final void markAllMessagesAsRead(long chatRoomId) {
    }
    
    private final void loadOtherUserProfile(long otherUserId) {
    }
    
    public final void loadMessages(long chatRoomId) {
    }
    
    public final void sendMessage(long chatRoomId, @org.jetbrains.annotations.NotNull()
    java.lang.String content) {
    }
    
    private final void sendMessageInternal(long chatRoomId, java.lang.String content) {
    }
    
    public final void addMessage(@org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.chat.MessageResponseDto message) {
    }
    
    public final void leaveChatRoom(long chatRoomId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlin.Result<kotlin.Unit>, kotlin.Unit> onResult) {
    }
    
    public final void sendImageMessage(long chatRoomId, @org.jetbrains.annotations.NotNull()
    java.lang.String imagePath, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlin.Result<com.atolow.miixs.data.model.chat.MessageResponseDto>, kotlin.Unit> onResult) {
    }
}