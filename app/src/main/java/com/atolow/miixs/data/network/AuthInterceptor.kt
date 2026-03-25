package com.atolow.miixs.data.network

import android.content.Context
import android.content.Intent
import android.util.Log
import com.atolow.miixs.data.local.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject

class AuthInterceptor(private val context: Context) : Interceptor {
    private val TAG = "AuthInterceptor"
    
    companion object {
        const val ACTION_TOKEN_EXPIRED = "com.atolow.miixs.TOKEN_EXPIRED"
        const val ACTION_USER_BANNED = "com.atolow.miixs.USER_BANNED"
        const val EXTRA_BAN_MESSAGE = "ban_message"
    }
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val token = TokenManager.getAccessToken()
        
        Log.d(TAG, "요청 URL: ${request.url}")
        Log.d(TAG, "토큰 존재 여부: ${token != null}")
        Log.d(TAG, "토큰: ${if (token != null) "${token.take(20)}..." else "null"}")
        
        val newRequest = if (token != null) {
            request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            Log.w(TAG, "토큰이 없어서 Authorization 헤더를 추가하지 않습니다")
            request
        }
        
        // 헤더 확인
        val authHeader = newRequest.header("Authorization")
        Log.d(TAG, "Authorization 헤더: ${authHeader?.take(30) ?: "없음"}")
        
        val response = chain.proceed(newRequest)
        
        // 401 Unauthorized 발생 시 토큰 삭제 및 브로드캐스트 전송
        if (response.code == 401) {
            Log.w(TAG, "401 Unauthorized 응답 받음 - 토큰 삭제 및 로그인 필요")
            // 토큰이 만료되었거나 유효하지 않으므로 삭제
            TokenManager.clear()
            // 브로드캐스트로 토큰 만료 알림
            val intent = Intent(ACTION_TOKEN_EXPIRED)
            context.sendBroadcast(intent)
        }
        
        // 403 Forbidden 발생 시 (정지된 사용자) 토큰 삭제 및 브로드캐스트 전송
        if (response.code == 403) {
            Log.w(TAG, "403 Forbidden 응답 받음 - 사용자 정지 확인")
            try {
                // 에러 메시지 추출
                val errorBody = response.peekBody(2048).string()
                val banMessage = try {
                    val jsonObject = JSONObject(errorBody)
                    jsonObject.getString("message") ?: "정지된 사용자입니다"
                } catch (e: Exception) {
                    Log.e(TAG, "에러 메시지 파싱 실패", e)
                    "정지된 사용자입니다"
                }
                
                // 새로운 채팅 금지 관련 메시지인지 확인 (정지가 아닌 경우)
                if (banMessage.contains("새로운 채팅", ignoreCase = true) || 
                    banMessage.contains("채팅 금지", ignoreCase = true) ||
                    banMessage.contains("받고 있지 않습니다", ignoreCase = true)) {
                    // 새로운 채팅 금지는 정지가 아니므로 처리하지 않음
                    Log.d(TAG, "새로운 채팅 금지 메시지 - 정지 처리 건너뜀: $banMessage")
                    return response
                }
                
                // 정지된 사용자인 경우에만 처리
                Log.w(TAG, "정지된 사용자로 확인됨: $banMessage")
                // 토큰 삭제
                TokenManager.clear()
                
                // 브로드캐스트로 정지 알림 (메시지 포함)
                val intent = Intent(ACTION_USER_BANNED).apply {
                    putExtra(EXTRA_BAN_MESSAGE, banMessage)
                }
                context.sendBroadcast(intent)
            } catch (e: Exception) {
                Log.e(TAG, "403 에러 처리 실패", e)
                // 에러 메시지 추출 실패 시 기본 메시지 사용
                TokenManager.clear()
                val intent = Intent(ACTION_USER_BANNED).apply {
                    putExtra(EXTRA_BAN_MESSAGE, "정지된 사용자입니다")
                }
                context.sendBroadcast(intent)
            }
        }
        
        return response
    }
}

