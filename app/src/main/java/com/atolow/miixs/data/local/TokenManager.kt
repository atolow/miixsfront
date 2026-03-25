package com.atolow.miixs.data.local

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import android.util.Log
import org.json.JSONObject

object TokenManager {
    private const val PREFS_NAME = "miixs_prefs"
    private const val KEY_ACCESS_TOKEN = "access_token"
    private const val KEY_REFRESH_TOKEN = "refresh_token"
    private const val KEY_USER_ID = "user_id"
    private const val KEY_USERNAME = "username"
    private const val TAG = "TokenManager"
    
    private lateinit var prefs: SharedPreferences
    
    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        
        // 기존 토큰이 있으면 사용자 정보 다시 파싱
        val accessToken = getAccessToken()
        if (accessToken != null && getUserId() == -1L) {
            Log.d(TAG, "기존 토큰에서 사용자 정보 재파싱 시도")
            extractUserInfoFromToken(accessToken)
        }
    }
    
    fun saveTokens(accessToken: String, refreshToken: String) {
        Log.d(TAG, "토큰 저장 시작")
        val success = prefs.edit()
            .putString(KEY_ACCESS_TOKEN, accessToken)
            .putString(KEY_REFRESH_TOKEN, refreshToken)
            .commit()  // apply() 대신 commit() 사용 - 동기적으로 저장 보장
        
        if (!success) {
            Log.e(TAG, "토큰 저장 실패!")
            return
        }
        
        // JWT 토큰에서 사용자 정보 추출
        Log.d(TAG, "JWT 파싱 시작: token=${accessToken.take(50)}...")
        extractUserInfoFromToken(accessToken)
        
        // 저장 후 확인
        val savedUserId = getUserId()
        Log.d(TAG, "토큰 저장 완료, 저장된 userId=$savedUserId")
    }
    
    /**
     * JWT 토큰에서 사용자 ID와 username 추출
     */
    private fun extractUserInfoFromToken(token: String) {
        try {
            Log.d(TAG, "JWT 파싱 시작: token 길이=${token.length}")
            val parts = token.split(".")
            Log.d(TAG, "JWT parts 개수: ${parts.size}")
            
            if (parts.size < 2) {
                Log.e(TAG, "JWT 토큰 형식이 올바르지 않습니다. parts.size=${parts.size}")
                return
            }
            
            // Base64 디코딩 (패딩 추가)
            val payload = parts[1]
            Log.d(TAG, "Payload: ${payload.take(50)}...")
            
            // Base64 디코딩 시도 (패딩이 필요할 수 있음)
            val decodedBytes = try {
                Base64.decode(payload, Base64.URL_SAFE or Base64.NO_WRAP)
            } catch (e: Exception) {
                Log.w(TAG, "URL_SAFE 디코딩 실패, DEFAULT 시도", e)
                // 패딩 추가 후 다시 시도
                val paddedPayload = payload + "=".repeat((4 - payload.length % 4) % 4)
                Base64.decode(paddedPayload, Base64.DEFAULT)
            }
            
            val payloadJson = String(decodedBytes, Charsets.UTF_8)
            Log.d(TAG, "디코딩된 payload: $payloadJson")
            
            val jsonObject = JSONObject(payloadJson)
            
            // 사용자 ID 추출 (백엔드에서 userId 필드 사용)
            val userId = if (jsonObject.has("userId")) {
                jsonObject.getLong("userId")
            } else if (jsonObject.has("sub")) {
                // sub가 숫자일 수도 있음
                val subValue = jsonObject.get("sub")
                if (subValue is Number) {
                    subValue.toLong()
                } else {
                    -1L
                }
            } else {
                -1L
            }
            
            val username = if (jsonObject.has("username")) {
                jsonObject.getString("username")
            } else if (jsonObject.has("sub")) {
                jsonObject.getString("sub")
            } else {
                null
            }
            
            if (userId != -1L) {
                Log.d(TAG, "JWT에서 사용자 정보 추출 성공: userId=$userId, username=$username")
                saveUserInfo(userId, username ?: "")
            } else {
                Log.w(TAG, "JWT에서 사용자 ID를 찾을 수 없습니다. payload: $payloadJson")
                Log.w(TAG, "JSON keys: ${jsonObject.keys().asSequence().toList()}")
            }
        } catch (e: Exception) {
            Log.e(TAG, "JWT 토큰 파싱 실패", e)
            e.printStackTrace()
        }
    }
    
    fun getAccessToken(): String? {
        return prefs.getString(KEY_ACCESS_TOKEN, null)
    }
    
    fun getRefreshToken(): String? {
        return prefs.getString(KEY_REFRESH_TOKEN, null)
    }
    
    fun saveUserInfo(userId: Long, username: String) {
        prefs.edit()
            .putLong(KEY_USER_ID, userId)
            .putString(KEY_USERNAME, username)
            .commit()  // apply() 대신 commit() 사용 - 동기적으로 저장 보장
    }
    
    fun getUserId(): Long {
        return prefs.getLong(KEY_USER_ID, -1L)
    }
    
    fun getUsername(): String? {
        return prefs.getString(KEY_USERNAME, null)
    }
    
    fun clear() {
        prefs.edit().clear().apply()
    }
    
    fun clearTokens() {
        prefs.edit()
            .remove(KEY_ACCESS_TOKEN)
            .remove(KEY_REFRESH_TOKEN)
            .remove(KEY_USER_ID)
            .remove(KEY_USERNAME)
            .apply()
    }
    
    fun isLoggedIn(): Boolean {
        return getAccessToken() != null
    }
    
    /**
     * JWT 토큰의 만료 여부를 확인합니다.
     * @return true if token is expired or doesn't exist, false otherwise
     */
    fun isTokenExpired(): Boolean {
        val accessToken = getAccessToken() ?: return true
        
        return try {
            val parts = accessToken.split(".")
            if (parts.size < 2) {
                Log.w(TAG, "토큰 형식이 올바르지 않습니다.")
                return true
            }
            
            // Base64 디코딩
            val payload = parts[1]
            val decodedBytes = try {
                Base64.decode(payload, Base64.URL_SAFE or Base64.NO_WRAP)
            } catch (e: Exception) {
                val paddedPayload = payload + "=".repeat((4 - payload.length % 4) % 4)
                Base64.decode(paddedPayload, Base64.DEFAULT)
            }
            
            val payloadJson = String(decodedBytes, Charsets.UTF_8)
            val jsonObject = JSONObject(payloadJson)
            
            // exp 클레임 확인 (만료 시간은 초 단위 Unix timestamp)
            if (jsonObject.has("exp")) {
                val exp = jsonObject.getLong("exp")
                val currentTime = System.currentTimeMillis() / 1000 // 초 단위로 변환
                val isExpired = currentTime >= exp
                
                if (isExpired) {
                    Log.d(TAG, "토큰이 만료되었습니다. exp=$exp, current=$currentTime")
                }
                
                isExpired
            } else {
                // exp 클레임이 없으면 만료되지 않은 것으로 간주 (또는 만료된 것으로 간주할 수도 있음)
                Log.w(TAG, "토큰에 exp 클레임이 없습니다.")
                false
            }
        } catch (e: Exception) {
            Log.e(TAG, "토큰 만료 확인 실패", e)
            true // 파싱 실패 시 만료된 것으로 간주
        }
    }
}

