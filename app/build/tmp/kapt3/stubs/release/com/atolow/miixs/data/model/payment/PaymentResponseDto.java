package com.atolow.miixs.data.model.payment;

import com.google.gson.annotations.SerializedName;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b)\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0097\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\u0013J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\t\u0010*\u001a\u00020\u0010H\u00c6\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010#J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\bH\u00c6\u0003J\t\u00101\u001a\u00020\bH\u00c6\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u00aa\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\bH\u00c6\u0001\u00a2\u0006\u0002\u00106J\u0013\u00107\u001a\u00020\u00102\b\u00108\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00109\u001a\u00020:H\u00d6\u0001J\t\u0010;\u001a\u00020\bH\u00d6\u0001R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0018\u0010\r\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0018\u0010\f\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0018\u0010\n\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0016\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010$\u001a\u0004\b\"\u0010#R\u0016\u0010\u000f\u001a\u00020\u00108\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&\u00a8\u0006<"}, d2 = {"Lcom/atolow/miixs/data/model/payment/PaymentResponseDto;", "", "paymentId", "", "userId", "amount", "cashAmount", "paymentMethod", "", "status", "receiptId", "transactionId", "productId", "completedAt", "failureReason", "verified", "", "createdAt", "modifiedAt", "(JLjava/lang/Long;JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)V", "getAmount", "()J", "getCashAmount", "getCompletedAt", "()Ljava/lang/String;", "getCreatedAt", "getFailureReason", "getModifiedAt", "getPaymentId", "getPaymentMethod", "getProductId", "getReceiptId", "getStatus", "getTransactionId", "getUserId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getVerified", "()Z", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/Long;JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)Lcom/atolow/miixs/data/model/payment/PaymentResponseDto;", "equals", "other", "hashCode", "", "toString", "app_release"})
public final class PaymentResponseDto {
    @com.google.gson.annotations.SerializedName(value = "paymentId")
    private final long paymentId = 0L;
    @com.google.gson.annotations.SerializedName(value = "userId")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long userId = null;
    @com.google.gson.annotations.SerializedName(value = "amount")
    private final long amount = 0L;
    @com.google.gson.annotations.SerializedName(value = "cashAmount")
    private final long cashAmount = 0L;
    @com.google.gson.annotations.SerializedName(value = "paymentMethod")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String paymentMethod = null;
    @com.google.gson.annotations.SerializedName(value = "status")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String status = null;
    @com.google.gson.annotations.SerializedName(value = "receiptId")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String receiptId = null;
    @com.google.gson.annotations.SerializedName(value = "transactionId")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String transactionId = null;
    @com.google.gson.annotations.SerializedName(value = "productId")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String productId = null;
    @com.google.gson.annotations.SerializedName(value = "completedAt")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String completedAt = null;
    @com.google.gson.annotations.SerializedName(value = "failureReason")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String failureReason = null;
    @com.google.gson.annotations.SerializedName(value = "verified")
    private final boolean verified = false;
    @com.google.gson.annotations.SerializedName(value = "createdAt")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String createdAt = null;
    @com.google.gson.annotations.SerializedName(value = "modifiedAt")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String modifiedAt = null;
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component11() {
        return null;
    }
    
    public final boolean component12() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component2() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final long component4() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.atolow.miixs.data.model.payment.PaymentResponseDto copy(long paymentId, @org.jetbrains.annotations.Nullable()
    java.lang.Long userId, long amount, long cashAmount, @org.jetbrains.annotations.NotNull()
    java.lang.String paymentMethod, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.Nullable()
    java.lang.String receiptId, @org.jetbrains.annotations.Nullable()
    java.lang.String transactionId, @org.jetbrains.annotations.Nullable()
    java.lang.String productId, @org.jetbrains.annotations.Nullable()
    java.lang.String completedAt, @org.jetbrains.annotations.Nullable()
    java.lang.String failureReason, boolean verified, @org.jetbrains.annotations.Nullable()
    java.lang.String createdAt, @org.jetbrains.annotations.Nullable()
    java.lang.String modifiedAt) {
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
    
    public PaymentResponseDto(long paymentId, @org.jetbrains.annotations.Nullable()
    java.lang.Long userId, long amount, long cashAmount, @org.jetbrains.annotations.NotNull()
    java.lang.String paymentMethod, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.Nullable()
    java.lang.String receiptId, @org.jetbrains.annotations.Nullable()
    java.lang.String transactionId, @org.jetbrains.annotations.Nullable()
    java.lang.String productId, @org.jetbrains.annotations.Nullable()
    java.lang.String completedAt, @org.jetbrains.annotations.Nullable()
    java.lang.String failureReason, boolean verified, @org.jetbrains.annotations.Nullable()
    java.lang.String createdAt, @org.jetbrains.annotations.Nullable()
    java.lang.String modifiedAt) {
        super();
    }
    
    public final long getPaymentId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getUserId() {
        return null;
    }
    
    public final long getAmount() {
        return 0L;
    }
    
    public final long getCashAmount() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPaymentMethod() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getReceiptId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTransactionId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getProductId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCompletedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFailureReason() {
        return null;
    }
    
    public final boolean getVerified() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCreatedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getModifiedAt() {
        return null;
    }
}