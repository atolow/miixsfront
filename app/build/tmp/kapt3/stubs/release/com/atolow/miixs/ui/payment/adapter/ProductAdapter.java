package com.atolow.miixs.ui.payment.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.atolow.miixs.data.model.payment.ProductResponseDto;
import com.atolow.miixs.databinding.ItemProductBinding;
import java.text.NumberFormat;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u0010\u0011\u0012B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/atolow/miixs/ui/payment/adapter/ProductAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/atolow/miixs/data/model/payment/ProductResponseDto;", "Lcom/atolow/miixs/ui/payment/adapter/ProductAdapter$ProductViewHolder;", "onProductClick", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ProductClickListener", "ProductDiffCallback", "ProductViewHolder", "app_release"})
public final class ProductAdapter extends androidx.recyclerview.widget.ListAdapter<com.atolow.miixs.data.model.payment.ProductResponseDto, com.atolow.miixs.ui.payment.adapter.ProductAdapter.ProductViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.atolow.miixs.data.model.payment.ProductResponseDto, kotlin.Unit> onProductClick = null;
    
    public ProductAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.atolow.miixs.data.model.payment.ProductResponseDto, kotlin.Unit> onProductClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.atolow.miixs.ui.payment.adapter.ProductAdapter.ProductViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.atolow.miixs.ui.payment.adapter.ProductAdapter.ProductViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\u0012\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/atolow/miixs/ui/payment/adapter/ProductAdapter$ProductClickListener;", "Landroid/view/View$OnClickListener;", "context", "Landroid/content/Context;", "product", "Lcom/atolow/miixs/data/model/payment/ProductResponseDto;", "onProductClick", "Lkotlin/Function1;", "", "(Landroid/content/Context;Lcom/atolow/miixs/data/model/payment/ProductResponseDto;Lkotlin/jvm/functions/Function1;)V", "onClick", "v", "Landroid/view/View;", "app_release"})
    public static final class ProductClickListener implements android.view.View.OnClickListener {
        @org.jetbrains.annotations.NotNull()
        private final android.content.Context context = null;
        @org.jetbrains.annotations.NotNull()
        private final com.atolow.miixs.data.model.payment.ProductResponseDto product = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.atolow.miixs.data.model.payment.ProductResponseDto, kotlin.Unit> onProductClick = null;
        
        public ProductClickListener(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.payment.ProductResponseDto product, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.atolow.miixs.data.model.payment.ProductResponseDto, kotlin.Unit> onProductClick) {
            super();
        }
        
        @java.lang.Override()
        public void onClick(@org.jetbrains.annotations.Nullable()
        android.view.View v) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/atolow/miixs/ui/payment/adapter/ProductAdapter$ProductDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/atolow/miixs/data/model/payment/ProductResponseDto;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"})
    public static final class ProductDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.atolow.miixs.data.model.payment.ProductResponseDto> {
        
        public ProductDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.payment.ProductResponseDto oldItem, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.payment.ProductResponseDto newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.payment.ProductResponseDto oldItem, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.payment.ProductResponseDto newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/atolow/miixs/ui/payment/adapter/ProductAdapter$ProductViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/atolow/miixs/databinding/ItemProductBinding;", "onProductClick", "Lkotlin/Function1;", "Lcom/atolow/miixs/data/model/payment/ProductResponseDto;", "", "(Lcom/atolow/miixs/databinding/ItemProductBinding;Lkotlin/jvm/functions/Function1;)V", "bind", "product", "formatNumber", "", "number", "", "app_release"})
    public static final class ProductViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.atolow.miixs.databinding.ItemProductBinding binding = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.atolow.miixs.data.model.payment.ProductResponseDto, kotlin.Unit> onProductClick = null;
        
        public ProductViewHolder(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.databinding.ItemProductBinding binding, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.atolow.miixs.data.model.payment.ProductResponseDto, kotlin.Unit> onProductClick) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.payment.ProductResponseDto product) {
        }
        
        private final java.lang.String formatNumber(long number) {
            return null;
        }
    }
}