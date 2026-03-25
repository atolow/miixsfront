package com.atolow.miixs.data.network

import com.atolow.miixs.data.model.ApiResponse
import com.atolow.miixs.data.model.PageResponse
import com.atolow.miixs.data.model.block.BlockResponseDto
import com.atolow.miixs.data.model.block.BlockUserRequestDto
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Query

interface BlockApi {
    @POST("api/users/blocks")
    suspend fun blockUser(@Body request: BlockUserRequestDto): Response<ApiResponse<BlockResponseDto>>
    
    @DELETE("api/users/blocks/{blockedUserId}")
    suspend fun unblockUser(@Path("blockedUserId") blockedUserId: Long): Response<ApiResponse<Void>>
    
    @GET("api/users/blocks")
    suspend fun getBlockedUsers(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<ApiResponse<PageResponse<BlockResponseDto>>>
}

