package com.atolow.miixs.data.network

import com.atolow.miixs.data.model.ApiResponse
import com.atolow.miixs.data.model.auth.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequestDto): Response<ApiResponse<LoginResponseDto>>
    
    @POST("api/auth/logout")
    suspend fun logout(): Response<ApiResponse<LogoutResponseDto>>
    
    @POST("api/auth/refresh")
    suspend fun refreshToken(@Body request: RefreshTokenRequestDto): Response<ApiResponse<RefreshTokenResponseDto>>
    
    @POST("api/auth/deactivate")
    suspend fun deactivate(): Response<ApiResponse<DeactivateUserResponseDto>>
    
    @POST("api/auth/send-verification-code")
    suspend fun sendVerificationCode(@Body request: SendVerificationCodeRequestDto): Response<ApiResponse<Void>>
    
    @POST("api/auth/verify-email")
    suspend fun verifyEmail(@Body request: EmailVerificationRequestDto): Response<ApiResponse<EmailVerificationResponseDto>>
    
    @GET("api/auth/check-resend-availability/{email}")
    suspend fun checkResendAvailability(@Path("email") email: String): Response<ApiResponse<ResendCooldownResponseDto>>
    
    @POST("api/auth/password/find")
    suspend fun findPassword(@Body request: FindPasswordRequestDto): Response<ApiResponse<FindPasswordResponseDto>>
    
    @POST("api/auth/password/verify")
    suspend fun verifyPasswordResetCode(@Body request: VerifyPasswordResetCodeRequestDto): Response<ApiResponse<VerifyPasswordResetCodeResponseDto>>
    
    @POST("api/auth/password/reset")
    suspend fun resetPassword(@Body request: ResetPasswordRequestDto): Response<ApiResponse<ResetPasswordResponseDto>>
    
    @POST("api/auth/password/change")
    suspend fun changePassword(@Body request: ChangePasswordRequestDto): Response<ApiResponse<ChangePasswordResponseDto>>
    
    @POST("api/auth/phone/send-verification-code")
    suspend fun sendPhoneVerificationCode(@Body request: SendPhoneVerificationCodeRequestDto): Response<ApiResponse<Void>>
    
    @POST("api/auth/phone/verify-code")
    suspend fun verifyPhoneCode(@Body request: VerifyPhoneCodeRequestDto): Response<ApiResponse<Void>>
}

