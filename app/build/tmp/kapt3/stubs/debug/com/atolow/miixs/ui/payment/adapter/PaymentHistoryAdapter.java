package com.atolow.miixs.ui.payment.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.atolow.miixs.R;
import com.atolow.miixs.data.model.payment.PaymentResponseDto;
import com.atolow.miixs.databinding.ItemPaymentHistoryBinding;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u000e\u000fB\u0005\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016\u00a8\u0006\u0010"}, d2 = {"Lcom/atolow/miixs/ui/payment/adapter/PaymentHistoryAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/atolow/miixs/data/model/payment/PaymentResponseDto;", "Lcom/atolow/miixs/ui/payment/adapter/PaymentHistoryAdapter$PaymentHistoryViewHolder;", "()V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "PaymentHistoryDiffCallback", "PaymentHistoryViewHolder", "app_debug"})
public final class PaymentHistoryAdapter extends androidx.recyclerview.widget.ListAdapter<com.atolow.miixs.data.model.payment.PaymentResponseDto, com.atolow.miixs.ui.payment.adapter.PaymentHistoryAdapter.PaymentHistoryViewHolder> {
    
    public PaymentHistoryAdapter() {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.atolow.miixs.ui.payment.adapter.PaymentHistoryAdapter.PaymentHistoryViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.atolow.miixs.ui.payment.adapter.PaymentHistoryAdapter.PaymentHistoryViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/atolow/miixs/ui/payment/adapter/PaymentHistoryAdapter$PaymentHistoryDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/atolow/miixs/data/model/payment/PaymentResponseDto;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class PaymentHistoryDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.atolow.miixs.data.model.payment.PaymentResponseDto> {
        
        public PaymentHistoryDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.payment.PaymentResponseDto oldItem, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.payment.PaymentResponseDto newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.payment.PaymentResponseDto oldItem, @org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.payment.PaymentResponseDto newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/atolow/miixs/ui/payment/adapter/PaymentHistoryAdapter$PaymentHistoryViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/atolow/miixs/databinding/ItemPaymentHistoryBinding;", "(Lcom/atolow/miixs/databinding/ItemPaymentHistoryBinding;)V", "bind", "", "payment", "Lcom/atolow/miixs/data/model/payment/PaymentResponseDto;", "formatDate", "", "dateString", "formatNumber", "number", "", "app_debug"})
    public static final class PaymentHistoryViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.atolow.miixs.databinding.ItemPaymentHistoryBinding binding = null;
        
        public PaymentHistoryViewHolder(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.databinding.ItemPaymentHistoryBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.atolow.miixs.data.model.payment.PaymentResponseDto payment) {
        }
        
        private final java.lang.String formatNumber(long number) {
            return null;
        }
        
        private final java.lang.String formatDate(java.lang.String dateString) {
            return null;
        }
    }
}