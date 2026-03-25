package com.atolow.miixs.ui.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import android.widget.PopupMenu;
import com.atolow.miixs.R;
import com.atolow.miixs.data.local.TokenManager;
import com.atolow.miixs.data.model.chat.MessageResponseDto;
import com.atolow.miixs.data.repository.BlockRepository;
import com.atolow.miixs.data.repository.PostRepository;
import com.atolow.miixs.data.repository.ReportRepository;
import com.atolow.miixs.data.websocket.WebSocketManager;
import com.atolow.miixs.databinding.ActivityChatRoomBinding;
import com.atolow.miixs.ui.auth.LoginActivity;
import com.atolow.miixs.ui.chat.adapter.MessageAdapter;
import com.atolow.miixs.ui.chat.viewmodel.ChatRoomViewModel;
import com.atolow.miixs.util.ChatNotificationManager;
import com.atolow.miixs.util.NotificationHelper;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 A2\u00020\u0001:\u0001AB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\bH\u0002J\b\u0010!\u001a\u00020\u001fH\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002J\"\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0014J\u0012\u0010)\u001a\u00020\u001f2\b\u0010*\u001a\u0004\u0018\u00010+H\u0014J\b\u0010,\u001a\u00020\u001fH\u0014J\b\u0010-\u001a\u00020\u001fH\u0014J\b\u0010.\u001a\u00020\u001fH\u0014J\u0010\u0010/\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\bH\u0002J\b\u00100\u001a\u00020\u001fH\u0002J\b\u00101\u001a\u00020\u001fH\u0002J\b\u00102\u001a\u00020\u001fH\u0002J\b\u00103\u001a\u00020\u001fH\u0002J\b\u00104\u001a\u00020\u001fH\u0002J\b\u00105\u001a\u00020\u001fH\u0002J\b\u00106\u001a\u00020\u001fH\u0002J\b\u00107\u001a\u00020\u001fH\u0002J\u0010\u00108\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\bH\u0002J\b\u00109\u001a\u00020\u001fH\u0002J\u0010\u0010:\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020<H\u0002J\u0010\u0010=\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\bH\u0002J\b\u0010>\u001a\u00020\u001fH\u0002J\u0010\u0010?\u001a\u00020\u001f2\u0006\u0010@\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006B"}, d2 = {"Lcom/atolow/miixs/ui/chat/ChatRoomActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/atolow/miixs/databinding/ActivityChatRoomBinding;", "blockRepository", "Lcom/atolow/miixs/data/repository/BlockRepository;", "chatRoomId", "", "messageAdapter", "Lcom/atolow/miixs/ui/chat/adapter/MessageAdapter;", "otherUserId", "Ljava/lang/Long;", "otherUserName", "", "postRepository", "Lcom/atolow/miixs/data/repository/PostRepository;", "previousFavoriteState", "", "Ljava/lang/Boolean;", "reportRepository", "Lcom/atolow/miixs/data/repository/ReportRepository;", "viewModel", "Lcom/atolow/miixs/ui/chat/viewmodel/ChatRoomViewModel;", "getViewModel", "()Lcom/atolow/miixs/ui/chat/viewmodel/ChatRoomViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "webSocketManager", "Lcom/atolow/miixs/data/websocket/WebSocketManager;", "blockUser", "", "userId", "connectWebSocket", "leaveChatRoom", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "reportUser", "scrollToBottom", "sendMessage", "setupClickListeners", "setupObservers", "setupRecyclerView", "setupSwipeRefresh", "setupToolbar", "setupWindowInsets", "showBlockDialog", "showLeaveChatRoomDialog", "showMoreMenu", "anchor", "Landroid/view/View;", "showReportDialog", "toggleFavorite", "updateFavoriteHeart", "isFavorite", "Companion", "app_debug"})
public final class ChatRoomActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.atolow.miixs.databinding.ActivityChatRoomBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.atolow.miixs.ui.chat.adapter.MessageAdapter messageAdapter;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.websocket.WebSocketManager webSocketManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.BlockRepository blockRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.ReportRepository reportRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.repository.PostRepository postRepository = null;
    private long chatRoomId = -1L;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String otherUserName;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long otherUserId;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Boolean previousFavoriteState;
    public static final int REQUEST_CODE_IMAGE_UPLOAD = 1001;
    @org.jetbrains.annotations.NotNull()
    public static final com.atolow.miixs.ui.chat.ChatRoomActivity.Companion Companion = null;
    
    public ChatRoomActivity() {
        super();
    }
    
    private final com.atolow.miixs.ui.chat.viewmodel.ChatRoomViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void setupSwipeRefresh() {
    }
    
    private final void setupObservers() {
    }
    
    private final void setupWindowInsets() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void updateFavoriteHeart(boolean isFavorite) {
    }
    
    private final void toggleFavorite() {
    }
    
    private final void showMoreMenu(android.view.View anchor) {
    }
    
    private final void showBlockDialog(long userId) {
    }
    
    private final void blockUser(long userId) {
    }
    
    private final void showReportDialog(long userId) {
    }
    
    private final void reportUser(long userId) {
    }
    
    private final void sendMessage() {
    }
    
    private final void scrollToBottom() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void showLeaveChatRoomDialog() {
    }
    
    private final void leaveChatRoom() {
    }
    
    private final void connectWebSocket() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/atolow/miixs/ui/chat/ChatRoomActivity$Companion;", "", "()V", "REQUEST_CODE_IMAGE_UPLOAD", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}