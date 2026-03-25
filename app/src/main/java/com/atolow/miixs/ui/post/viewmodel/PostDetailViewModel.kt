package com.atolow.miixs.ui.post.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atolow.miixs.data.model.chat.CreateChatRoomResponseDto
import com.atolow.miixs.data.model.post.PostResponseDto
import com.atolow.miixs.data.repository.ChatRepository
import com.atolow.miixs.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostDetailViewModel : ViewModel() {
    private val postRepository = PostRepository()
    private val chatRepository = ChatRepository()
    
    private val _post = MutableStateFlow<PostResponseDto?>(null)
    val post: StateFlow<PostResponseDto?> = _post
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    
    private val _createChatRoomResult = MutableStateFlow<kotlin.Result<CreateChatRoomResponseDto>?>(null)
    val createChatRoomResult: StateFlow<kotlin.Result<CreateChatRoomResponseDto>?> = _createChatRoomResult
    
    fun loadPost(postId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            postRepository.getPost(postId).onSuccess { post ->
                _post.value = post
                _isLoading.value = false
            }.onFailure { exception ->
                _error.value = exception.message ?: "게시글 조회 실패"
                _isLoading.value = false
            }
        }
    }
    
    fun deletePost(postId: Long) {
        viewModelScope.launch {
            postRepository.deletePost(postId).onFailure { exception ->
                _error.value = exception.message ?: "게시글 삭제 실패"
            }
        }
    }
    
    fun createChatRoom(otherUserId: Long) {
        viewModelScope.launch {
            android.util.Log.d("PostDetailViewModel", "채팅방 생성 시작: otherUserId=$otherUserId")
            _createChatRoomResult.value = null
            
            chatRepository.createChatRoom(otherUserId).onSuccess { chatRoomResponse ->
                android.util.Log.d("PostDetailViewModel", "채팅방 생성 성공: chatRoomId=${chatRoomResponse.chatRoomId}, otherUserId=${chatRoomResponse.otherUserId}")
                _createChatRoomResult.value = kotlin.Result.success(chatRoomResponse)
            }.onFailure { exception ->
                android.util.Log.e("PostDetailViewModel", "채팅방 생성 실패: otherUserId=$otherUserId", exception)
                android.util.Log.e("PostDetailViewModel", "예외 메시지: ${exception.message}")
                exception.printStackTrace()
                _createChatRoomResult.value = kotlin.Result.failure(exception)
            }
        }
    }
}

