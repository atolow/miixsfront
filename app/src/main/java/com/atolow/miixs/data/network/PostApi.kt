package com.atolow.miixs.data.network

import com.atolow.miixs.data.model.ApiResponse
import com.atolow.miixs.data.model.PageResponse
import com.atolow.miixs.data.model.post.CreatePostRequestDto
import com.atolow.miixs.data.model.post.PostResponseDto
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Query

interface PostApi {
    @POST("api/posts")
    suspend fun createPost(@Body request: CreatePostRequestDto): Response<ApiResponse<PostResponseDto>>
    
    @GET("api/posts/{postId}")
    suspend fun getPost(@Path("postId") postId: Long): Response<ApiResponse<PostResponseDto>>
    
    @GET("api/posts")
    suspend fun getPostList(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20,
        @Query("location") location: String? = null,
        @Query("gender") gender: String? = null,
        @Query("minAge") minAge: Int? = null,
        @Query("maxAge") maxAge: Int? = null
    ): Response<ApiResponse<PageResponse<PostResponseDto>>>
    
    @GET("api/posts/my")
    suspend fun getMyPosts(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<ApiResponse<PageResponse<PostResponseDto>>>
    
    @PUT("api/posts/{postId}")
    suspend fun updatePost(
        @Path("postId") postId: Long,
        @Body request: CreatePostRequestDto
    ): Response<ApiResponse<PostResponseDto>>
    
    @DELETE("api/posts/{postId}")
    suspend fun deletePost(@Path("postId") postId: Long): Response<ApiResponse<Void>>
}

