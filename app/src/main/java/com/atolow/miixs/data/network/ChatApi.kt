package com.atolow.miixs.data.network

import com.atolow.miixs.data.model.ApiResponse
import com.atolow.miixs.data.model.PageResponse
import com.atolow.miixs.data.model.chat.ChatRoomResponseDto
import com.atolow.miixs.data.model.chat.CreateChatRoomRequestDto
import com.atolow.miixs.data.model.chat.CreateChatRoomResponseDto
import com.atolow.miixs.data.model.chat.MessageResponseDto
import com.atolow.miixs.data.model.chat.SendMessageRequestDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Query

interface ChatApi {
    @POST("api/chat/rooms")
    suspend fun createChatRoom(@Body request: CreateChatRoomRequestDto): Response<ApiResponse<CreateChatRoomResponseDto>>
    
    @GET("api/chat/rooms")
    suspend fun getChatRoomList(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<ApiResponse<PageResponse<ChatRoomResponseDto>>>
    
    @GET("api/chat/rooms/{chatRoomId}/messages")
    suspend fun getMessages(
        @Path("chatRoomId") chatRoomId: Long,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 50
    ): Response<ApiResponse<PageResponse<MessageResponseDto>>>
    
    @POST("api/chat/messages")
    suspend fun sendMessage(@Body request: SendMessageRequestDto): Response<ApiResponse<MessageResponseDto>>
    
    @Multipart
    @POST("api/chat/rooms/{chatRoomId}/images")
    suspend fun sendImageMessage(
        @Path("chatRoomId") chatRoomId: Long,
        @Part image: MultipartBody.Part
    ): Response<ApiResponse<MessageResponseDto>>
    
    @POST("api/chat/rooms/{chatRoomId}/messages/{messageId}/read")
    suspend fun markMessageAsRead(
        @Path("chatRoomId") chatRoomId: Long,
        @Path("messageId") messageId: Long
    ): Response<ApiResponse<Void>>
    
    @GET("api/chat/rooms/{chatRoomId}/unread-count")
    suspend fun getUnreadMessageCount(@Path("chatRoomId") chatRoomId: Long): Response<ApiResponse<Long>>
    
    @GET("api/chat/unread-count/total")
    suspend fun getTotalUnreadMessageCount(): Response<ApiResponse<Long>>
    
    @DELETE("api/chat/rooms/{chatRoomId}")
    suspend fun leaveChatRoom(@Path("chatRoomId") chatRoomId: Long): Response<ApiResponse<Void>>
    
    @POST("api/chat/rooms/{chatRoomId}/favorite")
    suspend fun toggleFavorite(@Path("chatRoomId") chatRoomId: Long): Response<ApiResponse<Void>>
    
    @GET("api/chat/rooms/favorites")
    suspend fun getFavoriteChatRoomList(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<ApiResponse<PageResponse<ChatRoomResponseDto>>>
}

