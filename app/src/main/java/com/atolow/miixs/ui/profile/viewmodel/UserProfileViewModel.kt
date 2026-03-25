package com.atolow.miixs.ui.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atolow.miixs.data.model.block.BlockResponseDto
import com.atolow.miixs.data.model.chat.CreateChatRoomResponseDto
import com.atolow.miixs.data.model.post.PostResponseDto
import com.atolow.miixs.data.model.report.ReportResponseDto
import com.atolow.miixs.data.model.user.ProfileResponseDto
import com.atolow.miixs.data.repository.BlockRepository
import com.atolow.miixs.data.repository.ChatRepository
import com.atolow.miixs.data.repository.PostRepository
import com.atolow.miixs.data.repository.ReportRepository
import com.atolow.miixs.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserProfileViewModel : ViewModel() {
    private val userRepository = UserRepository()
    private val blockRepository = BlockRepository()
    private val reportRepository = ReportRepository()
    private val postRepository = PostRepository()
    private val chatRepository = ChatRepository()
    
    private val _profile = MutableStateFlow<ProfileResponseDto?>(null)
    val profile: StateFlow<ProfileResponseDto?> = _profile
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    
    private val _blockResult = MutableStateFlow<kotlin.Result<BlockResponseDto>?>(null)
    val blockResult: StateFlow<kotlin.Result<BlockResponseDto>?> = _blockResult
    
    private val _reportResult = MutableStateFlow<kotlin.Result<ReportResponseDto>?>(null)
    val reportResult: StateFlow<kotlin.Result<ReportResponseDto>?> = _reportResult
    
    private val _createChatRoomResult = MutableStateFlow<kotlin.Result<CreateChatRoomResponseDto>?>(null)
    val createChatRoomResult: StateFlow<kotlin.Result<CreateChatRoomResponseDto>?> = _createChatRoomResult
    
    fun loadUserProfile(userId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            userRepository.getUserProfile(userId).fold(
                onSuccess = { profile ->
                    _profile.value = profile
                },
                onFailure = { e ->
                    _error.value = e.message ?: "프로필 정보를 불러오는데 실패했습니다"
                }
            )
            _isLoading.value = false
        }
    }
    
    fun blockUser() {
        val profile = _profile.value ?: return
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _blockResult.value = blockRepository.blockUser(profile.id)
            _isLoading.value = false
        }
    }
    
    fun reportUser(reason: String) {
        val profile = _profile.value ?: return
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            // 유저 직접 신고
            _reportResult.value = reportRepository.reportUser(profile.id, reason)
            _isLoading.value = false
        }
    }
    
    fun clearBlockResult() {
        _blockResult.value = null
    }
    
    fun clearReportResult() {
        _reportResult.value = null
    }
    
    fun createChatRoom(otherUserId: Long) {
        viewModelScope.launch {
            android.util.Log.d("UserProfileViewModel", "채팅방 생성 시작: otherUserId=$otherUserId")
            _createChatRoomResult.value = null
            
            chatRepository.createChatRoom(otherUserId).onSuccess { chatRoomResponse ->
                android.util.Log.d("UserProfileViewModel", "채팅방 생성 성공: chatRoomId=${chatRoomResponse.chatRoomId}, otherUserId=${chatRoomResponse.otherUserId}")
                _createChatRoomResult.value = kotlin.Result.success(chatRoomResponse)
            }.onFailure { exception ->
                android.util.Log.e("UserProfileViewModel", "채팅방 생성 실패: otherUserId=$otherUserId", exception)
                android.util.Log.e("UserProfileViewModel", "예외 메시지: ${exception.message}")
                exception.printStackTrace()
                _createChatRoomResult.value = kotlin.Result.failure(exception)
            }
        }
    }
    
    fun clearCreateChatRoomResult() {
        _createChatRoomResult.value = null
    }
    
    fun clearError() {
        _error.value = null
    }
}

