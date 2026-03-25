package com.atolow.miixs

import android.app.Activity
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.util.Log
import com.atolow.miixs.data.local.NotificationSettings
import com.atolow.miixs.data.local.TokenManager
import com.atolow.miixs.data.network.AuthInterceptor
import com.atolow.miixs.data.websocket.WebSocketManager
import com.atolow.miixs.ui.auth.LoginActivity
import com.atolow.miixs.util.NotificationHelper
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins

class MiixsApplication : Application() {
    companion object {
        private var instance: MiixsApplication? = null
        
        fun getInstance(): MiixsApplication {
            return instance ?: throw IllegalStateException("Application not initialized")
        }
    }
    
    override fun onCreate() {
        super.onCreate()
        instance = this
        TokenManager.init(this)
        NotificationSettings.init(this)

        // RxJava 구독 해제 후 발생하는 UndeliverableException 등이 앱을 죽이지 않도록 처리
        RxJavaPlugins.setErrorHandler { e ->
            if (e is UndeliverableException) {
                Log.w("MiixsApplication", "RxJava undeliverable (disposed/canceled): ${e.cause?.message ?: e.message}")
            } else {
                Log.e("MiixsApplication", "RxJava global error", e)
            }
        }

        // 토큰 만료(401) 시 로그인 화면으로 이동 — 앱 전역에서 수신
        val tokenExpiredReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == AuthInterceptor.ACTION_TOKEN_EXPIRED && context != null) {
                    TokenManager.clear()
                    val loginIntent = Intent(context, LoginActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    }
                    context.startActivity(loginIntent)
                }
            }
        }
        val filter = IntentFilter(AuthInterceptor.ACTION_TOKEN_EXPIRED)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(tokenExpiredReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(tokenExpiredReceiver, filter)
        }
        
        // 알림 채널 생성
        NotificationHelper.createNotificationChannel(this)
        
        // 모든 Activity에 화면 캡처 방지 적용
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                // FLAG_SECURE 설정으로 화면 캡처 및 스크린샷 방지
                activity.window.setFlags(
                    WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE
                )
            }
            
            override fun onActivityStarted(activity: Activity) {
                // Activity가 시작될 때마다 FLAG_SECURE 재확인
                activity.window.setFlags(
                    WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE
                )
            }
            
            override fun onActivityResumed(activity: Activity) {
                // Activity가 재개될 때마다 FLAG_SECURE 재확인
                activity.window.setFlags(
                    WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE
                )
            }
            
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
        
        // 로그인 상태일 경우 WebSocket 연결 시도 (실패해도 앱은 정상 작동)
        if (TokenManager.isLoggedIn()) {
            try {
                WebSocketManager.getInstance().connect()
            } catch (e: Exception) {
                Log.w("MiixsApplication", "WebSocket 연결 실패 (앱은 정상 작동합니다)", e)
            }
        }
    }
}

