package com.atolow.miixs.ui.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atolow.miixs.data.model.auth.LogoutResponseDto
import com.atolow.miixs.data.model.user.DashboardResponseDto
import com.atolow.miixs.data.repository.AuthRepository
import com.atolow.miixs.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val repository = UserRepository()
    private val authRepository = AuthRepository()
    
    private val _dashboard = MutableStateFlow<DashboardResponseDto?>(null)
    val dashboard: StateFlow<DashboardResponseDto?> = _dashboard
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    
    private val _logoutResult = MutableStateFlow<kotlin.Result<LogoutResponseDto>?>(null)
    val logoutResult: StateFlow<kotlin.Result<LogoutResponseDto>?> = _logoutResult
    
    fun loadDashboard() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            repository.getDashboard().fold(
                onSuccess = { dashboard ->
                    _dashboard.value = dashboard
                },
                onFailure = { e ->
                    _error.value = e.message ?: "프로필 정보를 불러오는데 실패했습니다"
                }
            )
            _isLoading.value = false
        }
    }
    
    fun logout() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _logoutResult.value = authRepository.logout()
            _isLoading.value = false
        }
    }
    
    fun clearLogoutResult() {
        _logoutResult.value = null
    }
    
    private val _deactivateResult = MutableStateFlow<kotlin.Result<Unit>?>(null)
    val deactivateResult: StateFlow<kotlin.Result<Unit>?> = _deactivateResult
    
    fun deactivate() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            repository.deactivate().fold(
                onSuccess = {
                    _deactivateResult.value = Result.success(Unit)
                },
                onFailure = { e ->
                    _error.value = e.message ?: "회원탈퇴에 실패했습니다"
                    _deactivateResult.value = Result.failure(e)
                }
            )
            _isLoading.value = false
        }
    }
    
    fun clearDeactivateResult() {
        _deactivateResult.value = null
    }
    
    fun updateBlockNewChats(blockNewChats: Boolean) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            repository.updateBlockNewChats(blockNewChats).fold(
                onSuccess = {
                    // 성공 시 대시보드 다시 로드하여 최신 상태 반영
                    loadDashboard()
                    val message = if (blockNewChats) "새로운 채팅 금지가 활성화되었습니다" else "새로운 채팅 금지가 비활성화되었습니다"
                    // Toast는 Fragment에서 처리하도록 하기 위해 error에 메시지 전달하지 않음
                },
                onFailure = { e ->
                    _error.value = e.message ?: "채팅 설정 업데이트에 실패했습니다"
                }
            )
            _isLoading.value = false
        }
    }
}

