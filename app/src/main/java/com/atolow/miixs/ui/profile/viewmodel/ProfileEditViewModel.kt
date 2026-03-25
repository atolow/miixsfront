package com.atolow.miixs.ui.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atolow.miixs.data.model.user.ProfileResponseDto
import com.atolow.miixs.data.model.user.UpdateProfileRequestDto
import com.atolow.miixs.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileEditViewModel : ViewModel() {
    private val repository = UserRepository()
    
    private val _profile = MutableStateFlow<ProfileResponseDto?>(null)
    val profile: StateFlow<ProfileResponseDto?> = _profile
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    
    private val _updateResult = MutableStateFlow<kotlin.Result<ProfileResponseDto>?>(null)
    val updateResult: StateFlow<kotlin.Result<ProfileResponseDto>?> = _updateResult
    
    fun loadProfile() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getProfile().onSuccess { profile ->
                _profile.value = profile
                _isLoading.value = false
            }.onFailure { exception ->
                _error.value = exception.message ?: "프로필 조회 실패"
                _isLoading.value = false
            }
        }
    }
    
    fun updateProfile(name: String, bio: String, location: com.atolow.miixs.data.model.Location?) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            // 기존 프로필에서 phoneNumber 가져오기
            val currentPhoneNumber = _profile.value?.phoneNumber ?: ""
            val request = UpdateProfileRequestDto(
                name = name,
                phoneNumber = currentPhoneNumber, // 기존 값 유지
                bio = bio,
                location = location
            )
            _updateResult.value = repository.updateProfile(request)
            _isLoading.value = false
        }
    }
    
    fun uploadProfileImage(imagePath: String, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            repository.uploadProfileImage(imagePath).fold(
                onSuccess = { imageUrl ->
                    // 프로필 이미지 URL 업데이트
                    _profile.value?.let { currentProfile ->
                        _profile.value = currentProfile.copy(profileImageUrl = imageUrl)
                    }
                    _isLoading.value = false
                    onComplete(true)
                },
                onFailure = { exception ->
                    _error.value = exception.message ?: "이미지 업로드 실패"
                    _isLoading.value = false
                    onComplete(false)
                }
            )
        }
    }
    
    fun uploadProfileImages(imagePaths: List<String>, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            repository.uploadProfileImages(imagePaths).fold(
                onSuccess = { imageUrls ->
                    // 첫 번째 이미지를 기본 프로필 이미지로 설정
                    if (imageUrls.isNotEmpty()) {
                        _profile.value?.let { currentProfile ->
                            _profile.value = currentProfile.copy(profileImageUrl = imageUrls[0])
                        }
                    }
                    _isLoading.value = false
                    onComplete(true)
                },
                onFailure = { exception ->
                    _error.value = exception.message ?: "이미지 업로드 실패"
                    _isLoading.value = false
                    onComplete(false)
                }
            )
        }
    }
    
    fun clearUpdateResult() {
        _updateResult.value = null
    }
}

