package com.atolow.miixs.data.repository

import com.atolow.miixs.data.model.ApiResponse
import com.atolow.miixs.data.model.payment.*
import com.atolow.miixs.data.network.ApiClient
import com.atolow.miixs.data.network.PaymentApi

class PaymentRepository {
    private val paymentApi: PaymentApi = ApiClient.paymentApi

    suspend fun getActiveProducts(): Result<List<ProductResponseDto>> {
        return try {
            val response = paymentApi.getActiveProducts()
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "상품 목록 조회 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    suspend fun verifyGooglePlayPayment(request: com.atolow.miixs.data.model.payment.VerifyGooglePlayPaymentRequestDto): Result<PaymentResponseDto> {
        return try {
            val response = paymentApi.verifyGooglePlayPayment(request)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "구글 플레이 결제 검증 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPaymentHistory(page: Int = 0, size: Int = 20): Result<com.atolow.miixs.data.model.PageResponse<PaymentResponseDto>> {
        return try {
            val response = paymentApi.getPaymentHistory(page, size)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "결제 내역 조회 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

