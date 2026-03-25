package com.atolow.miixs.ui.chat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.atolow.miixs.R;
import com.atolow.miixs.data.model.chat.ChatRoomResponseDto;
import com.atolow.miixs.databinding.ItemChatRoomFavoriteBinding;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0011\u0012B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\tH\u0016J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/FavoriteChatRoomAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/atolow/miixs/data/model/chat/ChatRoomResponseDto;", "Lcom/atolow/miixs/ui/chat/adapter/FavoriteChatRoomAdapter$FavoriteChatRoomViewHolder;", "onItemClick", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ChatRoomDiffCallback", "FavoriteChatRoomViewHolder", "app_release"})
public final class FavoriteChatRoomAdapter extends androidx.recyclerview.widget.ListAdapter<com.atolow.miixs.data.model.chat.ChatRoomResponseDto, com.atolow.miixs.ui.chat.adapter.FavoriteChatRoomAdapter.FavoriteChatRoomViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.atolow.miixs.data.model.chat.ChatRoomResponseDto, kotlin.Unit> onItemClick = null;
    
    public FavoriteChatRoomAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.atolow.miixs.data.model.chat.ChatRoomResponseDto, kotlin.Unit> onItemClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.atolow.miixs.ui.chat.adapter.FavoriteChatRoomAdapter.FavoriteChatRoomViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.atolow.miixs.ui.chat.adapter.FavoriteChatRoomAdapter.FavoriteChatRoomViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/FavoriteChatRoomAdapter$ChatRoomDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/atolow/miixs/data/model/chat/ChatRoomResponseDto;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"})
    public static final class ChatRoomDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.atolow.miixs.data.model.chat.ChatRoomResponseDto> {
        
        public ChatRoomDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.chat.ChatRoomResponseDto oldItem, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.chat.ChatRoomResponseDto newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.chat.ChatRoomResponseDto oldItem, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.chat.ChatRoomResponseDto newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001c\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/FavoriteChatRoomAdapter$FavoriteChatRoomViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/atolow/miixs/databinding/ItemChatRoomFavoriteBinding;", "onItemClick", "Lkotlin/Function1;", "Lcom/atolow/miixs/data/model/chat/ChatRoomResponseDto;", "", "(Lcom/atolow/miixs/databinding/ItemChatRoomFavoriteBinding;Lkotlin/jvm/functions/Function1;)V", "bind", "chatRoom", "isImageUrl", "", "text", "", "loadProfileImage", "imageUrl", "isDeactivated", "app_release"})
    public static final class FavoriteChatRoomViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.atolow.miixs.databinding.ItemChatRoomFavoriteBinding binding = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.atolow.miixs.data.model.chat.ChatRoomResponseDto, kotlin.Unit> onItemClick = null;
        
        public FavoriteChatRoomViewHolder(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.databinding.ItemChatRoomFavoriteBinding binding, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.atolow.miixs.data.model.chat.ChatRoomResponseDto, kotlin.Unit> onItemClick) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.chat.ChatRoomResponseDto chatRoom) {
        }
        
        private final boolean isImageUrl(java.lang.String text) {
            return false;
        }
        
        private final void loadProfileImage(java.lang.String imageUrl, boolean isDeactivated) {
        }
    }
}