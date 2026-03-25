package com.atolow.miixs.ui.auth.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atolow.miixs.data.model.auth.FindPasswordResponseDto
import com.atolow.miixs.data.model.auth.ResetPasswordResponseDto
import com.atolow.miixs.data.model.auth.VerifyPasswordResetCodeResponseDto
import com.atolow.miixs.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FindPasswordViewModel : ViewModel() {
    private val repository = AuthRepository()
    
    private val _findPasswordResult = MutableStateFlow<Result<FindPasswordResponseDto>?>(null)
    val findPasswordResult: StateFlow<Result<FindPasswordResponseDto>?> = _findPasswordResult
    
    private val _verifyCodeResult = MutableStateFlow<Result<VerifyPasswordResetCodeResponseDto>?>(null)
    val verifyCodeResult: StateFlow<Result<VerifyPasswordResetCodeResponseDto>?> = _verifyCodeResult
    
    private val _resetPasswordResult = MutableStateFlow<Result<ResetPasswordResponseDto>?>(null)
    val resetPasswordResult: StateFlow<Result<ResetPasswordResponseDto>?> = _resetPasswordResult
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    
    fun findPassword(email: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _findPasswordResult.value = repository.findPassword(email)
            _isLoading.value = false
        }
    }
    
    fun verifyPasswordResetCode(email: String, code: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _verifyCodeResult.value = repository.verifyPasswordResetCode(email, code)
            _isLoading.value = false
        }
    }
    
    fun resetPassword(email: String, newPassword: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _resetPasswordResult.value = repository.resetPassword(email, newPassword)
            _isLoading.value = false
        }
    }
}

