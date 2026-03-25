package com.atolow.miixs.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import org.json.JSONObject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0004H\u0002J\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001aJ\u0016\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004J\u0016\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/atolow/miixs/data/local/TokenManager;", "", "()V", "KEY_ACCESS_TOKEN", "", "KEY_REFRESH_TOKEN", "KEY_USERNAME", "KEY_USER_ID", "PREFS_NAME", "TAG", "prefs", "Landroid/content/SharedPreferences;", "clear", "", "clearTokens", "extractUserInfoFromToken", "token", "getAccessToken", "getRefreshToken", "getUserId", "", "getUsername", "init", "context", "Landroid/content/Context;", "isLoggedIn", "", "isTokenExpired", "saveTokens", "accessToken", "refreshToken", "saveUserInfo", "userId", "username", "app_debug"})
public final class TokenManager {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "miixs_prefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ACCESS_TOKEN = "access_token";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_REFRESH_TOKEN = "refresh_token";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_USER_ID = "user_id";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_USERNAME = "username";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "TokenManager";
    private static android.content.SharedPreferences prefs;
    @org.jetbrains.annotations.NotNull()
    public static final com.atolow.miixs.data.local.TokenManager INSTANCE = null;
    
    private TokenManager() {
        super();
    }
    
    public final void init(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void saveTokens(@org.jetbrains.annotations.NotNull()
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull()
    java.lang.String refreshToken) {
    }
    
    /**
     * JWT 토큰에서 사용자 ID와 username 추출
     */
    private final void extractUserInfoFromToken(java.lang.String token) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAccessToken() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRefreshToken() {
        return null;
    }
    
    public final void saveUserInfo(long userId, @org.jetbrains.annotations.NotNull()
    java.lang.String username) {
    }
    
    public final long getUserId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUsername() {
        return null;
    }
    
    public final void clear() {
    }
    
    public final void clearTokens() {
    }
    
    public final boolean isLoggedIn() {
        return false;
    }
    
    /**
     * JWT 토큰의 만료 여부를 확인합니다.
     * @return true if token is expired or doesn't exist, false otherwise
     */
    public final boolean isTokenExpired() {
        return false;
    }
}