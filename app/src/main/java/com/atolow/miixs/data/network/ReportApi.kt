package com.atolow.miixs.data.network

import com.atolow.miixs.data.model.ApiResponse
import com.atolow.miixs.data.model.report.CreateReportRequestDto
import com.atolow.miixs.data.model.report.CreateUserReportRequestDto
import com.atolow.miixs.data.model.report.ReportResponseDto
import retrofit2.Response
import retrofit2.http.*

interface ReportApi {
    @POST("api/reports")
    suspend fun createReport(@Body request: CreateReportRequestDto): Response<ApiResponse<ReportResponseDto>>
    
    @POST("api/reports/users")
    suspend fun createUserReport(@Body request: CreateUserReportRequestDto): Response<ApiResponse<ReportResponseDto>>
    
    @GET("api/reports/users/{userId}/count")
    suspend fun getReportCount(@Path("userId") userId: Long): Response<ApiResponse<Long>>
}

