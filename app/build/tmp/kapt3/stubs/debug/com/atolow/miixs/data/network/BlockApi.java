package com.atolow.miixs.data.network;

import com.atolow.miixs.data.model.ApiResponse;
import com.atolow.miixs.data.model.PageResponse;
import com.atolow.miixs.data.model.block.BlockResponseDto;
import com.atolow.miixs.data.model.block.BlockUserRequestDto;
import retrofit2.Response;
import retrofit2.http.*;
import retrofit2.http.Query;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ4\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n0\u00040\u00032\b\b\u0003\u0010\u000b\u001a\u00020\f2\b\b\u0003\u0010\r\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ$\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00040\u00032\b\b\u0001\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"Lcom/atolow/miixs/data/network/BlockApi;", "", "blockUser", "Lretrofit2/Response;", "Lcom/atolow/miixs/data/model/ApiResponse;", "Lcom/atolow/miixs/data/model/block/BlockResponseDto;", "request", "Lcom/atolow/miixs/data/model/block/BlockUserRequestDto;", "(Lcom/atolow/miixs/data/model/block/BlockUserRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBlockedUsers", "Lcom/atolow/miixs/data/model/PageResponse;", "page", "", "size", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unblockUser", "Ljava/lang/Void;", "blockedUserId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface BlockApi {
    
    @retrofit2.http.POST(value = "api/users/blocks")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object blockUser(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.block.BlockUserRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.block.BlockResponseDto>>> $completion);
    
    @retrofit2.http.DELETE(value = "api/users/blocks/{blockedUserId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object unblockUser(@retrofit2.http.Path(value = "blockedUserId")
    long blockedUserId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<java.lang.Void>>> $completion);
    
    @retrofit2.http.GET(value = "api/users/blocks")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBlockedUsers(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.PageResponse<com.atolow.miixs.data.model.block.BlockResponseDto>>>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}