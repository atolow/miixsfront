package com.atolow.miixs.data.repository

import android.util.Log
import com.atolow.miixs.data.model.ApiResponse
import com.atolow.miixs.data.model.report.CreateReportRequestDto
import com.atolow.miixs.data.model.report.CreateUserReportRequestDto
import com.atolow.miixs.data.model.report.ReportResponseDto
import com.atolow.miixs.data.network.ApiClient
import com.google.gson.Gson

class ReportRepository {
    private val reportApi = ApiClient.reportApi
    private val gson = Gson()
    private val TAG = "ReportRepository"
    
    suspend fun reportPost(postId: Long, reason: String? = null): Result<ReportResponseDto> {
        return try {
            val request = CreateReportRequestDto(postId, reason)
            val response = reportApi.createReport(request)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                val errorBody = try {
                    response.errorBody()?.string()
                } catch (e: Exception) {
                    null
                }
                val errorMessage = response.body()?.message ?: errorBody ?: "신고 실패"
                Log.e(TAG, "게시글 신고 실패: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e(TAG, "게시글 신고 예외 발생", e)
            Result.failure(e)
        }
    }
    
    suspend fun reportUser(userId: Long, reason: String): Result<ReportResponseDto> {
        return try {
            val request = CreateUserReportRequestDto(userId, reason)
            val response = reportApi.createUserReport(request)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                val errorBody = try {
                    response.errorBody()?.string()
                } catch (e: Exception) {
                    null
                }
                val errorMessage = try {
                    if (errorBody != null) {
                        val errorResponse = gson.fromJson(errorBody, com.atolow.miixs.data.model.ErrorResponse::class.java)
                        errorResponse.message
                    } else {
                        response.body()?.message ?: "신고 실패"
                    }
                } catch (e: Exception) {
                    response.body()?.message ?: errorBody ?: "신고 실패"
                }
                Log.e(TAG, "유저 신고 실패: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e(TAG, "유저 신고 예외 발생", e)
            Result.failure(e)
        }
    }
}

