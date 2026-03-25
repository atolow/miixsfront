package com.atolow.miixs.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.atolow.miixs.data.local.TokenManager
import com.atolow.miixs.ui.auth.LoginActivity

object AuthUtil {
    private const val TAG = "AuthUtil"
    
    /**
     * 뤾쿃 筌띾슢利????癒?짗 嚥≪뮄??袁⑹뜍 筌ｌ꼶??獄?嚥≪뮄????遺얇늺??곗쨮 ??猷?
     * 筌뤴뫀諭?Activity?癒?퐣 ?紐꾪뀱 揶쎛??
     */
    fun handleTokenExpiration(context: Context) {
        Log.w(TAG, "?醫뤾쿃 筌띾슢利?揶쏅Ŋ? - ?癒?짗 嚥≪뮄??袁⑹뜍 筌ｌ꼶??")
        
        // ?醫뤾쿃 ????
        TokenManager.clear()
        
        // 嚥≪뮄????遺얇늺??곗쨮 ??猷?
        val loginIntent = Intent(context, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        
        context.startActivity(loginIntent)
        
        // Activity??野껋럩??finish ?紐꾪뀱
        if (context is Activity) {
            context.finish()
        }
    }
    
    /**
     * ?醫뤾쿃??筌띾슢利??뤿??遺? ?類ㅼ뵥??랁? 筌띾슢利??野껋럩??嚥≪뮄????遺얇늺??곗쨮 ??猷?
     * @return true if token is expired and navigation was triggered, false otherwise
     */
    fun checkAndHandleTokenExpiration(context: Context): Boolean {
        if (TokenManager.isTokenExpired()) {
            handleTokenExpiration(context)
            return true
        }
        return false
    }
}

