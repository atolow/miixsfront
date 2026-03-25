package com.atolow.miixs.data.repository;

import com.atolow.miixs.data.model.ApiResponse;
import com.atolow.miixs.data.model.payment.*;
import com.atolow.miixs.data.network.ApiClient;
import com.atolow.miixs.data.network.PaymentApi;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\t\u0010\nJ6\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0011\u0010\u0012J$\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0018"}, d2 = {"Lcom/atolow/miixs/data/repository/PaymentRepository;", "", "()V", "paymentApi", "Lcom/atolow/miixs/data/network/PaymentApi;", "getActiveProducts", "Lkotlin/Result;", "", "Lcom/atolow/miixs/data/model/payment/ProductResponseDto;", "getActiveProducts-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPaymentHistory", "Lcom/atolow/miixs/data/model/PageResponse;", "Lcom/atolow/miixs/data/model/payment/PaymentResponseDto;", "page", "", "size", "getPaymentHistory-0E7RQCE", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyGooglePlayPayment", "request", "Lcom/atolow/miixs/data/model/payment/VerifyGooglePlayPaymentRequestDto;", "verifyGooglePlayPayment-gIAlu-s", "(Lcom/atolow/miixs/data/model/payment/VerifyGooglePlayPaymentRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class PaymentRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.network.PaymentApi paymentApi = null;
    
    public PaymentRepository() {
        super();
    }
}