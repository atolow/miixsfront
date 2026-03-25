package com.atolow.miixs.ui.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atolow.miixs.data.model.user.BlockedUserDto
import com.atolow.miixs.data.repository.BlockRepository
import com.atolow.miixs.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BlockedUsersViewModel : ViewModel() {
    private val userRepository = UserRepository()
    private val blockRepository = BlockRepository()
    
    private val _blockedUsers = MutableStateFlow<List<BlockedUserDto>>(emptyList())
    val blockedUsers: StateFlow<List<BlockedUserDto>> = _blockedUsers
    
    private val _unblockResult = MutableStateFlow<kotlin.Result<Unit>?>(null)
    val unblockResult: StateFlow<kotlin.Result<Unit>?> = _unblockResult
    
    fun loadBlockedUsers() {
        viewModelScope.launch {
            android.util.Log.d("BlockedUsersViewModel", "차단한 회원 목록 로드 시작")
            userRepository.getDashboard().fold(
                onSuccess = { dashboard ->
                    val blockedUsersList = dashboard.blockedUsers?.content ?: emptyList()
                    android.util.Log.d("BlockedUsersViewModel", "차단한 회원 목록 로드 성공: ${blockedUsersList.size}명")
                    _blockedUsers.value = blockedUsersList
                },
                onFailure = { exception ->
                    // 에러 발생 시 빈 리스트로 설정하여 빈 상태 메시지 표시
                    android.util.Log.e("BlockedUsersViewModel", "차단한 회원 목록 로드 실패", exception)
                    _blockedUsers.value = emptyList()
                }
            )
        }
    }
    
    fun unblockUser(blockedUserId: Long) {
        viewModelScope.launch {
            blockRepository.unblockUser(blockedUserId).fold(
                onSuccess = {
                    _unblockResult.value = Result.success(Unit)
                    // 차단 해제 후 목록 다시 로드
                    loadBlockedUsers()
                },
                onFailure = { exception ->
                    _unblockResult.value = Result.failure(exception)
                }
            )
        }
    }
    
    fun clearUnblockResult() {
        _unblockResult.value = null
    }
}

