package com.atolow.miixs.ui.post

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.atolow.miixs.R
import com.atolow.miixs.data.local.TokenManager
import com.atolow.miixs.databinding.ActivityPostDetailBinding
import com.atolow.miixs.ui.auth.LoginActivity
import com.atolow.miixs.ui.chat.ChatRoomActivity
import com.atolow.miixs.ui.post.viewmodel.PostDetailViewModel
import kotlinx.coroutines.launch

class PostDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostDetailBinding
    private val viewModel: PostDetailViewModel by viewModels()
    private var postId: Long = -1L
    private var isMyPost: Boolean = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 토큰이 없으면 로그인 화면으로 이동
        if (!TokenManager.isLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }
        
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        postId = intent.getLongExtra("postId", -1L)
        if (postId == -1L) {
            Toast.makeText(this, "게시글 정보가 없습니다", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        setupToolbar()
        setupClickListeners()
        setupObservers()
        viewModel.loadPost(postId)
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isMyPost) {
            menuInflater.inflate(R.menu.menu_post_detail, menu)
        }
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.menu_edit -> {
                // TODO: 게시글 수정 화면으로 이동
                true
            }
            R.id.menu_delete -> {
                showDeleteConfirmDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    private fun setupClickListeners() {
        binding.btnSendMessage.setOnClickListener {
            android.util.Log.d("PostDetailActivity", "메시지 보내기 버튼 클릭")
            viewModel.post.value?.let { post ->
android.util.Log.d("PostDetailActivity", "채팅방 생성: otherUserId=${post.authorId}, otherUserName=${post.authorName}")
                // 바로 채팅방 생성 및 이동
                viewModel.createChatRoom(post.authorId)
            } ?: run {
                android.util.Log.e("PostDetailActivity", "게시물 정보가 null입니다")
                Toast.makeText(this, "게시물 정보를 불러올 수 없습니다", Toast.LENGTH_SHORT).show()
            }
        }
        
        // 작성자 이름 클릭 시 프로필 화면으로 이동
        binding.tvAuthorName.setOnClickListener {
            viewModel.post.value?.let { post ->
                val isDeactivated = post.authorName == "탈퇴된 회원입니다"
                if (!isDeactivated) {
                    val intent = Intent(this, com.atolow.miixs.ui.profile.UserProfileActivity::class.java).apply {
                        putExtra("userId", post.authorId)
                    }
                    startActivity(intent)
                }
            }
        }
    }
    
    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.post.collect { post ->
                post?.let {
                    android.util.Log.d("PostDetailActivity", "게시물 로드 완료: postId=${it.postId}, authorId=${it.authorId}, authorName=${it.authorName}")
                    
                    binding.tvAuthorName.text = it.authorName
                    binding.tvContent.text = it.content
                    binding.tvCreatedAt.text = formatDateTime(it.createdAt)
                    
                    val currentUserId = TokenManager.getUserId()
                    isMyPost = it.authorId == currentUserId
                    
                    android.util.Log.d("PostDetailActivity", "isMyPost=$isMyPost, currentUserId=$currentUserId, authorId=${it.authorId}")
                    
                    // 메시지 보내기 버튼 항상 표시
                    binding.btnSendMessage.visibility = android.view.View.VISIBLE
                    binding.btnSendMessage.isEnabled = true
                    
                    invalidateOptionsMenu()
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading) android.view.View.VISIBLE else android.view.View.GONE
            }
        }
        
        lifecycleScope.launch {
            viewModel.error.collect { error ->
                error?.let {
                    Toast.makeText(this@PostDetailActivity, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.createChatRoomResult.collect { result ->
                result?.let { res ->
                    res.onSuccess { chatRoomResponse ->
                        // 새 채팅방에 대한 알림 구독 추가
                        val authorName = viewModel.post.value?.authorName ?: "알 수 없음"
                        com.atolow.miixs.util.ChatNotificationManager.subscribeToNewChatRoom(
                            this@PostDetailActivity,
                            chatRoomResponse.chatRoomId,
                            authorName
                        )
                        
                        // 채팅방으로 이동
                        val intent = Intent(this@PostDetailActivity, ChatRoomActivity::class.java)
                        intent.putExtra("chatRoomId", chatRoomResponse.chatRoomId)
                        startActivity(intent)
                    }.onFailure { error ->
                        val errorMessage = error.message ?: "채팅방 생성 실패"
                        Toast.makeText(this@PostDetailActivity, errorMessage, Toast.LENGTH_LONG).show()
                        android.util.Log.e("PostDetailActivity", "채팅방 생성 실패", error)
                    }
                }
            }
        }
    }
    
    private fun showDeleteConfirmDialog() {
        AlertDialog.Builder(this)
            .setTitle("게시글 삭제")
            .setMessage("정말 삭제하시겠습니까?")
            .setPositiveButton("삭제") { _, _ ->
                viewModel.deletePost(postId)
                finish()
            }
            .setNegativeButton("취소", null)
            .show()
    }
    
    private fun formatDateTime(dateTime: String?): String {
        if (dateTime == null) return ""
        return try {
            dateTime.substring(0, 16).replace("T", " ")
        } catch (e: Exception) {
            dateTime
        }
    }
}

