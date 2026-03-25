package com.atolow.miixs.ui.chat.viewmodel;

import androidx.lifecycle.ViewModel;
import com.atolow.miixs.data.model.chat.ChatRoomResponseDto;
import com.atolow.miixs.data.repository.ChatRepository;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010\u0013\u001a\u00020\u00142\u0018\u0010\u0015\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u0017\u0012\u0004\u0012\u00020\u00140\u0016J\u0006\u0010\u0019\u001a\u00020\u0014J\u0006\u0010\u001a\u001a\u00020\u0014R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/atolow/miixs/ui/chat/viewmodel/ChatListViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_chatRooms", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/atolow/miixs/data/model/chat/ChatRoomResponseDto;", "_favorites", "_isLoading", "", "chatRooms", "Lkotlinx/coroutines/flow/StateFlow;", "getChatRooms", "()Lkotlinx/coroutines/flow/StateFlow;", "favorites", "getFavorites", "isLoading", "repository", "Lcom/atolow/miixs/data/repository/ChatRepository;", "leaveAllChatRoomsExceptFavorites", "", "onResult", "Lkotlin/Function1;", "Lkotlin/Result;", "", "loadChatRooms", "loadFavorites", "app_release"})
public final class ChatListViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.ChatRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.atolow.miixs.data.model.chat.ChatRoomResponseDto>> _favorites = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.atolow.miixs.data.model.chat.ChatRoomResponseDto>> favorites = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.atolow.miixs.data.model.chat.ChatRoomResponseDto>> _chatRooms = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.atolow.miixs.data.model.chat.ChatRoomResponseDto>> chatRooms = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    
    public ChatListViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.atolow.miixs.data.model.chat.ChatRoomResponseDto>> getFavorites() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.atolow.miixs.data.model.chat.ChatRoomResponseDto>> getChatRooms() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    public final void loadFavorites() {
    }
    
    public final void loadChatRooms() {
    }
    
    public final void leaveAllChatRoomsExceptFavorites(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlin.Result<java.lang.Integer>, kotlin.Unit> onResult) {
    }
}