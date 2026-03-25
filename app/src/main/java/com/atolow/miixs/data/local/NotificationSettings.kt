package com.atolow.miixs.data.local

import android.content.Context
import android.content.SharedPreferences

object NotificationSettings {
    private const val PREFS_NAME = "miixs_prefs"
    private const val KEY_PUSH_NOTIFICATION_ENABLED = "push_notification_enabled"
    
    private lateinit var prefs: SharedPreferences
    
    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    
    fun isPushNotificationEnabled(): Boolean {
        // 기본값은 true (알림 활성화)
        return prefs.getBoolean(KEY_PUSH_NOTIFICATION_ENABLED, true)
    }
    
    fun setPushNotificationEnabled(enabled: Boolean) {
        prefs.edit()
            .putBoolean(KEY_PUSH_NOTIFICATION_ENABLED, enabled)
            .apply()
    }
}

