package com.atolow.miixs.data.network;

import com.atolow.miixs.data.model.ApiResponse;
import com.atolow.miixs.data.model.PageResponse;
import com.atolow.miixs.data.model.post.CreatePostRequestDto;
import com.atolow.miixs.data.model.post.PostResponseDto;
import retrofit2.Response;
import retrofit2.http.*;
import retrofit2.http.Query;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ$\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ4\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000f0\u00040\u00032\b\b\u0003\u0010\u0010\u001a\u00020\u00112\b\b\u0003\u0010\u0012\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0013J$\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJd\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000f0\u00040\u00032\b\b\u0003\u0010\u0010\u001a\u00020\u00112\b\b\u0003\u0010\u0012\u001a\u00020\u00112\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u00172\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\u00112\n\b\u0003\u0010\u001a\u001a\u0004\u0018\u00010\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u001bJ.\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u001d\u00a8\u0006\u001e"}, d2 = {"Lcom/atolow/miixs/data/network/PostApi;", "", "createPost", "Lretrofit2/Response;", "Lcom/atolow/miixs/data/model/ApiResponse;", "Lcom/atolow/miixs/data/model/post/PostResponseDto;", "request", "Lcom/atolow/miixs/data/model/post/CreatePostRequestDto;", "(Lcom/atolow/miixs/data/model/post/CreatePostRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePost", "Ljava/lang/Void;", "postId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMyPosts", "Lcom/atolow/miixs/data/model/PageResponse;", "page", "", "size", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPost", "getPostList", "location", "", "gender", "minAge", "maxAge", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePost", "(JLcom/atolow/miixs/data/model/post/CreatePostRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface PostApi {
    
    @retrofit2.http.POST(value = "api/posts")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createPost(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.post.CreatePostRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.post.PostResponseDto>>> $completion);
    
    @retrofit2.http.GET(value = "api/posts/{postId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPost(@retrofit2.http.Path(value = "postId")
    long postId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.post.PostResponseDto>>> $completion);
    
    @retrofit2.http.GET(value = "api/posts")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPostList(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @retrofit2.http.Query(value = "location")
    @org.jetbrains.annotations.Nullable()
    java.lang.String location, @retrofit2.http.Query(value = "gender")
    @org.jetbrains.annotations.Nullable()
    java.lang.String gender, @retrofit2.http.Query(value = "minAge")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer minAge, @retrofit2.http.Query(value = "maxAge")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer maxAge, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.PageResponse<com.atolow.miixs.data.model.post.PostResponseDto>>>> $completion);
    
    @retrofit2.http.GET(value = "api/posts/my")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMyPosts(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.PageResponse<com.atolow.miixs.data.model.post.PostResponseDto>>>> $completion);
    
    @retrofit2.http.PUT(value = "api/posts/{postId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updatePost(@retrofit2.http.Path(value = "postId")
    long postId, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.post.CreatePostRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.post.PostResponseDto>>> $completion);
    
    @retrofit2.http.DELETE(value = "api/posts/{postId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deletePost(@retrofit2.http.Path(value = "postId")
    long postId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<java.lang.Void>>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}