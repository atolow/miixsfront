package com.atolow.miixs.data.network;

import com.atolow.miixs.data.model.ApiResponse;
import com.atolow.miixs.data.model.PageResponse;
import com.atolow.miixs.data.model.chat.ChatRoomResponseDto;
import com.atolow.miixs.data.model.chat.CreateChatRoomRequestDto;
import com.atolow.miixs.data.model.chat.CreateChatRoomResponseDto;
import com.atolow.miixs.data.model.chat.MessageResponseDto;
import com.atolow.miixs.data.model.chat.SendMessageRequestDto;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.*;
import retrofit2.http.Query;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ4\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u00040\u00032\b\b\u0003\u0010\f\u001a\u00020\r2\b\b\u0003\u0010\u000e\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ4\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u00040\u00032\b\b\u0003\u0010\f\u001a\u00020\r2\b\b\u0003\u0010\u000e\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ>\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\n0\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u00142\b\b\u0003\u0010\f\u001a\u00020\r2\b\b\u0003\u0010\u000e\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0017J$\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0019J$\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0019J.\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u001d\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u001eJ.\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010 \u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"J$\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020$H\u00a7@\u00a2\u0006\u0002\u0010%J$\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00040\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0019\u00a8\u0006\'"}, d2 = {"Lcom/atolow/miixs/data/network/ChatApi;", "", "createChatRoom", "Lretrofit2/Response;", "Lcom/atolow/miixs/data/model/ApiResponse;", "Lcom/atolow/miixs/data/model/chat/CreateChatRoomResponseDto;", "request", "Lcom/atolow/miixs/data/model/chat/CreateChatRoomRequestDto;", "(Lcom/atolow/miixs/data/model/chat/CreateChatRoomRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getChatRoomList", "Lcom/atolow/miixs/data/model/PageResponse;", "Lcom/atolow/miixs/data/model/chat/ChatRoomResponseDto;", "page", "", "size", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFavoriteChatRoomList", "getMessages", "Lcom/atolow/miixs/data/model/chat/MessageResponseDto;", "chatRoomId", "", "(JIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalUnreadMessageCount", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnreadMessageCount", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "leaveChatRoom", "Ljava/lang/Void;", "markMessageAsRead", "messageId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendImageMessage", "image", "Lokhttp3/MultipartBody$Part;", "(JLokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendMessage", "Lcom/atolow/miixs/data/model/chat/SendMessageRequestDto;", "(Lcom/atolow/miixs/data/model/chat/SendMessageRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toggleFavorite", "app_release"})
public abstract interface ChatApi {
    
    @retrofit2.http.POST(value = "api/chat/rooms")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createChatRoom(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.chat.CreateChatRoomRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.chat.CreateChatRoomResponseDto>>> $completion);
    
    @retrofit2.http.GET(value = "api/chat/rooms")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getChatRoomList(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.PageResponse<com.atolow.miixs.data.model.chat.ChatRoomResponseDto>>>> $completion);
    
    @retrofit2.http.GET(value = "api/chat/rooms/{chatRoomId}/messages")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMessages(@retrofit2.http.Path(value = "chatRoomId")
    long chatRoomId, @retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.PageResponse<com.atolow.miixs.data.model.chat.MessageResponseDto>>>> $completion);
    
    @retrofit2.http.POST(value = "api/chat/messages")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendMessage(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.chat.SendMessageRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.chat.MessageResponseDto>>> $completion);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "api/chat/rooms/{chatRoomId}/images")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendImageMessage(@retrofit2.http.Path(value = "chatRoomId")
    long chatRoomId, @retrofit2.http.Part()
    @org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part image, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.chat.MessageResponseDto>>> $completion);
    
    @retrofit2.http.POST(value = "api/chat/rooms/{chatRoomId}/messages/{messageId}/read")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markMessageAsRead(@retrofit2.http.Path(value = "chatRoomId")
    long chatRoomId, @retrofit2.http.Path(value = "messageId")
    long messageId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<java.lang.Void>>> $completion);
    
    @retrofit2.http.GET(value = "api/chat/rooms/{chatRoomId}/unread-count")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUnreadMessageCount(@retrofit2.http.Path(value = "chatRoomId")
    long chatRoomId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<java.lang.Long>>> $completion);
    
    @retrofit2.http.GET(value = "api/chat/unread-count/total")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalUnreadMessageCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<java.lang.Long>>> $completion);
    
    @retrofit2.http.DELETE(value = "api/chat/rooms/{chatRoomId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object leaveChatRoom(@retrofit2.http.Path(value = "chatRoomId")
    long chatRoomId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<java.lang.Void>>> $completion);
    
    @retrofit2.http.POST(value = "api/chat/rooms/{chatRoomId}/favorite")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object toggleFavorite(@retrofit2.http.Path(value = "chatRoomId")
    long chatRoomId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<java.lang.Void>>> $completion);
    
    @retrofit2.http.GET(value = "api/chat/rooms/favorites")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFavoriteChatRoomList(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.PageResponse<com.atolow.miixs.data.model.chat.ChatRoomResponseDto>>>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}