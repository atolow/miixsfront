package com.atolow.miixs.ui.chat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import android.widget.PopupMenu
import com.atolow.miixs.R
import com.atolow.miixs.data.local.TokenManager
import com.atolow.miixs.data.model.chat.MessageResponseDto
import com.atolow.miixs.data.repository.BlockRepository
import com.atolow.miixs.data.repository.PostRepository
import com.atolow.miixs.data.repository.ReportRepository
import com.atolow.miixs.data.websocket.WebSocketManager
import com.atolow.miixs.databinding.ActivityChatRoomBinding
import com.atolow.miixs.ui.auth.LoginActivity
import com.atolow.miixs.ui.chat.adapter.MessageAdapter
import com.atolow.miixs.ui.chat.viewmodel.ChatRoomViewModel
import com.atolow.miixs.util.ChatNotificationManager
import com.atolow.miixs.util.NotificationHelper
import kotlinx.coroutines.launch

class ChatRoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatRoomBinding
    private val viewModel: ChatRoomViewModel by viewModels()
    private lateinit var messageAdapter: MessageAdapter
    private val webSocketManager = WebSocketManager.getInstance()
    private val blockRepository = BlockRepository()
    private val reportRepository = ReportRepository()
    private val postRepository = PostRepository()
    private var chatRoomId: Long = -1L
    private var otherUserName: String? = null
    private var otherUserId: Long? = null
    private var previousFavoriteState: Boolean? = null
    
    companion object {
        const val REQUEST_CODE_IMAGE_UPLOAD = 1001
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 토큰이 없으면 로그인 화면으로 이동
        if (!TokenManager.isLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }
        
        binding = ActivityChatRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        chatRoomId = intent.getLongExtra("chatRoomId", -1L)
        if (chatRoomId == -1L) {
            Toast.makeText(this, "채팅방 정보가 없습니다", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        setupToolbar()
        setupRecyclerView()
        setupSwipeRefresh()
        setupObservers()
        setupClickListeners()
        setupWindowInsets()
        
        // 초기 하트 상태 설정 (빈 하트)
        updateFavoriteHeart(false)
        
        viewModel.loadChatRoomInfo(chatRoomId)
        viewModel.loadMessages(chatRoomId)
        connectWebSocket()
        
        // 현재 열려있는 채팅방 ID 설정 (이 채팅방의 메시지는 알림 표시 안 함)
        ChatNotificationManager.setCurrentChatRoomId(chatRoomId)
        // 채팅방에 들어오면 해당 채팅방의 모든 알림 지우기
        NotificationHelper.cancelAllNotificationsForChatRoom(this, chatRoomId)
    }
    
    override fun onPause() {
        super.onPause()
        // 채팅방이 백그라운드로 가면 알림 표시 가능하도록 설정
        ChatNotificationManager.setCurrentChatRoomId(null)
    }
    
    override fun onResume() {
        super.onResume()
        // 채팅방이 다시 포그라운드로 오면 알림 표시 안 함
        ChatNotificationManager.setCurrentChatRoomId(chatRoomId)
        // 채팅방에 들어오면 해당 채팅방의 모든 알림 지우기
        NotificationHelper.cancelAllNotificationsForChatRoom(this, chatRoomId)
        // WebSocket 연결 확인 및 재구독
        connectWebSocket()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        // 뒤로가기 버튼 설정
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupRecyclerView() {
        messageAdapter = MessageAdapter(viewModel.getCurrentUserId(), null, null)
        binding.rvMessages.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
        }
        binding.rvMessages.adapter = messageAdapter
    }
    
    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            // 새로고침 시 채팅방 정보와 메시지 다시 로드
            viewModel.loadChatRoomInfo(chatRoomId)
            viewModel.loadMessages(chatRoomId)
        }
        
        // RecyclerView의 스크롤 리스너를 추가하여 SwipeRefreshLayout 활성화 제어
        binding.rvMessages.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // RecyclerView가 맨 위에 있을 때만 SwipeRefreshLayout 활성화
                val layoutManager = recyclerView.layoutManager as? LinearLayoutManager
                val isAtTop = layoutManager?.findFirstCompletelyVisibleItemPosition() == 0
                binding.swipeRefreshLayout.isEnabled = isAtTop
            }
        })
        
        // 초기 상태 설정: RecyclerView가 비어있거나 맨 위에 있을 때 활성화
        binding.swipeRefreshLayout.isEnabled = true
        
        // 로딩 상태에 따라 새로고침 인디케이터 제어
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                if (!isLoading) {
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        }
    }
    
    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.chatRoomInfo.collect { chatRoomInfo ->
                chatRoomInfo?.let {
                    android.util.Log.d("ChatRoomActivity", "chatRoomInfo 업데이트: isFavorite=${it.isFavorite}, previousFavoriteState=$previousFavoriteState")
                    
                    // 툴바에 상대방 username 표시
                    binding.tvOtherUserName.text = it.otherUserName
                    otherUserName = it.otherUserName
                    otherUserId = it.otherUserId
                    
                    // 탈퇴한 회원인지 확인 (닉네임이 "탈퇴된 회원입니다"인 경우)
                    val isDeactivated = it.otherUserName == "탈퇴된 회원입니다"
                    
                    // 툴바 프로필 이미지 로드
                    if (isDeactivated || it.otherUserProfileImageUrl.isNullOrEmpty()) {
                        binding.ivProfile.setImageResource(R.drawable.bg_profile_circle)
                    } else {
                        Glide.with(this@ChatRoomActivity)
                            .load(it.otherUserProfileImageUrl)
                            .circleCrop()
                            .placeholder(R.drawable.bg_profile_circle)
                            .error(R.drawable.bg_profile_circle)
                            .into(binding.ivProfile)
                    }
                    
                    // MessageAdapter에 상대방 프로필 정보 업데이트
                    messageAdapter.updateOtherUserProfile(it.otherUserProfileImageUrl, it.otherUserName)
                    
                    // 즐겨찾기 상태에 따라 하트 아이콘 업데이트
                    updateFavoriteHeart(it.isFavorite)
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.messages.collect { messages ->
                messageAdapter.submitMessages(messages)
                if (messages.isNotEmpty()) {
                    // 어댑터 업데이트 후 스크롤
                    binding.rvMessages.post {
                        val itemCount = messageAdapter.itemCount
                        if (itemCount > 0) {
                            binding.rvMessages.smoothScrollToPosition(itemCount - 1)
                        }
                    }
                }
            }
        }
        
        // 메시지가 로드되면 읽음 처리 (한 번만 실행)
        var hasMarkedAsRead = false
        lifecycleScope.launch {
            viewModel.messages.collect { messages ->
                if (!hasMarkedAsRead && messages.isNotEmpty()) {
                    hasMarkedAsRead = true
                    // 읽지 않은 메시지가 있는지 확인
                    val currentUserId = viewModel.getCurrentUserId()
                    val hasUnreadMessages = messages.any { 
                        it.senderId != currentUserId && !it.isRead 
                    }
                    if (hasUnreadMessages) {
                        viewModel.markAllMessagesAsRead(chatRoomId)
                    }
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.isFirstMessage.collect { isFirstMessage ->
                binding.cardFirstMessageWarning.visibility = if (isFirstMessage) View.VISIBLE else View.GONE
            }
        }
        
        lifecycleScope.launch {
            viewModel.isOtherUserLeft.collect { isOtherUserLeft ->
                // 상대방이 나간 경우 메시지 입력 폼 숨기기
                binding.llMessageInput.visibility = if (isOtherUserLeft) View.GONE else View.VISIBLE
            }
        }
        
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
        
        lifecycleScope.launch {
            viewModel.error.collect { error ->
                error?.let {
                    Toast.makeText(this@ChatRoomActivity, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    private fun setupWindowInsets() {
        // 시스템 네비게이션 바 높이만큼 채팅 입력 영역에 패딩 추가
        ViewCompat.setOnApplyWindowInsetsListener(binding.llMessageInput) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                view.paddingLeft,
                view.paddingTop,
                view.paddingRight,
                systemBars.bottom
            )
            insets
        }
    }
    
    private fun setupClickListeners() {
        // 전송 버튼 클릭
        binding.btnSend.setOnClickListener {
            sendMessage()
        }
        
        // 입력 필드에서 엔터키로 전송
        binding.etMessage.setOnEditorActionListener { _, _, _ ->
            sendMessage()
            true
        }
        
        // 카메라 버튼 클릭 (이미지 업로드 페이지로 이동)
        binding.ivAttach.setOnClickListener {
            val intent = Intent(this, ImageUploadActivity::class.java).apply {
                putExtra("chatRoomId", chatRoomId)
            }
            startActivityForResult(intent, REQUEST_CODE_IMAGE_UPLOAD)
        }
        
        // 더보기 버튼 클릭
        binding.btnMore.setOnClickListener {
            showMoreMenu(it)
        }
        
        // 채팅방 나가기 버튼 클릭
        binding.btnExit.setOnClickListener {
            showLeaveChatRoomDialog()
        }
        
        // 즐겨찾기 하트 클릭
        binding.ivFavoriteHeart.setOnClickListener {
            toggleFavorite()
        }
        
        // 툴바 뒤로가기 버튼
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun updateFavoriteHeart(isFavorite: Boolean) {
        if (isFavorite) {
            // 빨간색 하트로 변경
            binding.ivFavoriteHeart.setImageResource(com.atolow.miixs.R.drawable.ic_heart_filled)
            binding.ivFavoriteHeart.imageTintList = android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#FF0000"))
        } else {
            // 빈 하트로 변경
            binding.ivFavoriteHeart.setImageResource(com.atolow.miixs.R.drawable.ic_heart_outline)
            binding.ivFavoriteHeart.imageTintList = android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#999999"))
        }
    }
    
    private fun toggleFavorite() {
        viewModel.toggleFavorite(chatRoomId) { isFavorite ->
            // 즐겨찾기 상태 업데이트
            updateFavoriteHeart(isFavorite)
            
            // 메시지 표시
            val message = if (isFavorite) {
                "즐겨찾기가 활성화 되었습니다."
            } else {
                "즐겨찾기가 비활성화 되었습니다."
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun showMoreMenu(anchor: View) {
        val popupMenu = PopupMenu(this, anchor)
        popupMenu.menu.add(0, 1, 0, "차단하기")
        popupMenu.menu.add(0, 2, 0, "신고하기")
        
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                1 -> {
                    // 차단하기
                    otherUserId?.let { userId ->
                        showBlockDialog(userId)
                    } ?: run {
                        Toast.makeText(this, "사용자 정보를 불러올 수 없습니다", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                2 -> {
                    // 신고하기
                    otherUserId?.let { userId ->
                        showReportDialog(userId)
                    } ?: run {
                        Toast.makeText(this, "사용자 정보를 불러올 수 없습니다", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
    
    private fun showBlockDialog(userId: Long) {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("차단하기")
            .setMessage("이 사용자를 차단하시겠습니까? 차단된 사용자와의 채팅은 더 이상 불가능합니다.")
            .setPositiveButton("차단") { _, _ ->
                blockUser(userId)
            }
            .setNegativeButton("취소", null)
            .show()
    }
    
    private fun blockUser(userId: Long) {
        lifecycleScope.launch {
            blockRepository.blockUser(userId).fold(
                onSuccess = {
                    Toast.makeText(this@ChatRoomActivity, "사용자를 차단했습니다", Toast.LENGTH_SHORT).show()
                    // 차단 후 채팅방 정보와 메시지 다시 로드하여 상태 업데이트
                    viewModel.loadChatRoomInfo(chatRoomId)
                    viewModel.loadMessages(chatRoomId)
                    // Activity는 종료하지 않고, 메시지 입력 폼만 숨김
                },
                onFailure = { exception ->
                    val errorMessage = exception.message?.removePrefix("차단 실패: ") ?: "차단에 실패했습니다"
                    Toast.makeText(this@ChatRoomActivity, errorMessage, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
    
    private fun showReportDialog(userId: Long) {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("신고하기")
            .setMessage("이 사용자를 신고하시겠습니까?")
            .setPositiveButton("신고") { _, _ ->
                reportUser(userId)
            }
            .setNegativeButton("취소", null)
            .show()
    }
    
    private fun reportUser(userId: Long) {
        lifecycleScope.launch {
            // 사용자의 게시글을 찾아서 신고
            postRepository.getPostList(page = 0, size = 20).fold(
                onSuccess = { pageResponse ->
                    val userPost = pageResponse.content.find { it.authorId == userId }
                    if (userPost != null) {
                        reportRepository.reportPost(userPost.postId, "채팅방에서 신고").fold(
                            onSuccess = {
                                Toast.makeText(this@ChatRoomActivity, "신고가 접수되었습니다", Toast.LENGTH_SHORT).show()
                            },
                            onFailure = { exception ->
                                val errorMessage = exception.message?.removePrefix("신고 실패: ") ?: "신고에 실패했습니다"
                                Toast.makeText(this@ChatRoomActivity, errorMessage, Toast.LENGTH_SHORT).show()
                            }
                        )
                    } else {
                        Toast.makeText(this@ChatRoomActivity, "신고할 게시글을 찾을 수 없습니다", Toast.LENGTH_SHORT).show()
                    }
                },
                onFailure = { exception ->
                    Toast.makeText(this@ChatRoomActivity, "게시글 목록을 불러올 수 없습니다", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
    
    private fun sendMessage() {
        val content = binding.etMessage.text.toString().trim()
        if (content.isEmpty()) {
            return
        }

        // 메시지 길이 체크 (200자 제한)
        if (content.length > 200) {
            Toast.makeText(this, "최대 200글자 이하만 작성할 수 있습니다", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.sendMessage(chatRoomId, content)
        binding.etMessage.text?.clear()
        
        // 메시지 전송 후 맨 아래로 스크롤
        scrollToBottom()
    }
    
    private fun scrollToBottom() {
        binding.rvMessages.post {
            val itemCount = messageAdapter.itemCount
            if (itemCount > 0) {
                binding.rvMessages.smoothScrollToPosition(itemCount - 1)
            }
        }
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_IMAGE_UPLOAD && resultCode == RESULT_OK) {
            // 이미지 업로드가 완료되면 메시지 목록 새로고침
            viewModel.loadMessages(chatRoomId)
            // 맨 아래로 스크롤
            scrollToBottom()
        }
    }
    
    private fun showLeaveChatRoomDialog() {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("채팅방 나가기")
            .setMessage("정말 채팅방을 나가시겠습니까? 나가면 채팅 내역이 삭제됩니다.")
            .setPositiveButton("나가기") { _, _ ->
                leaveChatRoom()
            }
            .setNegativeButton("취소", null)
            .show()
    }
    
    private fun leaveChatRoom() {
        viewModel.leaveChatRoom(chatRoomId) { result ->
            result.onSuccess {
                Toast.makeText(this, "채팅방을 나갔습니다", Toast.LENGTH_SHORT).show()
                finish() // Activity 종료
            }.onFailure { exception ->
                Toast.makeText(this, exception.message ?: "채팅방 나가기 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun connectWebSocket() {
        if (!webSocketManager.isConnected()) {
            webSocketManager.connect()
        }
        
        // 채팅방 메시지 구독
        webSocketManager.subscribeToChatRoom(chatRoomId) { message ->
            android.util.Log.d("ChatRoomActivity", "WebSocket 메시지 수신: messageId=${message.messageId}, type=${message.messageType}, content=${message.content}")
            viewModel.addMessage(message)
            // 새 메시지가 오면 맨 아래로 스크롤
            scrollToBottom()
            
            // 시스템 메시지인 경우 Toast로 알림
            if (message.messageType.name == "SYSTEM") {
                Toast.makeText(this, message.content, Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        // 채팅방을 닫을 때 현재 채팅방 ID 초기화
        ChatNotificationManager.setCurrentChatRoomId(null)
        // WebSocket 연결은 앱 전체에서 유지하므로 여기서는 구독만 해제
    }
}

