package com.atolow.miixs.data.network

import com.atolow.miixs.data.model.ApiResponse
import com.atolow.miixs.data.model.user.DashboardResponseDto
import com.atolow.miixs.data.model.user.ProfileResponseDto
import com.atolow.miixs.data.model.user.UpdateProfileRequestDto
import com.atolow.miixs.data.model.user.UpdateChatSettingsRequestDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Query

interface UserApi {
    @GET("api/auth/profile")
    suspend fun getProfile(): Response<ApiResponse<ProfileResponseDto>>
    
    @PUT("api/auth/profile")
    suspend fun updateProfile(@Body request: UpdateProfileRequestDto): Response<ApiResponse<ProfileResponseDto>>
    
    @Multipart
    @POST("api/auth/profile/image")
    suspend fun uploadProfileImage(@Part image: MultipartBody.Part): Response<ApiResponse<String>>
    
    @Multipart
    @POST("api/auth/profile/images")
    suspend fun uploadProfileImages(@Part images: List<MultipartBody.Part>): Response<ApiResponse<List<String>>>
    
    @DELETE("api/auth/profile/image")
    suspend fun deleteProfileImage(): Response<ApiResponse<Void>>
    
    @GET("api/auth/dashboard")
    suspend fun getDashboard(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<ApiResponse<DashboardResponseDto>>
    
    @GET("api/users/{userId}")
    suspend fun getUserProfile(@Path("userId") userId: Long): Response<ApiResponse<ProfileResponseDto>>
    
    @GET("api/users")
    suspend fun getUserList(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20,
        @Query("location") location: String? = null,
        @Query("gender") gender: String? = null,
        @Query("minAge") minAge: Int? = null,
        @Query("maxAge") maxAge: Int? = null
    ): Response<ApiResponse<com.atolow.miixs.data.model.PageResponse<ProfileResponseDto>>>
    
    @Multipart
    @POST("api/auth/oauth/additional-info")
    suspend fun completeOAuthAdditionalInfo(
        @Part("nickname") nickname: okhttp3.RequestBody,
        @Part("location") location: okhttp3.RequestBody,
        @Part("phoneNumber") phoneNumber: okhttp3.RequestBody,
        @Part("verificationCode") verificationCode: okhttp3.RequestBody,
        @Part("bio") bio: okhttp3.RequestBody?,
        @Part profileImages: List<MultipartBody.Part>?
    ): Response<ApiResponse<Unit>>
    
    @PUT("api/auth/chat-settings")
    suspend fun updateChatSettings(@Body request: UpdateChatSettingsRequestDto): Response<ApiResponse<Unit>>
}

