package com.atolow.miixs.data.repository;

import android.util.Log;
import com.atolow.miixs.data.local.TokenManager;
import com.atolow.miixs.data.model.ApiResponse;
import com.atolow.miixs.data.model.ErrorResponse;
import com.atolow.miixs.data.model.auth.*;
import com.atolow.miixs.data.model.auth.SendPhoneVerificationCodeRequestDto;
import com.atolow.miixs.data.model.auth.VerifyPhoneCodeRequestDto;
import com.atolow.miixs.data.network.ApiClient;
import com.google.gson.Gson;
import android.content.Context;
import android.net.Uri;
import okhttp3.MultipartBody;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\n2\u0006\u0010\f\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0011\u0010\u000eJ,\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\n2\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\nH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\nH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001e\u0010\u001bJ,\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\n2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\"\u0010\u0017J$\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\n2\u0006\u0010%\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b&\u0010\u000eJ$\u0010\'\u001a\b\u0012\u0004\u0012\u00020$0\n2\u0006\u0010\f\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b(\u0010\u000eJ,\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\n2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b,\u0010\u0017J,\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\n2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b/\u0010\u0017J,\u00100\u001a\b\u0012\u0004\u0012\u00020$0\n2\u0006\u0010%\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b1\u0010\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u00062"}, d2 = {"Lcom/atolow/miixs/data/repository/AuthRepository;", "", "()V", "TAG", "", "authApi", "Lcom/atolow/miixs/data/network/AuthApi;", "gson", "Lcom/google/gson/Gson;", "checkResendAvailability", "Lkotlin/Result;", "Lcom/atolow/miixs/data/model/auth/ResendCooldownResponseDto;", "email", "checkResendAvailability-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findPassword", "Lcom/atolow/miixs/data/model/auth/FindPasswordResponseDto;", "findPassword-gIAlu-s", "login", "Lcom/atolow/miixs/data/model/auth/LoginResponseDto;", "username", "password", "login-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logout", "Lcom/atolow/miixs/data/model/auth/LogoutResponseDto;", "logout-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshToken", "Lcom/atolow/miixs/data/model/auth/RefreshTokenResponseDto;", "refreshToken-IoAF18A", "resetPassword", "Lcom/atolow/miixs/data/model/auth/ResetPasswordResponseDto;", "newPassword", "resetPassword-0E7RQCE", "sendPhoneVerificationCode", "", "phoneNumber", "sendPhoneVerificationCode-gIAlu-s", "sendVerificationCode", "sendVerificationCode-gIAlu-s", "verifyEmail", "Lcom/atolow/miixs/data/model/auth/EmailVerificationResponseDto;", "code", "verifyEmail-0E7RQCE", "verifyPasswordResetCode", "Lcom/atolow/miixs/data/model/auth/VerifyPasswordResetCodeResponseDto;", "verifyPasswordResetCode-0E7RQCE", "verifyPhoneCode", "verifyPhoneCode-0E7RQCE", "app_debug"})
public final class AuthRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.atolow.miixs.data.network.AuthApi authApi = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "AuthRepository";
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    
    public AuthRepository() {
        super();
    }
}