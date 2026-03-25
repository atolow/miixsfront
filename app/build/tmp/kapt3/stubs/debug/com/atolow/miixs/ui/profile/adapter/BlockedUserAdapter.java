package com.atolow.miixs.ui.profile.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.atolow.miixs.R;
import com.atolow.miixs.data.model.user.BlockedUserDto;
import com.atolow.miixs.databinding.ItemBlockedUserBinding;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0015\u0016B+\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\b\u00a2\u0006\u0002\u0010\tJ\u0006\u0010\f\u001a\u00020\u0006J\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0016J\u000e\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u000bR\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/atolow/miixs/ui/profile/adapter/BlockedUserAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/atolow/miixs/data/model/user/BlockedUserDto;", "Lcom/atolow/miixs/ui/profile/adapter/BlockedUserAdapter$ViewHolder;", "onUnblockClick", "Lkotlin/Function1;", "", "onItemClick", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "selectedPosition", "", "clearSelection", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setSelectedPosition", "DiffCallback", "ViewHolder", "app_debug"})
public final class BlockedUserAdapter extends androidx.recyclerview.widget.ListAdapter<com.atolow.miixs.data.model.user.BlockedUserDto, com.atolow.miixs.ui.profile.adapter.BlockedUserAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.atolow.miixs.data.model.user.BlockedUserDto, kotlin.Unit> onUnblockClick = null;
    @org.jetbrains.annotations.Nullable()
    private final kotlin.jvm.functions.Function0<kotlin.Unit> onItemClick = null;
    private int selectedPosition = -1;
    
    public BlockedUserAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.atolow.miixs.data.model.user.BlockedUserDto, kotlin.Unit> onUnblockClick, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onItemClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.atolow.miixs.ui.profile.adapter.BlockedUserAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.atolow.miixs.ui.profile.adapter.BlockedUserAdapter.ViewHolder holder, int position) {
    }
    
    public final void setSelectedPosition(int position) {
    }
    
    public final void clearSelection() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/atolow/miixs/ui/profile/adapter/BlockedUserAdapter$DiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/atolow/miixs/data/model/user/BlockedUserDto;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class DiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.atolow.miixs.data.model.user.BlockedUserDto> {
        
        public DiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.user.BlockedUserDto oldItem, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.user.BlockedUserDto newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.user.BlockedUserDto oldItem, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.user.BlockedUserDto newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/atolow/miixs/ui/profile/adapter/BlockedUserAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/atolow/miixs/databinding/ItemBlockedUserBinding;", "onUnblockClick", "Lkotlin/Function1;", "Lcom/atolow/miixs/data/model/user/BlockedUserDto;", "", "onItemClick", "Lkotlin/Function0;", "adapter", "Lcom/atolow/miixs/ui/profile/adapter/BlockedUserAdapter;", "(Lcom/atolow/miixs/databinding/ItemBlockedUserBinding;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lcom/atolow/miixs/ui/profile/adapter/BlockedUserAdapter;)V", "bind", "user", "position", "", "app_debug"})
    public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.atolow.miixs.databinding.ItemBlockedUserBinding binding = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.atolow.miixs.data.model.user.BlockedUserDto, kotlin.Unit> onUnblockClick = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onItemClick = null;
        @org.jetbrains.annotations.NotNull()
        private final com.atolow.miixs.ui.profile.adapter.BlockedUserAdapter adapter = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.databinding.ItemBlockedUserBinding binding, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.atolow.miixs.data.model.user.BlockedUserDto, kotlin.Unit> onUnblockClick, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function0<kotlin.Unit> onItemClick, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.ui.profile.adapter.BlockedUserAdapter adapter) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.user.BlockedUserDto user, int position) {
        }
    }
}