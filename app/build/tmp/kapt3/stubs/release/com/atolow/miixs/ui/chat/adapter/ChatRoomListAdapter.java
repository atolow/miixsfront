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
import com.atolow.miixs.databinding.ItemChatRoomListBinding;
import com.atolow.miixs.databinding.ItemChatSectionHeaderBinding;
import java.text.SimpleDateFormat;
import java.util.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00142\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0004\u0012\u0013\u0014\u0015B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\nH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/ChatRoomListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/atolow/miixs/ui/chat/adapter/ChatListItem;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onItemClick", "Lkotlin/Function1;", "Lcom/atolow/miixs/data/model/chat/ChatRoomResponseDto;", "", "(Lkotlin/jvm/functions/Function1;)V", "getItemViewType", "", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ChatListItemDiffCallback", "ChatRoomListViewHolder", "Companion", "SectionHeaderViewHolder", "app_release"})
public final class ChatRoomListAdapter extends androidx.recyclerview.widget.ListAdapter<com.atolow.miixs.ui.chat.adapter.ChatListItem, androidx.recyclerview.widget.RecyclerView.ViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.atolow.miixs.data.model.chat.ChatRoomResponseDto, kotlin.Unit> onItemClick = null;
    private static final int VIEW_TYPE_SECTION_HEADER = 0;
    private static final int VIEW_TYPE_CHAT_ROOM = 1;
    @org.jetbrains.annotations.NotNull()
    public static final com.atolow.miixs.ui.chat.adapter.ChatRoomListAdapter.Companion Companion = null;
    
    public ChatRoomListAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.atolow.miixs.data.model.chat.ChatRoomResponseDto, kotlin.Unit> onItemClick) {
        super(null);
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/ChatRoomListAdapter$ChatListItemDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/atolow/miixs/ui/chat/adapter/ChatListItem;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"})
    public static final class ChatListItemDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.atolow.miixs.ui.chat.adapter.ChatListItem> {
        
        public ChatListItemDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.ui.chat.adapter.ChatListItem oldItem, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.ui.chat.adapter.ChatListItem newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.ui.chat.adapter.ChatListItem oldItem, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.ui.chat.adapter.ChatListItem newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0006J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0002J\u001c\u0010\u0011\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u0013\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/ChatRoomListAdapter$ChatRoomListViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/atolow/miixs/databinding/ItemChatRoomListBinding;", "onItemClick", "Lkotlin/Function1;", "Lcom/atolow/miixs/data/model/chat/ChatRoomResponseDto;", "", "(Lcom/atolow/miixs/databinding/ItemChatRoomListBinding;Lkotlin/jvm/functions/Function1;)V", "bind", "chatRoom", "formatTimestamp", "", "timestamp", "isImageUrl", "", "text", "loadProfileImage", "imageUrl", "isDeactivated", "app_release"})
    public static final class ChatRoomListViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.atolow.miixs.databinding.ItemChatRoomListBinding binding = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.atolow.miixs.data.model.chat.ChatRoomResponseDto, kotlin.Unit> onItemClick = null;
        
        public ChatRoomListViewHolder(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.databinding.ItemChatRoomListBinding binding, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.atolow.miixs.data.model.chat.ChatRoomResponseDto, kotlin.Unit> onItemClick) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.chat.ChatRoomResponseDto chatRoom) {
        }
        
        private final void loadProfileImage(java.lang.String imageUrl, boolean isDeactivated) {
        }
        
        private final boolean isImageUrl(java.lang.String text) {
            return false;
        }
        
        private final java.lang.String formatTimestamp(java.lang.String timestamp) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/ChatRoomListAdapter$Companion;", "", "()V", "VIEW_TYPE_CHAT_ROOM", "", "VIEW_TYPE_SECTION_HEADER", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/ChatRoomListAdapter$SectionHeaderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/atolow/miixs/databinding/ItemChatSectionHeaderBinding;", "(Lcom/atolow/miixs/databinding/ItemChatSectionHeaderBinding;)V", "bind", "", "title", "", "app_release"})
    public static final class SectionHeaderViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.atolow.miixs.databinding.ItemChatSectionHeaderBinding binding = null;
        
        public SectionHeaderViewHolder(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.databinding.ItemChatSectionHeaderBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        java.lang.String title) {
        }
    }
}