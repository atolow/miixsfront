package com.atolow.miixs.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.atolow.miixs.R
import com.atolow.miixs.data.local.NotificationSettings
import com.atolow.miixs.data.local.TokenManager
import com.atolow.miixs.databinding.FragmentProfileBinding
import com.atolow.miixs.ui.auth.LoginActivity
import com.atolow.miixs.ui.profile.PrivacyPolicyActivity
import com.atolow.miixs.ui.profile.TermsOfServiceActivity
import com.atolow.miixs.ui.profile.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // NotificationSettings 초기화
        NotificationSettings.init(requireContext())
        
        setupClickListeners()
        setupObservers()
        setupPushNotificationToggle()
        viewModel.loadDashboard()
    }
    
    override fun onResume() {
        super.onResume()
        // 프로필 수정 화면에서 돌아왔을 때 목록 새로고침
        viewModel.loadDashboard()
    }
    
    private fun setupClickListeners() {
        binding.cardProfileHeader.setOnClickListener {
            // 프로필 수정 화면으로 이동
            val intent = Intent(requireContext(), ProfileEditActivity::class.java)
            startActivity(intent)
        }
        
        binding.llPayment.setOnClickListener {
            // 결제하기 화면으로 이동
            val intent = Intent(requireContext(), com.atolow.miixs.ui.payment.PaymentActivity::class.java)
            startActivity(intent)
        }
        
        binding.llBlockedUsers.setOnClickListener {
            // 차단한 회원 목록 화면으로 이동
            val intent = Intent(requireContext(), BlockedUsersActivity::class.java)
            startActivity(intent)
        }
        
        binding.llLogout.setOnClickListener {
            showLogoutDialog()
        }
        
        binding.llDeactivate.setOnClickListener {
            showDeactivateDialog()
        }
        
        binding.llContact.setOnClickListener {
            sendEmail()
        }
        
        // 새로운 채팅 금지 토글 클릭 리스너
        binding.llBlockNewChats.setOnClickListener {
            binding.switchBlockNewChats.isChecked = !binding.switchBlockNewChats.isChecked
        }
        
        // 새로운 채팅 금지 토글 변경 리스너
        binding.switchBlockNewChats.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateBlockNewChats(isChecked)
            val message = if (isChecked) "새로운 채팅 금지가 활성화되었습니다" else "새로운 채팅 금지가 비활성화되었습니다"
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
        
        binding.llTermsOfService.setOnClickListener {
            // 서비스 이용약관 화면으로 이동
            val intent = Intent(requireContext(), TermsOfServiceActivity::class.java)
            startActivity(intent)
        }
        
        binding.llPrivacyPolicy.setOnClickListener {
            // 개인정보 취급방법 화면으로 이동
            val intent = Intent(requireContext(), PrivacyPolicyActivity::class.java)
            startActivity(intent)
        }
        
        // 푸시알림 토글 클릭 리스너 (LinearLayout 클릭 시 토글 변경)
        binding.llPushNotification.setOnClickListener {
            binding.switchPushNotification.isChecked = !binding.switchPushNotification.isChecked
        }
        
        // 푸시알림 토글 변경 리스너
        binding.switchPushNotification.setOnCheckedChangeListener { _, isChecked ->
            NotificationSettings.setPushNotificationEnabled(isChecked)
            val message = if (isChecked) "푸시알림이 활성화되었습니다" else "푸시알림이 비활성화되었습니다"
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun setupPushNotificationToggle() {
        // 저장된 설정값으로 토글 상태 설정
        val isEnabled = NotificationSettings.isPushNotificationEnabled()
        binding.switchPushNotification.isChecked = isEnabled
    }
    
    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.dashboard.collect { dashboard ->
                dashboard?.let {
                    // 프로필 이미지 (null이거나 빈 문자열이면 기본 이미지 표시)
                    if (it.profileImageUrl.isNullOrEmpty()) {
                        binding.ivProfileImage.setImageResource(R.drawable.bg_profile_circle)
                    } else {
                        Glide.with(this@ProfileFragment)
                            .load(it.profileImageUrl)
                            .circleCrop()
                            .placeholder(R.drawable.bg_profile_circle)
                            .error(R.drawable.bg_profile_circle)
                            .into(binding.ivProfileImage)
                    }
                    
                    // 닉네임 (name 우선 사용, 없으면 username)
                    binding.tvUsername.text = it.name ?: it.username ?: "닉네임 없음"
                    
                    // 지역
                    binding.tvLocation.text = it.location?.displayName ?: "지역 미설정"
                    
                    // 성별 / 나이
                    val genderText = when (it.gender) {
                        com.atolow.miixs.data.model.Gender.MALE -> "남성"
                        com.atolow.miixs.data.model.Gender.FEMALE -> "여성"
                        else -> ""
                    }
                    val ageText = if (it.age != null) "${it.age}세" else ""
                    binding.tvGenderAge.text = "$genderText / $ageText".trimStart('/').trim()
                    
                    // 자기소개
                    binding.tvBio.text = it.bio ?: "자기소개를 입력해주세요"
                    
                    // 믹시즈 캐시 표시
                    val cashAmount = it.miixsCash ?: 0L
                    binding.tvMiixsCash.text = "${cashAmount} 믹시즈"
                    
                    // 새로운 채팅 금지 토글 상태 설정
                    binding.switchBlockNewChats.isChecked = it.blockNewChats ?: false
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                // 로딩 상태 처리
            }
        }
        
        lifecycleScope.launch {
            viewModel.error.collect { error ->
                error?.let {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.logoutResult.collect { result ->
                result?.let { res ->
                    res.fold(
                        onSuccess = {
                            Toast.makeText(requireContext(), "로그아웃되었습니다.", Toast.LENGTH_SHORT).show()
                            // 로그인 화면으로 이동
                            val intent = Intent(requireContext(), LoginActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                            startActivity(intent)
                            requireActivity().finish()
                            viewModel.clearLogoutResult()
                        },
                        onFailure = { error ->
                            Toast.makeText(requireContext(), error.message ?: "로그아웃 실패", Toast.LENGTH_SHORT).show()
                            viewModel.clearLogoutResult()
                        }
                    )
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.deactivateResult.collect { result ->
                result?.let { res ->
                    res.fold(
                        onSuccess = {
                            Toast.makeText(requireContext(), "회원탈퇴가 완료되었습니다.", Toast.LENGTH_SHORT).show()
                            // 회원탈퇴 후 로그아웃 처리
                            viewModel.logout()
                            viewModel.clearDeactivateResult()
                        },
                        onFailure = { error ->
                            Toast.makeText(requireContext(), error.message ?: "회원탈퇴 실패", Toast.LENGTH_SHORT).show()
                            viewModel.clearDeactivateResult()
                        }
                    )
                }
            }
        }
    }
    
    private fun showLogoutDialog() {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("로그아웃")
            .setMessage("정말 로그아웃 하시겠습니까?")
            .setPositiveButton("로그아웃") { _, _ ->
                viewModel.logout()
            }
            .setNegativeButton("취소", null)
            .show()
    }
    
    private fun showDeactivateDialog() {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("회원탈퇴")
            .setMessage("정말 회원탈퇴를 하시겠습니까? 이 작업은 되돌릴 수 없습니다.")
            .setPositiveButton("탈퇴") { _, _ ->
                viewModel.deactivate()
            }
            .setNegativeButton("취소", null)
            .show()
    }
    
    private fun sendEmail() {
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("admin@miixs.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Miixs 문의")
            putExtra(Intent.EXTRA_TEXT, "문의 내용을 입력해주세요.\n\n")
        }
        
        try {
            startActivity(Intent.createChooser(emailIntent, "이메일 앱 선택"))
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "이메일 앱을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
