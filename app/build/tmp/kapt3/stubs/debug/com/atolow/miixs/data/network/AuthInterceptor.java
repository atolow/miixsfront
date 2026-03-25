package com.atolow.miixs.data.network;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.atolow.miixs.data.local.TokenManager;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.json.JSONObject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/atolow/miixs/data/network/AuthInterceptor;", "Lokhttp3/Interceptor;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "app_debug"})
public final class AuthInterceptor implements okhttp3.Interceptor {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "AuthInterceptor";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_TOKEN_EXPIRED = "com.atolow.miixs.TOKEN_EXPIRED";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_USER_BANNED = "com.atolow.miixs.USER_BANNED";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_BAN_MESSAGE = "ban_message";
    @org.jetbrains.annotations.NotNull()
    public static final com.atolow.miixs.data.network.AuthInterceptor.Companion Companion = null;
    
    public AuthInterceptor(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public okhttp3.Response intercept(@org.jetbrains.annotations.NotNull()
    okhttp3.Interceptor.Chain chain) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/atolow/miixs/data/network/AuthInterceptor$Companion;", "", "()V", "ACTION_TOKEN_EXPIRED", "", "ACTION_USER_BANNED", "EXTRA_BAN_MESSAGE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}