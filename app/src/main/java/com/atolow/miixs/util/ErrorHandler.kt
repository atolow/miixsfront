package com.atolow.miixs.util

import android.content.Context
import android.widget.Toast
import java.net.UnknownHostException

object ErrorHandler {
    fun handleError(context: Context, throwable: Throwable) {
        val message = when (throwable) {
            is UnknownHostException -> "네트워크 연결을 확인해주세요"
            is java.net.SocketTimeoutException -> "요청 시간이 초과되었습니다"
            is java.net.ConnectException -> "서버에 연결할 수 없습니다"
            is retrofit2.HttpException -> {
                when (throwable.code()) {
                    401 -> "인증이 필요합니다"
                    403 -> "권한이 없습니다"
                    404 -> "요청한 리소스를 찾을 수 없습니다"
                    500 -> "서버 오류가 발생했습니다"
                    else -> "오류가 발생했습니다 (${throwable.code()})"
                }
            }
            else -> throwable.message ?: "알 수 없는 오류가 발생했습니다"
        }
        
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    
    fun showMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    
    fun showSuccessMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

