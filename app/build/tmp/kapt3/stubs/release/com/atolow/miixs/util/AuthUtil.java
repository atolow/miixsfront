package com.atolow.miixs.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.atolow.miixs.data.local.TokenManager;
import com.atolow.miixs.ui.auth.LoginActivity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/atolow/miixs/util/AuthUtil;", "", "()V", "TAG", "", "checkAndHandleTokenExpiration", "", "context", "Landroid/content/Context;", "handleTokenExpiration", "", "app_release"})
public final class AuthUtil {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "AuthUtil";
    @org.jetbrains.annotations.NotNull()
    public static final com.atolow.miixs.util.AuthUtil INSTANCE = null;
    
    private AuthUtil() {
        super();
    }
    
    /**
     * 뤾쿃 筌띾슢利????癒?짗 嚥≪뮄??袁⑹뜍 筌ｌ꼶??獄?嚥≪뮄????遺얇늺??곗쨮 ??猷?
     * 筌뤴뫀諭?Activity?癒?퐣 ?紐꾪뀱 揶쎛??
     */
    public final void handleTokenExpiration(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    /**
     * ?醫뤾쿃??筌띾슢利??뤿??遺? ?類ㅼ뵥??랁? 筌띾슢利??野껋럩??嚥≪뮄????遺얇늺??곗쨮 ??猷?
     * @return true if token is expired and navigation was triggered, false otherwise
     */
    public final boolean checkAndHandleTokenExpiration(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
}