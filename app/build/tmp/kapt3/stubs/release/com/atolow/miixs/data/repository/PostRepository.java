package com.atolow.miixs.data.repository;

import android.util.Log;
import com.atolow.miixs.data.model.ApiResponse;
import com.atolow.miixs.data.model.ErrorResponse;
import com.atolow.miixs.data.model.PageResponse;
import com.atolow.miixs.data.model.post.*;
import com.atolow.miixs.data.network.ApiClient;
import com.google.gson.Gson;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0013\u0010\u0014J6\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00160\n2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001a\u0010\u001bJ$\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001d\u0010\u0014J6\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00160\n2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001f\u0010\u001bJ^\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00160\n2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010\u00182\b\u0010&\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\'\u0010(J,\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b*\u0010+R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006,"}, d2 = {"Lcom/atolow/miixs/data/repository/PostRepository;", "", "()V", "TAG", "", "gson", "Lcom/google/gson/Gson;", "postApi", "Lcom/atolow/miixs/data/network/PostApi;", "createPost", "Lkotlin/Result;", "Lcom/atolow/miixs/data/model/post/PostResponseDto;", "content", "createPost-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePost", "", "postId", "", "deletePost-gIAlu-s", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMyPosts", "Lcom/atolow/miixs/data/model/PageResponse;", "page", "", "size", "getMyPosts-0E7RQCE", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPost", "getPost-gIAlu-s", "getPostList", "getPostList-0E7RQCE", "getPostListWithFilters", "location", "Lcom/atolow/miixs/data/model/Location;", "gender", "Lcom/atolow/miixs/data/model/Gender;", "minAge", "maxAge", "getPostListWithFilters-bMdYcbs", "(Lcom/atolow/miixs/data/model/Location;Lcom/atolow/miixs/data/model/Gender;Ljava/lang/Integer;Ljava/lang/Integer;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePost", "updatePost-0E7RQCE", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class PostRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.network.PostApi postApi = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "PostRepository";
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    
    public PostRepository() {
        super();
    }
}