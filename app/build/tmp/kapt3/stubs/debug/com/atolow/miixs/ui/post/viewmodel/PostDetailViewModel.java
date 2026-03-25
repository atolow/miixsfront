package com.atolow.miixs.ui.post.viewmodel;

import androidx.lifecycle.ViewModel;
import com.atolow.miixs.data.model.chat.CreateChatRoomResponseDto;
import com.atolow.miixs.data.model.post.PostResponseDto;
import com.atolow.miixs.data.repository.ChatRepository;
import com.atolow.miixs.data.repository.PostRepository;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u001dJ\u000e\u0010 \u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u001dR\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010\u000f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00050\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0019\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/atolow/miixs/ui/post/viewmodel/PostDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_createChatRoomResult", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlin/Result;", "Lcom/atolow/miixs/data/model/chat/CreateChatRoomResponseDto;", "_error", "", "_isLoading", "", "_post", "Lcom/atolow/miixs/data/model/post/PostResponseDto;", "chatRepository", "Lcom/atolow/miixs/data/repository/ChatRepository;", "createChatRoomResult", "Lkotlinx/coroutines/flow/StateFlow;", "getCreateChatRoomResult", "()Lkotlinx/coroutines/flow/StateFlow;", "error", "getError", "isLoading", "post", "getPost", "postRepository", "Lcom/atolow/miixs/data/repository/PostRepository;", "createChatRoom", "", "otherUserId", "", "deletePost", "postId", "loadPost", "app_debug"})
public final class PostDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.PostRepository postRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.ChatRepository chatRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.atolow.miixs.data.model.post.PostResponseDto> _post = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.atolow.miixs.data.model.post.PostResponseDto> post = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> error = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<kotlin.Result<com.atolow.miixs.data.model.chat.CreateChatRoomResponseDto>> _createChatRoomResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.chat.CreateChatRoomResponseDto>> createChatRoomResult = null;
    
    public PostDetailViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.atolow.miixs.data.model.post.PostResponseDto> getPost() {
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
    public final kotlinx.coroutines.flow.StateFlow<kotlin.Result<com.atolow.miixs.data.model.chat.CreateChatRoomResponseDto>> getCreateChatRoomResult() {
        return null;
    }
    
    public final void loadPost(long postId) {
    }
    
    public final void deletePost(long postId) {
    }
    
    public final void createChatRoom(long otherUserId) {
    }
}