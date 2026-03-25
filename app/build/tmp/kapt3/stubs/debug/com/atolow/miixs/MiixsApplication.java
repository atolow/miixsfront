package com.atolow.miixs;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.util.Log;
import com.atolow.miixs.data.local.NotificationSettings;
import com.atolow.miixs.data.local.TokenManager;
import com.atolow.miixs.data.network.AuthInterceptor;
import com.atolow.miixs.data.websocket.WebSocketManager;
import com.atolow.miixs.ui.auth.LoginActivity;
import com.atolow.miixs.util.NotificationHelper;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016\u00a8\u0006\u0006"}, d2 = {"Lcom/atolow/miixs/MiixsApplication;", "Landroid/app/Application;", "()V", "onCreate", "", "Companion", "app_debug"})
public final class MiixsApplication extends android.app.Application {
    @org.jetbrains.annotations.Nullable()
    private static com.atolow.miixs.MiixsApplication instance;
    @org.jetbrains.annotations.NotNull()
    public static final com.atolow.miixs.MiixsApplication.Companion Companion = null;
    
    public MiixsApplication() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/atolow/miixs/MiixsApplication$Companion;", "", "()V", "instance", "Lcom/atolow/miixs/MiixsApplication;", "getInstance", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.atolow.miixs.MiixsApplication getInstance() {
            return null;
        }
    }
}