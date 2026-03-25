package com.atolow.miixs.ui.common

/**
 * Result 래퍼 클래스 - 성공/실패 상태를 명확하게 표현
 */
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
    
    inline fun <R> fold(
        onSuccess: (T) -> R,
        onError: (Throwable) -> R
    ): R {
        return when (this) {
            is Success -> onSuccess(data)
            is Error -> onError(exception)
            is Loading -> throw IllegalStateException("Loading state cannot be folded")
        }
    }
    
    inline fun onSuccess(action: (T) -> Unit): Result<T> {
        if (this is Success) action(data)
        return this
    }
    
    inline fun onError(action: (Throwable) -> Unit): Result<T> {
        if (this is Error) action(exception)
        return this
    }
    
    inline fun onLoading(action: () -> Unit): Result<T> {
        if (this is Loading) action()
        return this
    }
}

