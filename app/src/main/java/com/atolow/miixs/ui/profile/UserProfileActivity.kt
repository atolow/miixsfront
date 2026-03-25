package com.atolow.miixs.ui.profile

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.atolow.miixs.R
import com.atolow.miixs.data.local.TokenManager
import com.atolow.miixs.databinding.ActivityUserProfileBinding
import com.atolow.miixs.ui.chat.ChatRoomActivity
import com.atolow.miixs.ui.profile.adapter.ProfileImagePagerAdapter
import com.atolow.miixs.ui.profile.viewmodel.UserProfileViewModel
import com.atolow.miixs.util.ChatNotificationManager
import kotlinx.coroutines.launch

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    private val viewModel: UserProfileViewModel by viewModels()
    private lateinit var imagePagerAdapter: ProfileImagePagerAdapter
    private val indicators = mutableListOf<ImageView>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val userId = intent.getLongExtra("userId", -1L)
        if (userId == -1L) {
            Toast.makeText(this, "사용자 정보를 불러올 수 없습니다", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        setupToolbar()
        setupViewPager()
        setupClickListeners()
        setupObservers()
        
        viewModel.loadUserProfile(userId)
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupViewPager() {
        imagePagerAdapter = ProfileImagePagerAdapter()
        binding.viewPagerProfileImages.adapter = imagePagerAdapter
        
        // ViewPager2 페이지 변경 리스너 추가
        binding.viewPagerProfileImages.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateIndicators(position)
            }
        })
        
        // 전체 컨테이너 영역에서 스와이프 가능하도록 터치 이벤트 전달
        setupSwipeArea()
    }
    
    private fun setupSwipeArea() {
        // ViewPager2의 내부 RecyclerView 찾기
        val recyclerView = binding.viewPagerProfileImages.getChildAt(0) as? androidx.recyclerview.widget.RecyclerView
        
        // 부모 LinearLayout의 터치 이벤트를 ViewPager2의 RecyclerView로 전달
        binding.llProfileImageContainer.setOnTouchListener { _, event ->
            recyclerView?.onTouchEvent(event) ?: false
        }
    }
    
    private fun setupClickListeners() {
        binding.ivMore.setOnClickListener {
            showMoreMenuDialog()
        }
        
        binding.btnSendMessage.setOnClickListener {
            android.util.Log.d("UserProfileActivity", "메시지 보내기 버튼 클릭")
            viewModel.profile.value?.let { profile ->
                android.util.Log.d("UserProfileActivity", "채팅방 생성: otherUserId=${profile.id}, otherUserName=${profile.name ?: profile.username}")
                // 바로 채팅방 생성 및 이동
                viewModel.createChatRoom(profile.id)
            } ?: run {
                android.util.Log.e("UserProfileActivity", "프로필 정보가 null입니다")
                Toast.makeText(this, "프로필 정보를 불러올 수 없습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun showMoreMenuDialog() {
        val options = arrayOf("차단하기", "신고하기")
        
        AlertDialog.Builder(this)
            .setItems(options) { _, which ->
                when (which) {
                    0 -> showBlockConfirmDialog()
                    1 -> showReportConfirmDialog()
                }
            }
            .show()
    }
    
    private fun isOwnProfile(): Boolean {
        val currentUserId = TokenManager.getUserId()
        val profileUserId = viewModel.profile.value?.id
        return currentUserId != -1L && profileUserId != null && currentUserId == profileUserId
    }
    
    private fun showBlockConfirmDialog() {
        // 자기 자신인지 확인
        if (isOwnProfile()) {
            Toast.makeText(this, "자기 자신은 차단할 수 없습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        
        AlertDialog.Builder(this)
            .setTitle("차단하기")
            .setMessage("이 사용자를 차단하시겠습니까? 차단된 사용자는 더 이상 보이지 않습니다.")
            .setPositiveButton("차단") { _, _ ->
                viewModel.blockUser()
            }
            .setNegativeButton("취소", null)
            .show()
    }
    
    private fun showReportConfirmDialog() {
        // 자기 자신인지 확인
        if (isOwnProfile()) {
            Toast.makeText(this, "자기 자신은 신고할 수 없습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        
        // 신고 사유 입력 다이얼로그 표시
        val input = android.widget.EditText(this)
        input.hint = "신고 사유를 입력해주세요"
        input.maxLines = 5
        input.minLines = 3
        
        AlertDialog.Builder(this)
            .setTitle("신고하기")
            .setMessage("이 사용자를 신고하시겠습니까? 신고 사유를 입력해주세요.")
            .setView(input)
            .setPositiveButton("신고") { _, _ ->
                val reason = input.text.toString().trim()
                if (reason.isEmpty()) {
                    Toast.makeText(this, "신고 사유를 입력해주세요.", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                viewModel.reportUser(reason)
            }
            .setNegativeButton("취소", null)
            .show()
    }
    
    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.error.collect { error ->
                error?.let {
                    Toast.makeText(this@UserProfileActivity, it, Toast.LENGTH_SHORT).show()
                    viewModel.clearError()
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.profile.collect { profile ->
                profile?.let {
                    updateProfileUI(it)
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.blockResult.collect { result ->
                result?.fold(
                    onSuccess = {
                        Toast.makeText(this@UserProfileActivity, "사용자를 차단했습니다", Toast.LENGTH_SHORT).show()
                        finish()
                    },
                    onFailure = { e ->
                        Toast.makeText(this@UserProfileActivity, "차단 실패: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                )
                viewModel.clearBlockResult()
            }
        }
        
        lifecycleScope.launch {
            viewModel.reportResult.collect { result ->
                result?.fold(
                    onSuccess = {
                        Toast.makeText(this@UserProfileActivity, "신고가 접수되었습니다", Toast.LENGTH_SHORT).show()
                    },
                    onFailure = { e ->
                        Toast.makeText(this@UserProfileActivity, "신고 실패: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                )
                viewModel.clearReportResult()
            }
        }
        
        lifecycleScope.launch {
            viewModel.createChatRoomResult.collect { result ->
                result?.let { res ->
                    res.onSuccess { chatRoomResponse ->
                        // 새 채팅방에 대한 알림 구독 추가
                        val profile = viewModel.profile.value
                        val otherUserName = profile?.name ?: profile?.username ?: "알 수 없음"
                        ChatNotificationManager.subscribeToNewChatRoom(
                            this@UserProfileActivity,
                            chatRoomResponse.chatRoomId,
                            otherUserName
                        )
                        
                        // 채팅방으로 이동
                        val intent = Intent(this@UserProfileActivity, ChatRoomActivity::class.java)
                        intent.putExtra("chatRoomId", chatRoomResponse.chatRoomId)
                        startActivity(intent)
                    }.onFailure { error ->
                        android.util.Log.e("UserProfileActivity", "채팅방 생성 실패", error)
                        val errorMessage = error.message ?: "채팅방 생성에 실패했습니다"
                        Toast.makeText(this@UserProfileActivity, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                    viewModel.clearCreateChatRoomResult()
                }
            }
        }
    }
    
    private fun updateProfileUI(profile: com.atolow.miixs.data.model.user.ProfileResponseDto) {
        // 이름 설정
        binding.toolbar.title = "${profile.name ?: profile.username}님의 프로필"
        
        // 이름
        binding.tvName.text = profile.name ?: profile.username
        
        // 지역, 나이, 성별
        val locationText = profile.location?.displayName ?: ""
        val ageText = profile.age?.let { "${it}살" } ?: ""
        val genderText = when (profile.gender) {
            com.atolow.miixs.data.model.Gender.MALE -> "남자"
            com.atolow.miixs.data.model.Gender.FEMALE -> "여자"
            else -> ""
        }
        
        val locationAgeGenderText = listOfNotNull(locationText, ageText, genderText)
            .joinToString(", ")
        binding.tvLocationAgeGender.text = locationAgeGenderText.ifEmpty { "정보 없음" }
        
        // 자기소개
        if (profile.bio.isNullOrEmpty()) {
            binding.tvBio.text = "아직 자기소개가 없어요"
            binding.tvBio.setTextColor(getColor(android.R.color.darker_gray))
        } else {
            binding.tvBio.text = profile.bio
            binding.tvBio.setTextColor(getColor(R.color.black))
        }
        
        // 마지막 로그인 시간 (현재는 createdAt 사용)
        binding.tvLastLogin.text = "방금전에 마지막으로 로그인"
        
        // 프로필 이미지 설정 - profileImageUrls 우선 사용, 없으면 profileImageUrl 사용
        val imageUrls = profile.profileImageUrls?.filterNotNull()?.ifEmpty { null }
            ?: profile.profileImageUrl?.let { listOf(it) }
            ?: emptyList()
        
        imagePagerAdapter.updateImages(imageUrls)
        
        // 인디케이터 설정
        setupIndicators(imageUrls.size)
        
        // 첫 번째 인디케이터 활성화
        if (imageUrls.isNotEmpty()) {
            updateIndicators(0)
        }
    }
    
    private fun setupIndicators(count: Int) {
        // 기존 인디케이터 제거
        binding.llIndicators.removeAllViews()
        indicators.clear()
        
        if (count <= 0) {
            binding.llIndicators.visibility = View.GONE
            return
        }
        
        // 인디케이터 표시
        binding.llIndicators.visibility = View.VISIBLE
        
        // 동그라미 인디케이터 생성
        val sizeInDp = 8 // 8dp
        val marginInDp = 4 // 4dp
        val density = resources.displayMetrics.density
        val size = (sizeInDp * density).toInt()
        val margin = (marginInDp * density).toInt()
        
        for (i in 0 until count) {
            val indicator = ImageView(this)
            val layoutParams = android.widget.LinearLayout.LayoutParams(size, size)
            layoutParams.setMargins(margin, 0, margin, 0)
            indicator.layoutParams = layoutParams
            
            // 동그라미 모양 drawable 생성
            val drawable = GradientDrawable()
            drawable.shape = GradientDrawable.OVAL
            
            // 초기 상태는 모두 회색
            drawable.setColor(ContextCompat.getColor(this, android.R.color.darker_gray))
            indicator.background = drawable
            
            indicators.add(indicator)
            binding.llIndicators.addView(indicator)
        }
    }
    
    private fun updateIndicators(selectedPosition: Int) {
        indicators.forEachIndexed { index, indicator ->
            val drawable = indicator.background as? GradientDrawable
            if (drawable != null) {
                if (index == selectedPosition) {
                    // 선택된 인디케이터는 흰색
                    drawable.setColor(ContextCompat.getColor(this, android.R.color.white))
                } else {
                    // 선택되지 않은 인디케이터는 회색
                    drawable.setColor(ContextCompat.getColor(this, android.R.color.darker_gray))
                }
            }
        }
    }
}

