package com.atolow.miixs.data.network;

import com.atolow.miixs.data.model.ApiResponse;
import com.atolow.miixs.data.model.PageResponse;
import com.atolow.miixs.data.model.payment.CreatePaymentRequestDto;
import com.atolow.miixs.data.model.payment.PaymentResponseDto;
import com.atolow.miixs.data.model.payment.VerifyGooglePlayPaymentRequestDto;
import retrofit2.Response;
import retrofit2.http.*;
import retrofit2.http.Query;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\fJ$\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J4\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00120\u00040\u00032\b\b\u0003\u0010\u0013\u001a\u00020\u00142\b\b\u0003\u0010\u0015\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0016J$\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/atolow/miixs/data/network/PaymentApi;", "", "createPayment", "Lretrofit2/Response;", "Lcom/atolow/miixs/data/model/ApiResponse;", "Lcom/atolow/miixs/data/model/payment/PaymentResponseDto;", "request", "Lcom/atolow/miixs/data/model/payment/CreatePaymentRequestDto;", "(Lcom/atolow/miixs/data/model/payment/CreatePaymentRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveProducts", "", "Lcom/atolow/miixs/data/model/payment/ProductResponseDto;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPayment", "paymentId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPaymentHistory", "Lcom/atolow/miixs/data/model/PageResponse;", "page", "", "size", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyGooglePlayPayment", "Lcom/atolow/miixs/data/model/payment/VerifyGooglePlayPaymentRequestDto;", "(Lcom/atolow/miixs/data/model/payment/VerifyGooglePlayPaymentRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface PaymentApi {
    
    @retrofit2.http.POST(value = "api/payments")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createPayment(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.payment.CreatePaymentRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.payment.PaymentResponseDto>>> $completion);
    
    @retrofit2.http.POST(value = "api/payments/verify/google-play")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object verifyGooglePlayPayment(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.payment.VerifyGooglePlayPaymentRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.payment.PaymentResponseDto>>> $completion);
    
    @retrofit2.http.GET(value = "api/payments")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPaymentHistory(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.PageResponse<com.atolow.miixs.data.model.payment.PaymentResponseDto>>>> $completion);
    
    @retrofit2.http.GET(value = "api/payments/{paymentId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPayment(@retrofit2.http.Path(value = "paymentId")
    long paymentId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.payment.PaymentResponseDto>>> $completion);
    
    @retrofit2.http.GET(value = "api/payments/products")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getActiveProducts(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<java.util.List<com.atolow.miixs.data.model.payment.ProductResponseDto>>>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}