package com.atolow.miixs.data.network;

import com.atolow.miixs.data.model.ApiResponse;
import com.atolow.miixs.data.model.auth.*;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00a0\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ$\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0010J$\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0014J$\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0017H\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0010J$\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u001dH\u00a7@\u00a2\u0006\u0002\u0010\u001eJ$\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"J$\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020%H\u00a7@\u00a2\u0006\u0002\u0010&J$\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020(H\u00a7@\u00a2\u0006\u0002\u0010)J$\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020,H\u00a7@\u00a2\u0006\u0002\u0010-J$\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u000200H\u00a7@\u00a2\u0006\u0002\u00101J$\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u000203H\u00a7@\u00a2\u0006\u0002\u00104\u00a8\u00065"}, d2 = {"Lcom/atolow/miixs/data/network/AuthApi;", "", "changePassword", "Lretrofit2/Response;", "Lcom/atolow/miixs/data/model/ApiResponse;", "Lcom/atolow/miixs/data/model/auth/ChangePasswordResponseDto;", "request", "Lcom/atolow/miixs/data/model/auth/ChangePasswordRequestDto;", "(Lcom/atolow/miixs/data/model/auth/ChangePasswordRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkResendAvailability", "Lcom/atolow/miixs/data/model/auth/ResendCooldownResponseDto;", "email", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deactivate", "Lcom/atolow/miixs/data/model/auth/DeactivateUserResponseDto;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findPassword", "Lcom/atolow/miixs/data/model/auth/FindPasswordResponseDto;", "Lcom/atolow/miixs/data/model/auth/FindPasswordRequestDto;", "(Lcom/atolow/miixs/data/model/auth/FindPasswordRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "Lcom/atolow/miixs/data/model/auth/LoginResponseDto;", "Lcom/atolow/miixs/data/model/auth/LoginRequestDto;", "(Lcom/atolow/miixs/data/model/auth/LoginRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logout", "Lcom/atolow/miixs/data/model/auth/LogoutResponseDto;", "refreshToken", "Lcom/atolow/miixs/data/model/auth/RefreshTokenResponseDto;", "Lcom/atolow/miixs/data/model/auth/RefreshTokenRequestDto;", "(Lcom/atolow/miixs/data/model/auth/RefreshTokenRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetPassword", "Lcom/atolow/miixs/data/model/auth/ResetPasswordResponseDto;", "Lcom/atolow/miixs/data/model/auth/ResetPasswordRequestDto;", "(Lcom/atolow/miixs/data/model/auth/ResetPasswordRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendPhoneVerificationCode", "Ljava/lang/Void;", "Lcom/atolow/miixs/data/model/auth/SendPhoneVerificationCodeRequestDto;", "(Lcom/atolow/miixs/data/model/auth/SendPhoneVerificationCodeRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendVerificationCode", "Lcom/atolow/miixs/data/model/auth/SendVerificationCodeRequestDto;", "(Lcom/atolow/miixs/data/model/auth/SendVerificationCodeRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyEmail", "Lcom/atolow/miixs/data/model/auth/EmailVerificationResponseDto;", "Lcom/atolow/miixs/data/model/auth/EmailVerificationRequestDto;", "(Lcom/atolow/miixs/data/model/auth/EmailVerificationRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyPasswordResetCode", "Lcom/atolow/miixs/data/model/auth/VerifyPasswordResetCodeResponseDto;", "Lcom/atolow/miixs/data/model/auth/VerifyPasswordResetCodeRequestDto;", "(Lcom/atolow/miixs/data/model/auth/VerifyPasswordResetCodeRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyPhoneCode", "Lcom/atolow/miixs/data/model/auth/VerifyPhoneCodeRequestDto;", "(Lcom/atolow/miixs/data/model/auth/VerifyPhoneCodeRequestDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface AuthApi {
    
    @retrofit2.http.POST(value = "api/auth/login")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.auth.LoginRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.auth.LoginResponseDto>>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/logout")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object logout(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.auth.LogoutResponseDto>>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/refresh")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object refreshToken(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.auth.RefreshTokenRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.auth.RefreshTokenResponseDto>>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/deactivate")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deactivate(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.auth.DeactivateUserResponseDto>>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/send-verification-code")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendVerificationCode(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.auth.SendVerificationCodeRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<java.lang.Void>>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/verify-email")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object verifyEmail(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.auth.EmailVerificationRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.auth.EmailVerificationResponseDto>>> $completion);
    
    @retrofit2.http.GET(value = "api/auth/check-resend-availability/{email}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object checkResendAvailability(@retrofit2.http.Path(value = "email")
    @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.auth.ResendCooldownResponseDto>>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/password/find")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object findPassword(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.auth.FindPasswordRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.auth.FindPasswordResponseDto>>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/password/verify")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object verifyPasswordResetCode(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.auth.VerifyPasswordResetCodeRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.auth.VerifyPasswordResetCodeResponseDto>>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/password/reset")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object resetPassword(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.auth.ResetPasswordRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.auth.ResetPasswordResponseDto>>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/password/change")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object changePassword(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.auth.ChangePasswordRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<com.atolow.miixs.data.model.auth.ChangePasswordResponseDto>>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/phone/send-verification-code")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendPhoneVerificationCode(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.auth.SendPhoneVerificationCodeRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<java.lang.Void>>> $completion);
    
    @retrofit2.http.POST(value = "api/auth/phone/verify-code")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object verifyPhoneCode(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.atolow.miixs.data.model.auth.VerifyPhoneCodeRequestDto request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.atolow.miixs.data.model.ApiResponse<java.lang.Void>>> $completion);
}