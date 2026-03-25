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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/ChatItem;", "", "()V", "Date", "Message", "Lcom/atolow/miixs/ui/chat/adapter/ChatItem$Date;", "Lcom/atolow/miixs/ui/chat/adapter/ChatItem$Message;", "app_release"})
public abstract class ChatItem {
    
    private ChatItem() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/ChatItem$Date;", "Lcom/atolow/miixs/ui/chat/adapter/ChatItem;", "dateString", "", "(Ljava/lang/String;)V", "getDateString", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"})
    public static final class Date extends com.atolow.miixs.ui.chat.adapter.ChatItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String dateString = null;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.atolow.miixs.ui.chat.adapter.ChatItem.Date copy(@org.jetbrains.annotations.NotNull()
        java.lang.String dateString) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
        
        public Date(@org.jetbrains.annotations.NotNull()
        java.lang.String dateString) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDateString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/atolow/miixs/ui/chat/adapter/ChatItem$Message;", "Lcom/atolow/miixs/ui/chat/adapter/ChatItem;", "message", "Lcom/atolow/miixs/data/model/chat/MessageResponseDto;", "(Lcom/atolow/miixs/data/model/chat/MessageResponseDto;)V", "getMessage", "()Lcom/atolow/miixs/data/model/chat/MessageResponseDto;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"})
    public static final class Message extends com.atolow.miixs.ui.chat.adapter.ChatItem {
        @org.jetbrains.annotations.NotNull()
        private final com.atolow.miixs.data.model.chat.MessageResponseDto message = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.atolow.miixs.data.model.chat.MessageResponseDto component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.atolow.miixs.ui.chat.adapter.ChatItem.Message copy(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.chat.MessageResponseDto message) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
        
        public Message(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.chat.MessageResponseDto message) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.atolow.miixs.data.model.chat.MessageResponseDto getMessage() {
            return null;
        }
    }
}