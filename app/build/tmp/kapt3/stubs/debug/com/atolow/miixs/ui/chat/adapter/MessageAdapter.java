package com.atolow.miixs.ui.chat.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.atolow.miixs.R;
import com.atolow.miixs.data.model.chat.MessageResponseDto;
import com.atolow.miixs.databinding.ItemMessageSentBinding;
import com.atolow.miixs.databinding.ItemMessageReceivedBinding;
import com.atolow.miixs.databinding.ItemMessageDateBinding;
import com.atolow.miixs.databinding.ItemMessageSystemBinding;
import java.text.SimpleDateFormat;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000  2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0006\u001f !\"#$B%\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\tJ\u0012\u0010\n\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007H\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007J\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\rH\u0016J\u0014\u0010\u0018\u001a\u00020\u00122\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aJ\u001a\u0010\u001c\u001a\u00020\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/MessageAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/atolow/miixs/ui/chat/adapter/ChatItem;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "currentUserId", "", "otherUserProfileImageUrl", "", "otherUserName", "(JLjava/lang/String;Ljava/lang/String;)V", "formatDate", "dateString", "getItemViewType", "", "position", "getOtherUserName", "getOtherUserProfileImageUrl", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submitMessages", "messages", "", "Lcom/atolow/miixs/data/model/chat/MessageResponseDto;", "updateOtherUserProfile", "profileImageUrl", "userName", "ChatItemDiffCallback", "Companion", "DateViewHolder", "ReceivedMessageViewHolder", "SentMessageViewHolder", "SystemMessageViewHolder", "app_debug"})
public final class MessageAdapter extends androidx.recyclerview.widget.ListAdapter<com.atolow.miixs.ui.chat.adapter.ChatItem, androidx.recyclerview.widget.RecyclerView.ViewHolder> {
    private final long currentUserId = 0L;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String otherUserProfileImageUrl;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String otherUserName;
    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;
    private static final int VIEW_TYPE_DATE = 3;
    private static final int VIEW_TYPE_SYSTEM = 4;
    @org.jetbrains.annotations.NotNull()
    public static final com.atolow.miixs.ui.chat.adapter.MessageAdapter.Companion Companion = null;
    
    public MessageAdapter(long currentUserId, @org.jetbrains.annotations.Nullable()
    java.lang.String otherUserProfileImageUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String otherUserName) {
        super(null);
    }
    
    public final void updateOtherUserProfile(@org.jetbrains.annotations.Nullable()
    java.lang.String profileImageUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String userName) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getOtherUserProfileImageUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getOtherUserName() {
        return null;
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
    
    public final void submitMessages(@org.jetbrains.annotations.NotNull()
    java.util.List<com.atolow.miixs.data.model.chat.MessageResponseDto> messages) {
    }
    
    private final java.lang.String formatDate(java.lang.String dateString) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/MessageAdapter$ChatItemDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/atolow/miixs/ui/chat/adapter/ChatItem;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class ChatItemDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.atolow.miixs.ui.chat.adapter.ChatItem> {
        
        public ChatItemDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.ui.chat.adapter.ChatItem oldItem, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.ui.chat.adapter.ChatItem newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.ui.chat.adapter.ChatItem oldItem, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.ui.chat.adapter.ChatItem newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/MessageAdapter$Companion;", "", "()V", "VIEW_TYPE_DATE", "", "VIEW_TYPE_RECEIVED", "VIEW_TYPE_SENT", "VIEW_TYPE_SYSTEM", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/MessageAdapter$DateViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/atolow/miixs/databinding/ItemMessageDateBinding;", "(Lcom/atolow/miixs/databinding/ItemMessageDateBinding;)V", "bind", "", "dateString", "", "app_debug"})
    public static final class DateViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.atolow.miixs.databinding.ItemMessageDateBinding binding = null;
        
        public DateViewHolder(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.databinding.ItemMessageDateBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        java.lang.String dateString) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/MessageAdapter$ReceivedMessageViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/atolow/miixs/databinding/ItemMessageReceivedBinding;", "adapter", "Lcom/atolow/miixs/ui/chat/adapter/MessageAdapter;", "(Lcom/atolow/miixs/databinding/ItemMessageReceivedBinding;Lcom/atolow/miixs/ui/chat/adapter/MessageAdapter;)V", "bind", "", "message", "Lcom/atolow/miixs/data/model/chat/MessageResponseDto;", "formatTime", "", "timeString", "app_debug"})
    public static final class ReceivedMessageViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.atolow.miixs.databinding.ItemMessageReceivedBinding binding = null;
        @org.jetbrains.annotations.NotNull()
        private final com.atolow.miixs.ui.chat.adapter.MessageAdapter adapter = null;
        
        public ReceivedMessageViewHolder(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.databinding.ItemMessageReceivedBinding binding, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.ui.chat.adapter.MessageAdapter adapter) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.chat.MessageResponseDto message) {
        }
        
        private final java.lang.String formatTime(java.lang.String timeString) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/MessageAdapter$SentMessageViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/atolow/miixs/databinding/ItemMessageSentBinding;", "(Lcom/atolow/miixs/databinding/ItemMessageSentBinding;)V", "bind", "", "message", "Lcom/atolow/miixs/data/model/chat/MessageResponseDto;", "formatTime", "", "timeString", "app_debug"})
    public static final class SentMessageViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.atolow.miixs.databinding.ItemMessageSentBinding binding = null;
        
        public SentMessageViewHolder(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.databinding.ItemMessageSentBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.chat.MessageResponseDto message) {
        }
        
        private final java.lang.String formatTime(java.lang.String timeString) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/MessageAdapter$SystemMessageViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/atolow/miixs/databinding/ItemMessageSystemBinding;", "(Lcom/atolow/miixs/databinding/ItemMessageSystemBinding;)V", "bind", "", "message", "Lcom/atolow/miixs/data/model/chat/MessageResponseDto;", "app_debug"})
    public static final class SystemMessageViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.atolow.miixs.databinding.ItemMessageSystemBinding binding = null;
        
        public SystemMessageViewHolder(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.databinding.ItemMessageSystemBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.chat.MessageResponseDto message) {
        }
    }
}