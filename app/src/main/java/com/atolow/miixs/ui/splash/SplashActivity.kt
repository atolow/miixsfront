package com.atolow.miixs.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.atolow.miixs.R
import com.atolow.miixs.data.local.TokenManager
import com.atolow.miixs.data.repository.UserRepository
import com.atolow.miixs.ui.auth.LoginActivity
import com.atolow.miixs.ui.auth.OAuthAdditionalInfoActivity
import com.atolow.miixs.ui.main.MainActivity
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 화면 캡처 방지
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
        setContentView(R.layout.activity_splash)
        
        // TokenManager 초기화 (MiixsApplication에서도 초기화하지만, 여기서도 확실히 초기화)
        TokenManager.init(this)
        
        Handler(Looper.getMainLooper()).postDelayed({
            val isLoggedIn = TokenManager.isLoggedIn()
            android.util.Log.d("SplashActivity", "로그인 상태 확인: isLoggedIn=$isLoggedIn")
            if (isLoggedIn) {
                // 프로필 확인하여 phoneNumber가 null이면 추가 정보 입력 화면으로 이동
                lifecycleScope.launch {
                    checkProfileAndNavigate()
                }
            } else {
                android.util.Log.d("SplashActivity", "로그인되지 않음, LoginActivity로 이동")
                startActivity(Intent(this, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
                finish()
            }
        }, 2000) // 2초 후 이동
    }
    
    private suspend fun checkProfileAndNavigate() {
        try {
            val userRepository = UserRepository()
            val profileResult = userRepository.getProfile()
            
            profileResult.fold(
                onSuccess = { profile ->
                    val phoneNumber = profile.phoneNumber
                    val email = profile.email
                    
                    // phoneNumber가 null이거나 비어있고 email이 있으면 추가 정보 입력 화면으로 이동
                    if ((phoneNumber.isNullOrBlank() || phoneNumber == "00000000000") && !email.isNullOrBlank()) {
                        android.util.Log.d("SplashActivity", "추가 정보 입력 필요: phoneNumber=$phoneNumber, email=$email")
                        startActivity(Intent(this@SplashActivity, OAuthAdditionalInfoActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            putExtra("email", email)
                        })
                    } else {
                        android.util.Log.d("SplashActivity", "프로필 완료, MainActivity로 이동")
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        })
                    }
                    finish()
                },
                onFailure = { error ->
                    android.util.Log.e("SplashActivity", "프로필 조회 실패: ${error.message}", error)
                    // 프로필 조회 실패 시 MainActivity로 이동 (서버 에러일 수 있음)
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    })
                    finish()
                }
            )
        } catch (e: Exception) {
            android.util.Log.e("SplashActivity", "프로필 확인 중 오류 발생", e)
            // 오류 발생 시 MainActivity로 이동
            startActivity(Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
            finish()
        }
    }
    
    override fun onResume() {
        super.onResume()
        // Activity가 재개될 때마다 화면 캡처 방지 재확인
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
    }
}

