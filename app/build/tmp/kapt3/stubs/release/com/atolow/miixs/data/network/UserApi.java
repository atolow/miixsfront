package com.atolow.miixs.data.network;

import com.atolow.miixs.data.model.ApiResponse;
import com.atolow.miixs.data.model.user.DashboardResponseDto;
import com.atolow.miixs.data.model.user.ProfileResponseDto;
import com.atolow.miixs.data.model.user.UpdateProfileRequestDto;
import com.atolow.miixs.data.model.user.UpdateChatSettingsRequestDto;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.*;
import retrofit2.http.Query;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J`\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\t\u001a\u00020\u00072\b\b\u0001\u0010\n\u001a\u00020\u00072\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0010\b\u0001\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0012J.\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00040\u00032\b\b\u0003\u0010\u0015\u001a\u00020\u00162\b\b\u0003\u0010\u0017\u001a\u00020\u0016H\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0012Jd\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u001c0\u00040\u00032\b\b\u0003\u0010\u0015\u001a\u00020\u00162\b\b\u0003\u0010\u0017\u001a\u00020\u00162\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u001d2\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\n\b\u0003\u0010\u001f\u001a\u0004\u0018\u00010\u00162\n\b\u0003\u0010 \u001a\u0004\u0018\u00010\u0016H\u00a7@\u00a2\u0006\u0002\u0010!J$\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00040\u00032\b\b\u0001\u0010#\u001a\u00020$H\u00a7@\u00a2\u0006\u0002\u0010%J$\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\'\u001a\u00020(H\u00a7@\u00a2\u0006\u0002\u0010)J$\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00040\u00032\b\b\u0001\u0010\'\u001a\u00020+H\u00a7@\u00a2\u0006\u0002\u0010,J$\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u00040\u00032\b\b\u0001\u0010.\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010/J0\u00100\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\r0\u00040\u00032\u000e\b\u0001\u00101\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u00a7@\u00a2\u0006\u0002\u00102\u00a8\u00063"}, d2 = {"Lcom/atolow/miixs/data/network/UserApi;", "", "completeOAuthAdditionalInfo", "Lretrofit2/Response;", "Lcom/atolow/miixs/data/model/ApiResponse;", "", "nickname", "Lokhttp3/RequestBody;", "location", "phoneNumber", "verificationCode", "bio", "profileImages", "", "Lokhttp3/MultipartBody$Part;", "(Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteProfileImage", "Ljava/lang/Void;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDashboard", "Lcom/atolow/miixs/data/model/user/DashboardResponseDto;", "page", "", "size", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProfile", "Lcom/atolow/miixs/data/model/user/ProfileResponseDto;", "getUserList", "Lcom/atolow/miixs/data/model/PageResponse;", "", "gender", "minAge", "maxAge", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserProfile", "userId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateChatSettings", "request", "Lcom/atolow/miixs/data/model/user/UpdateChatSettingsRequestDto;", "(Lcom/atolow/miixs/data/model/user/UpdateChatSettingsRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProfile", "Lcom/atolow/miixs/data/model/user/UpdateProfileRequestDto;", "(Lcom/atolow/miixs/data/model/user/UpdateProfileRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadProfileImage", "image", "(Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadProfileImages", "images", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface UserApi {
    
    @retrofit2.http.GET(value = "api/auth/profile")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProfile(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.user.ProfileResponseDto>>> $completion);
    
    @retrofit2.http.PUT(value = "api/auth/profile")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateProfile(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.user.UpdateProfileRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.user.ProfileResponseDto>>> $completion);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "api/auth/profile/image")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object uploadProfileImage(@retrofit2.http.Part()
    @org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part image, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<java.lang.String>>> $completion);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "api/auth/profile/images")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object uploadProfileImages(@retrofit2.http.Part()
    @org.jetbrains.annotations.NotNull()
    java.util.List<okhttp3.MultipartBody.Part> images, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<java.util.List<java.lang.String>>>> $completion);
    
    @retrofit2.http.DELETE(value = "api/auth/profile/image")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteProfileImage(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<java.lang.Void>>> $completion);
    
    @retrofit2.http.GET(value = "api/auth/dashboard")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDashboard(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.user.DashboardResponseDto>>> $completion);
    
    @retrofit2.http.GET(value = "api/users/{userId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserProfile(@retrofit2.http.Path(value = "userId")
    long userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.user.ProfileResponseDto>>> $completion);
    
    @retrofit2.http.GET(value = "api/users")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserList(@retrofit2.http.Query(value = "page")
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
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.PageResponse<com.atolow.miixs.data.model.user.ProfileResponseDto>>>> $completion);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "api/auth/oauth/additional-info")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object completeOAuthAdditionalInfo(@retrofit2.http.Part(value = "nickname")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody nickname, @retrofit2.http.Part(value = "location")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody location, @retrofit2.http.Part(value = "phoneNumber")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody phoneNumber, @retrofit2.http.Part(value = "verificationCode")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody verificationCode, @retrofit2.http.Part(value = "bio")
    @org.jetbrains.annotations.Nullable()
    okhttp3.RequestBody bio, @retrofit2.http.Part()
    @org.jetbrains.annotations.Nullable()
    java.util.List<okhttp3.MultipartBody.Part> profileImages, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<kotlin.Unit>>> $completion);
    
    @retrofit2.http.PUT(value = "api/auth/chat-settings")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateChatSettings(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.user.UpdateChatSettingsRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<kotlin.Unit>>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}