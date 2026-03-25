package com.atolow.miixs.ui.chat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atolow.miixs.data.model.chat.ChatRoomResponseDto
import com.atolow.miixs.data.repository.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatListViewModel : ViewModel() {
    private val repository = ChatRepository()
    
    private val _favorites = MutableStateFlow<List<ChatRoomResponseDto>>(emptyList())
    val favorites: StateFlow<List<ChatRoomResponseDto>> = _favorites
    
    private val _chatRooms = MutableStateFlow<List<ChatRoomResponseDto>>(emptyList())
    val chatRooms: StateFlow<List<ChatRoomResponseDto>> = _chatRooms
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    
    fun loadFavorites() {
        viewModelScope.launch {
            android.util.Log.d("ChatListViewModel", "즐겨찾기 목록 로드 시작")
            repository.getFavoriteChatRoomList().onSuccess { pageResponse ->
                android.util.Log.d("ChatListViewModel", "즐겨찾기 목록 로드 성공: ${pageResponse.content.size}개")
                _favorites.value = pageResponse.content
            }.onFailure { exception ->
                android.util.Log.e("ChatListViewModel", "즐겨찾기 목록 로드 실패", exception)
                _favorites.value = emptyList()
            }
        }
    }
    
    fun loadChatRooms() {
        viewModelScope.launch {
            android.util.Log.d("ChatListViewModel", "전체 채팅방 목록 로드 시작")
            _isLoading.value = true
            repository.getChatRoomList().onSuccess { pageResponse ->
                android.util.Log.d("ChatListViewModel", "전체 채팅방 목록 로드 성공: ${pageResponse.content.size}개")
                _chatRooms.value = pageResponse.content
                _isLoading.value = false
            }.onFailure { exception ->
                android.util.Log.e("ChatListViewModel", "전체 채팅방 목록 로드 실패", exception)
                exception.printStackTrace()
                _chatRooms.value = emptyList()
                _isLoading.value = false
            }
        }
    }
    
    fun leaveAllChatRoomsExceptFavorites(onResult: (Result<Int>) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // 즐겨찾기 채팅방 ID 목록
                val favoriteIds = _favorites.value.map { it.chatRoomId }.toSet()
                
                // 즐겨찾기가 아닌 모든 채팅방 필터링
                val chatRoomsToLeave = _chatRooms.value.filter { it.chatRoomId !in favoriteIds }
                
                android.util.Log.d("ChatListViewModel", "나갈 채팅방 수: ${chatRoomsToLeave.size}개 (즐겨찾기 제외)")
                
                if (chatRoomsToLeave.isEmpty()) {
                    _isLoading.value = false
                    onResult(Result.success(0))
                    return@launch
                }
                
                // 각 채팅방에 대해 나가기 API 호출
                var successCount = 0
                var failureCount = 0
                
                chatRoomsToLeave.forEach { chatRoom ->
                    repository.leaveChatRoom(chatRoom.chatRoomId).fold(
                        onSuccess = {
                            successCount++
                            android.util.Log.d("ChatListViewModel", "채팅방 나가기 성공: ${chatRoom.chatRoomId}")
                        },
                        onFailure = { exception ->
                            failureCount++
                            android.util.Log.e("ChatListViewModel", "채팅방 나가기 실패: ${chatRoom.chatRoomId}", exception)
                        }
                    )
                }
                
                _isLoading.value = false
                
                // 성공한 채팅방이 있으면 목록 새로고침
                if (successCount > 0) {
                    loadChatRooms()
                }
                
                if (failureCount > 0) {
                    onResult(Result.failure(Exception("일부 채팅방 나가기에 실패했습니다. (${successCount}개 성공, ${failureCount}개 실패)")))
                } else {
                    onResult(Result.success(successCount))
                }
            } catch (e: Exception) {
                _isLoading.value = false
                android.util.Log.e("ChatListViewModel", "모든 채팅방 나가기 중 예외 발생", e)
                onResult(Result.failure(e))
            }
        }
    }
}

