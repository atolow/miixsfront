package com.atolow.miixs.ui.chat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atolow.miixs.data.local.TokenManager
import com.atolow.miixs.data.model.chat.ChatRoomResponseDto
import com.atolow.miixs.data.model.chat.MessageResponseDto
import com.atolow.miixs.data.model.user.ProfileResponseDto
import com.atolow.miixs.data.repository.BlockRepository
import com.atolow.miixs.data.repository.ChatRepository
import com.atolow.miixs.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatRoomViewModel : ViewModel() {
    private val chatRepository = ChatRepository()
    private val userRepository = UserRepository()
    private val blockRepository = BlockRepository()
    
    private val _messages = MutableStateFlow<List<MessageResponseDto>>(emptyList())
    val messages: StateFlow<List<MessageResponseDto>> = _messages
    
    private val _otherUserProfile = MutableStateFlow<ProfileResponseDto?>(null)
    val otherUserProfile: StateFlow<ProfileResponseDto?> = _otherUserProfile
    
    private val _chatRoomInfo = MutableStateFlow<ChatRoomResponseDto?>(null)
    val chatRoomInfo: StateFlow<ChatRoomResponseDto?> = _chatRoomInfo
    
    private val _isFirstMessage = MutableStateFlow(false)
    val isFirstMessage: StateFlow<Boolean> = _isFirstMessage
    
    private val _isOtherUserLeft = MutableStateFlow(false)
    val isOtherUserLeft: StateFlow<Boolean> = _isOtherUserLeft
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    
    fun getCurrentUserId(): Long {
        return TokenManager.getUserId()
    }
    
    fun loadChatRoomInfo(chatRoomId: Long) {
        viewModelScope.launch {
            // 채팅방 목록에서 해당 채팅방 찾기
            chatRepository.getChatRoomList().onSuccess { pageResponse ->
                val chatRoom = pageResponse.content.find { it.chatRoomId == chatRoomId }
                chatRoom?.let {
                    _chatRoomInfo.value = it
                    // 상대방 프로필 정보 가져오기
                    loadOtherUserProfile(it.otherUserId)
                    
                    // 차단 관계 확인
                    checkBlockRelationship(it.otherUserId)
                }
            }
        }
    }
    
    private fun checkBlockRelationship(otherUserId: Long) {
        viewModelScope.launch {
            // 차단 관계 확인
            // 현재 사용자가 상대방을 차단했는지 확인
            blockRepository.getBlockedUsers().fold(
                onSuccess = { blockedUsers ->
                    val isBlocked = blockedUsers.any { it.blockedUserId == otherUserId }
                    if (isBlocked) {
                        _isOtherUserLeft.value = true
                    }
                },
                onFailure = {
                    // 차단 목록 조회 실패 시 무시
                }
            )
        }
    }
    
    fun toggleFavorite(chatRoomId: Long, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val currentFavoriteState = _chatRoomInfo.value?.isFavorite ?: false
            android.util.Log.d("ChatRoomViewModel", "toggleFavorite 시작: chatRoomId=$chatRoomId, 현재 상태=$currentFavoriteState")
            
            chatRepository.toggleFavorite(chatRoomId).onSuccess {
                android.util.Log.d("ChatRoomViewModel", "toggleFavorite API 호출 성공, 채팅방 정보 다시 로드 중...")
                // 채팅방 정보 다시 로드하여 즐겨찾기 상태 업데이트
                chatRepository.getChatRoomList().onSuccess { pageResponse ->
                    val chatRoom = pageResponse.content.find { it.chatRoomId == chatRoomId }
                    chatRoom?.let {
                        android.util.Log.d("ChatRoomViewModel", "채팅방 정보 로드 성공: chatRoomId=${it.chatRoomId}, isFavorite=${it.isFavorite}, 이전 상태=$currentFavoriteState")
                        val oldValue = _chatRoomInfo.value
                        _chatRoomInfo.value = it
                        android.util.Log.d("ChatRoomViewModel", "chatRoomInfo StateFlow 업데이트 완료: ${oldValue?.isFavorite} -> ${it.isFavorite}")
                        // 실제 서버에서 받은 즐겨찾기 상태를 콜백으로 전달
                        onResult(it.isFavorite)
                    } ?: run {
                        android.util.Log.w("ChatRoomViewModel", "채팅방을 찾을 수 없음, 토글된 상태로 가정")
                        // 채팅방을 찾을 수 없으면 토글된 상태로 가정
                        onResult(!currentFavoriteState)
                    }
                }.onFailure { exception ->
                    android.util.Log.e("ChatRoomViewModel", "채팅방 정보 로드 실패", exception)
                    // 로드 실패 시 토글된 상태로 가정
                    onResult(!currentFavoriteState)
                }
            }.onFailure { exception ->
                android.util.Log.e("ChatRoomViewModel", "toggleFavorite API 호출 실패", exception)
                _error.value = exception.message ?: "즐겨찾기 토글 실패"
            }
        }
    }
    
    fun markAllMessagesAsRead(chatRoomId: Long) {
        viewModelScope.launch {
            val messages = _messages.value
            if (messages.isNotEmpty()) {
                val currentUserId = getCurrentUserId()
                // 상대방이 보낸 읽지 않은 모든 메시지를 찾아서 읽음 처리
                val unreadMessagesFromOther = messages.filter { 
                    it.senderId != currentUserId && !it.isRead
                }
                
                if (unreadMessagesFromOther.isNotEmpty()) {
                    // 가장 최신 메시지를 읽음 처리 (백엔드에서 자동으로 이전 메시지도 처리)
                    val lastUnreadMessage = unreadMessagesFromOther.last()
                    android.util.Log.d("ChatRoomViewModel", "읽지 않은 메시지 ${unreadMessagesFromOther.size}개 발견, 마지막 메시지 ID: ${lastUnreadMessage.messageId} 읽음 처리 시작")
                    chatRepository.markMessageAsRead(chatRoomId, lastUnreadMessage.messageId).onSuccess {
                        android.util.Log.d("ChatRoomViewModel", "읽음 처리 성공, 채팅방 정보 다시 로드 중")
                        // 채팅방 정보 다시 로드하여 읽지 않은 메시지 개수 업데이트
                        loadChatRoomInfo(chatRoomId)
                    }.onFailure { exception ->
                        android.util.Log.e("ChatRoomViewModel", "읽음 처리 실패", exception)
                    }
                } else {
                    android.util.Log.d("ChatRoomViewModel", "읽지 않은 메시지가 없습니다")
                }
            }
        }
    }
    
    private fun loadOtherUserProfile(otherUserId: Long) {
        viewModelScope.launch {
            // 채팅방 목록에서 otherUserId로 username 찾기
            chatRepository.getChatRoomList().onSuccess { pageResponse ->
                val chatRoom = pageResponse.content.find { it.otherUserId == otherUserId }
                chatRoom?.let {
                    // username으로 프로필 조회 (간단하게 이름만 표시)
                    // 실제로는 백엔드에 userId로 프로필 조회 API가 필요하지만,
                    // 일단 채팅방 정보만 사용
                }
            }
        }
    }
    
    fun loadMessages(chatRoomId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            chatRepository.getMessages(chatRoomId).onSuccess { pageResponse ->
                val messageList = pageResponse.content.reversed() // 최신 메시지가 아래로
                _messages.value = messageList
                
                // 첫 메시지 전송 여부 확인
                // 현재 사용자가 보낸 메시지가 없고, 상대방도 보낸 메시지가 없을 때만 첫 메시지로 간주
                // 상대방이 먼저 메시지를 보냈다면, 내가 보낼 때는 50 miixsCash를 소모하지 않음
                val currentUserId = getCurrentUserId()
                val hasSentMessage = messageList.any { it.senderId == currentUserId }
                val hasReceivedMessage = messageList.any { it.senderId != currentUserId }
                
                // 현재 사용자가 보낸 메시지가 없고, 상대방도 보낸 메시지가 없을 때만 첫 메시지
                _isFirstMessage.value = !hasSentMessage && !hasReceivedMessage
                
                // 상대방이 나갔는지 확인 (SYSTEM 메시지 중 "상대방이 채팅방을 나갔습니다" 메시지 확인)
                val hasLeaveSystemMessage = messageList.any { message ->
                    message.messageType.name == "SYSTEM" && 
                    message.content.contains("채팅방을 나갔습니다") &&
                    message.senderId != currentUserId // 상대방이 나간 경우
                }
                
                // 차단 관계도 확인
                if (!hasLeaveSystemMessage) {
                    // 메시지에서 상대방 ID 추출하여 차단 관계 확인
                    val otherUserId = messageList.firstOrNull { it.senderId != currentUserId }?.senderId
                    if (otherUserId != null) {
                        checkBlockRelationship(otherUserId)
                    }
                } else {
                    _isOtherUserLeft.value = true
                }
                
                // 상대방 정보 추출 (메시지에서)
                if (messageList.isNotEmpty()) {
                    val otherUserMessage = messageList.firstOrNull { it.senderId != currentUserId }
                    otherUserMessage?.let {
                        // 상대방 이름은 메시지에서 가져올 수 있음
                        // 프로필 이미지는 별도로 조회 필요
                    }
                }
                
                _isLoading.value = false
            }.onFailure { exception ->
                _error.value = exception.message ?: "메시지 로드 실패"
                _isLoading.value = false
            }
        }
    }
    
    fun sendMessage(chatRoomId: Long, content: String) {
        viewModelScope.launch {
            // 메시지 길이 체크 (200자 제한)
            if (content.length > 200) {
                _error.value = "최대 200글자 이하만 작성할 수 있습니다"
                return@launch
            }
            
            // miixsCash 확인 (첫 메시지인 경우 50 이상 필요)
            if (_isFirstMessage.value) {
                userRepository.getProfile().onSuccess { profile ->
                    if (profile.miixsCash < 50) {
                        _error.value = "50 믹시즈가 없어서 메시지를 보낼 수 없습니다"
                        return@onSuccess
                    }
                    // 충분한 캐시가 있으면 메시지 전송
                    sendMessageInternal(chatRoomId, content)
                }.onFailure {
                    _error.value = "프로필 정보를 불러올 수 없습니다"
                }
            } else {
                // 첫 메시지가 아니면 바로 전송
                sendMessageInternal(chatRoomId, content)
            }
        }
    }
    
    private fun sendMessageInternal(chatRoomId: Long, content: String) {
        viewModelScope.launch {
            chatRepository.sendMessage(chatRoomId, content).onSuccess { message ->
                // 메시지를 즉시 UI에 추가 (optimistic update)
                addMessage(message)
                // 첫 메시지 플래그 업데이트
                if (_isFirstMessage.value) {
                    _isFirstMessage.value = false
                }
            }.onFailure { exception ->
                val errorMessage = exception.message ?: "메시지 전송 실패"
                // 백엔드에서 캐시 부족 에러인 경우
                if (errorMessage.contains("캐시", ignoreCase = true) || 
                    errorMessage.contains("miixsCash", ignoreCase = true) ||
                    errorMessage.contains("부족", ignoreCase = true) ||
                    errorMessage.contains("50", ignoreCase = false)) {
                    _error.value = "50 믹시즈가 없어서 메시지를 보낼 수 없습니다"
                } else if (errorMessage.contains("차단", ignoreCase = true) || 
                           errorMessage.contains("나갔습니다", ignoreCase = true)) {
                    // 차단 또는 나가기 에러인 경우 메시지 입력 폼 숨기기
                    _isOtherUserLeft.value = true
                    _error.value = errorMessage
                } else {
                    _error.value = errorMessage
                }
            }
        }
    }
    
    fun addMessage(message: MessageResponseDto) {
        val currentMessages = _messages.value.toMutableList()
        if (!currentMessages.any { it.messageId == message.messageId }) {
            currentMessages.add(message)
            _messages.value = currentMessages
            
            // 첫 메시지 플래그 업데이트
            // 현재 사용자가 보낸 메시지가 없고, 상대방도 보낸 메시지가 없을 때만 첫 메시지로 간주
            val currentUserId = getCurrentUserId()
            val hasSentMessage = currentMessages.any { it.senderId == currentUserId && it.messageType.name != "SYSTEM" }
            val hasReceivedMessage = currentMessages.any { it.senderId != currentUserId && it.messageType.name != "SYSTEM" }
            
            // 현재 사용자가 보낸 메시지가 없고, 상대방도 보낸 메시지가 없을 때만 첫 메시지
            _isFirstMessage.value = !hasSentMessage && !hasReceivedMessage
            
            // 시스템 메시지 확인 (상대방이 나갔거나 차단당한 경우)
            val hasLeaveOrBlockSystemMessage = currentMessages.any { msg ->
                msg.messageType.name == "SYSTEM" && 
                (msg.content.contains("채팅방을 나갔습니다") || 
                 msg.content.contains("차단당했습니다") ||
                 msg.content.contains("차단된 사용자")) &&
                msg.senderId != currentUserId
            }
            
            // 시스템 메시지가 있으면 차단/나가기 상태로 설정
            if (hasLeaveOrBlockSystemMessage) {
                _isOtherUserLeft.value = true
                android.util.Log.d("ChatRoomViewModel", "시스템 메시지 감지: 상대방이 나갔거나 차단됨")
            } else {
                // 시스템 메시지가 없어도 차단 관계 확인
                val otherUserId = _chatRoomInfo.value?.otherUserId
                if (otherUserId != null) {
                    checkBlockRelationship(otherUserId)
                }
            }
        }
    }
    
    fun leaveChatRoom(chatRoomId: Long, onResult: (Result<Unit>) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            chatRepository.leaveChatRoom(chatRoomId).onSuccess {
                _isLoading.value = false
                onResult(Result.success(Unit))
            }.onFailure { exception ->
                _isLoading.value = false
                _error.value = exception.message ?: "채팅방 나가기 실패"
                onResult(Result.failure(exception))
            }
        }
    }
    
    fun sendImageMessage(chatRoomId: Long, imagePath: String, onResult: (Result<MessageResponseDto>) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            chatRepository.sendImageMessage(chatRoomId, imagePath).onSuccess { message ->
                // 메시지를 즉시 UI에 추가 (optimistic update)
                addMessage(message)
                // 첫 메시지 플래그 업데이트
                if (_isFirstMessage.value) {
                    _isFirstMessage.value = false
                }
                _isLoading.value = false
                onResult(Result.success(message))
            }.onFailure { exception ->
                val errorMessage = exception.message ?: "이미지 전송 실패"
                // 백엔드에서 캐시 부족 에러인 경우
                if (errorMessage.contains("캐시", ignoreCase = true) || 
                    errorMessage.contains("miixsCash", ignoreCase = true) ||
                    errorMessage.contains("부족", ignoreCase = true) ||
                    errorMessage.contains("50", ignoreCase = false)) {
                    _error.value = "50 믹시즈가 없어서 메시지를 보낼 수 없습니다"
                } else if (errorMessage.contains("차단", ignoreCase = true) || 
                           errorMessage.contains("나갔습니다", ignoreCase = true)) {
                    // 차단 또는 나가기 에러인 경우 메시지 입력 폼 숨기기
                    _isOtherUserLeft.value = true
                    _error.value = errorMessage
                } else {
                    _error.value = errorMessage
                }
                _isLoading.value = false
                onResult(Result.failure(exception))
            }
        }
    }
}

