package com.atolow.miixs.ui.post.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.atolow.miixs.R;
import com.atolow.miixs.data.model.post.PostResponseDto;
import com.atolow.miixs.databinding.ItemPostBinding;
import com.atolow.miixs.ui.post.PostDetailActivity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0010\u0011B\u001d\u0012\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/atolow/miixs/ui/post/adapter/PostListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/atolow/miixs/data/model/post/PostResponseDto;", "Lcom/atolow/miixs/ui/post/adapter/PostListAdapter$PostViewHolder;", "onItemClick", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "PostDiffCallback", "PostViewHolder", "app_release"})
public final class PostListAdapter extends androidx.recyclerview.widget.ListAdapter<com.atolow.miixs.data.model.post.PostResponseDto, com.atolow.miixs.ui.post.adapter.PostListAdapter.PostViewHolder> {
    @org.jetbrains.annotations.Nullable()
    private final kotlin.jvm.functions.Function1<com.atolow.miixs.data.model.post.PostResponseDto, kotlin.Unit> onItemClick = null;
    
    public PostListAdapter(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super com.atolow.miixs.data.model.post.PostResponseDto, kotlin.Unit> onItemClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.atolow.miixs.ui.post.adapter.PostListAdapter.PostViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.atolow.miixs.ui.post.adapter.PostListAdapter.PostViewHolder holder, int position) {
    }
    
    public PostListAdapter() {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/atolow/miixs/ui/post/adapter/PostListAdapter$PostDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/atolow/miixs/data/model/post/PostResponseDto;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"})
    public static final class PostDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.atolow.miixs.data.model.post.PostResponseDto> {
        
        public PostDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.post.PostResponseDto oldItem, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.post.PostResponseDto newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.post.PostResponseDto oldItem, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.post.PostResponseDto newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0006J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/atolow/miixs/ui/post/adapter/PostListAdapter$PostViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/atolow/miixs/databinding/ItemPostBinding;", "onItemClick", "Lkotlin/Function1;", "Lcom/atolow/miixs/data/model/post/PostResponseDto;", "", "(Lcom/atolow/miixs/databinding/ItemPostBinding;Lkotlin/jvm/functions/Function1;)V", "bind", "post", "formatTimeAgo", "", "dateTime", "app_release"})
    public static final class PostViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.atolow.miixs.databinding.ItemPostBinding binding = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function1<com.atolow.miixs.data.model.post.PostResponseDto, kotlin.Unit> onItemClick = null;
        
        public PostViewHolder(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.databinding.ItemPostBinding binding, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super com.atolow.miixs.data.model.post.PostResponseDto, kotlin.Unit> onItemClick) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.post.PostResponseDto post) {
        }
        
        private final java.lang.String formatTimeAgo(java.lang.String dateTime) {
            return null;
        }
    }
}