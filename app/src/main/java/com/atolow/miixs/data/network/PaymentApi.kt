package com.atolow.miixs.data.network

import com.atolow.miixs.data.model.ApiResponse
import com.atolow.miixs.data.model.PageResponse
import com.atolow.miixs.data.model.payment.CreatePaymentRequestDto
import com.atolow.miixs.data.model.payment.PaymentResponseDto
import com.atolow.miixs.data.model.payment.VerifyGooglePlayPaymentRequestDto
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Query

interface PaymentApi {
    @POST("api/payments")
    suspend fun createPayment(@Body request: CreatePaymentRequestDto): Response<ApiResponse<PaymentResponseDto>>
    
    @POST("api/payments/verify/google-play")
    suspend fun verifyGooglePlayPayment(@Body request: VerifyGooglePlayPaymentRequestDto): Response<ApiResponse<PaymentResponseDto>>
    
    @GET("api/payments")
    suspend fun getPaymentHistory(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<ApiResponse<PageResponse<PaymentResponseDto>>>
    
    @GET("api/payments/{paymentId}")
    suspend fun getPayment(@Path("paymentId") paymentId: Long): Response<ApiResponse<PaymentResponseDto>>
    
    @GET("api/payments/products")
    suspend fun getActiveProducts(): Response<ApiResponse<List<com.atolow.miixs.data.model.payment.ProductResponseDto>>>
}

