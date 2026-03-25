package com.atolow.miixs.data.network;

import android.content.Context;
import com.atolow.miixs.MiixsApplication;
import com.atolow.miixs.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0019\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\"\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R#\u0010%\u001a\n \'*\u0004\u0018\u00010&0&8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b*\u0010\u0018\u001a\u0004\b(\u0010)R\u0011\u0010+\u001a\u00020,\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.\u00a8\u0006/"}, d2 = {"Lcom/atolow/miixs/data/network/ApiClient;", "", "()V", "BASE_URL", "", "authApi", "Lcom/atolow/miixs/data/network/AuthApi;", "getAuthApi", "()Lcom/atolow/miixs/data/network/AuthApi;", "blockApi", "Lcom/atolow/miixs/data/network/BlockApi;", "getBlockApi", "()Lcom/atolow/miixs/data/network/BlockApi;", "chatApi", "Lcom/atolow/miixs/data/network/ChatApi;", "getChatApi", "()Lcom/atolow/miixs/data/network/ChatApi;", "loggingInterceptor", "Lokhttp3/logging/HttpLoggingInterceptor;", "okHttpClient", "Lokhttp3/OkHttpClient;", "getOkHttpClient", "()Lokhttp3/OkHttpClient;", "okHttpClient$delegate", "Lkotlin/Lazy;", "paymentApi", "Lcom/atolow/miixs/data/network/PaymentApi;", "getPaymentApi", "()Lcom/atolow/miixs/data/network/PaymentApi;", "postApi", "Lcom/atolow/miixs/data/network/PostApi;", "getPostApi", "()Lcom/atolow/miixs/data/network/PostApi;", "reportApi", "Lcom/atolow/miixs/data/network/ReportApi;", "getReportApi", "()Lcom/atolow/miixs/data/network/ReportApi;", "retrofit", "Lretrofit2/Retrofit;", "kotlin.jvm.PlatformType", "getRetrofit", "()Lretrofit2/Retrofit;", "retrofit$delegate", "userApi", "Lcom/atolow/miixs/data/network/UserApi;", "getUserApi", "()Lcom/atolow/miixs/data/network/UserApi;", "app_release"})
public final class ApiClient {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String BASE_URL = "https://miixs.com/";
    @org.jetbrains.annotations.NotNull()
    private static final okhttp3.logging.HttpLoggingInterceptor loggingInterceptor = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy okHttpClient$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy retrofit$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.atolow.miixs.data.network.AuthApi authApi = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.atolow.miixs.data.network.UserApi userApi = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.atolow.miixs.data.network.ChatApi chatApi = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.atolow.miixs.data.network.PostApi postApi = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.atolow.miixs.data.network.PaymentApi paymentApi = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.atolow.miixs.data.network.BlockApi blockApi = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.atolow.miixs.data.network.ReportApi reportApi = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.atolow.miixs.data.network.ApiClient INSTANCE = null;
    
    private ApiClient() {
        super();
    }
    
    private final okhttp3.OkHttpClient getOkHttpClient() {
        return null;
    }
    
    private final retrofit2.Retrofit getRetrofit() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.atolow.miixs.data.network.AuthApi getAuthApi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.atolow.miixs.data.network.UserApi getUserApi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.atolow.miixs.data.network.ChatApi getChatApi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.atolow.miixs.data.network.PostApi getPostApi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.atolow.miixs.data.network.PaymentApi getPaymentApi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.atolow.miixs.data.network.BlockApi getBlockApi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.atolow.miixs.data.network.ReportApi getReportApi() {
        return null;
    }
}