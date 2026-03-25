package com.atolow.miixs.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.atolow.miixs.R
import com.atolow.miixs.data.local.TokenManager
import com.atolow.miixs.data.repository.UserRepository
import com.atolow.miixs.databinding.ActivityMainBinding
import com.atolow.miixs.service.ChatNotificationService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import com.atolow.miixs.data.network.AuthInterceptor
import com.atolow.miixs.ui.auth.LoginActivity
import com.atolow.miixs.ui.auth.OAuthAdditionalInfoActivity
import com.atolow.miixs.util.AuthUtil
import com.atolow.miixs.util.ChatNotificationManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var tokenExpiredReceiver: BroadcastReceiver? = null
    private var userBannedReceiver: BroadcastReceiver? = null
    private lateinit var binding: ActivityMainBinding
    private val REQUEST_CODE_NOTIFICATION_PERMISSION = 1001
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 토큰이 없으면 로그인 화면으로 이동
        if (!TokenManager.isLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }
        
        // 프로필 확인하여 phoneNumber가 null이면 추가 정보 입력 화면으로 이동
        lifecycleScope.launch {
            checkProfileAndNavigate()
        }
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val navController = findNavController(R.id.nav_host_fragment)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setupWithNavController(navController)
        
        // 알림 권한 확인 및 요청 (Android 13 이상)
        checkAndRequestNotificationPermission()
        
        // Foreground Service 시작 (백그라운드에서 알림 받기)
        startForegroundService()
        
        // 메시지 알림 구독 시작
        ChatNotificationManager.startListening(this)
        
        // 토큰 만료 브로드캐스트 리스너 등록
        tokenExpiredReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == AuthInterceptor.ACTION_TOKEN_EXPIRED) {
                    handleTokenExpiration()
                }
            }
        }
        val tokenExpiredFilter = IntentFilter(AuthInterceptor.ACTION_TOKEN_EXPIRED)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(tokenExpiredReceiver, tokenExpiredFilter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(tokenExpiredReceiver, tokenExpiredFilter)
        }
        
        // 정지된 사용자 브로드캐스트 리스너 등록
        userBannedReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == AuthInterceptor.ACTION_USER_BANNED) {
                    val banMessage = intent.getStringExtra(AuthInterceptor.EXTRA_BAN_MESSAGE) 
                        ?: "정지된 사용자입니다"
                    handleUserBanned(banMessage)
                }
            }
        }
        val userBannedFilter = IntentFilter(AuthInterceptor.ACTION_USER_BANNED)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(userBannedReceiver, userBannedFilter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(userBannedReceiver, userBannedFilter)
        }
    }
    
    private fun handleTokenExpiration() {
        AuthUtil.handleTokenExpiration(this)
    }
    
    private fun handleUserBanned(banMessage: String) {
        // 정지 메시지 표시
        Toast.makeText(this, banMessage, Toast.LENGTH_LONG).show()
        
        // 로그인 페이지로 이동
        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }
    
    private suspend fun checkProfileAndNavigate() {
        try {
            val userRepository = UserRepository()
            val profileResult = userRepository.getProfile()
            
            profileResult.fold(
                onSuccess = { profile ->
                    val phoneNumber = profile.phoneNumber
                    val email = profile.email
                    val age = profile.age
                    
                    // 18세 이하인 경우 로그인 페이지로 이동
                    if (age != null && age <= 18) {
                        android.util.Log.d("MainActivity", "미성년자 접근 차단: age=$age")
                        TokenManager.clear()
                        val intent = Intent(this@MainActivity, LoginActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        startActivity(intent)
                        finish()
                        // Toast는 Activity가 시작된 후에 표시
                        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                            Toast.makeText(this@MainActivity, "19세 미만은 믹시즈를 사용할 수 없습니다", Toast.LENGTH_LONG).show()
                        }, 500)
                        return@fold
                    }
                    
                    // phoneNumber가 null이거나 비어있고 email이 있으면 추가 정보 입력 화면으로 이동
                    if ((phoneNumber.isNullOrBlank() || phoneNumber == "00000000000") && !email.isNullOrBlank()) {
                        android.util.Log.d("MainActivity", "추가 정보 입력 필요: phoneNumber=$phoneNumber, email=$email")
                        startActivity(Intent(this@MainActivity, OAuthAdditionalInfoActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            putExtra("email", email)
                        })
                        finish()
                    }
                },
                onFailure = { error ->
                    android.util.Log.e("MainActivity", "프로필 조회 실패: ${error.message}", error)
                    // 프로필 조회 실패 시 계속 진행 (서버 에러일 수 있음)
                }
            )
        } catch (e: Exception) {
            android.util.Log.e("MainActivity", "프로필 확인 중 오류 발생", e)
            // 오류 발생 시 계속 진행
        }
    }
    
    private fun checkAndRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // 권한이 없으면 요청
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_CODE_NOTIFICATION_PERMISSION
                )
            }
        }
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        if (requestCode == REQUEST_CODE_NOTIFICATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                android.util.Log.d("MainActivity", "알림 권한이 허용되었습니다")
            } else {
                android.util.Log.w("MainActivity", "알림 권한이 거부되었습니다")
            }
        }
    }
    
    override fun onResume() {
        super.onResume()
        
        // 토큰 만료 체크
        if (AuthUtil.checkAndHandleTokenExpiration(this)) {
            return
        }
        
        // 앱이 백그라운드에서 돌아왔을 때 토큰 체크 및 프로필 확인
        if (!TokenManager.isLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            // 프로필 확인하여 phoneNumber가 null이면 추가 정보 입력 화면으로 이동
            lifecycleScope.launch {
                checkProfileAndNavigate()
            }
            // Foreground Service 시작 (백그라운드에서 알림 받기)
            startForegroundService()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        // 브로드캐스트 리스너 해제
        tokenExpiredReceiver?.let {
            try {
                unregisterReceiver(it)
            } catch (e: Exception) {
                // 이미 해제되었을 수 있음
            }
        }
        userBannedReceiver?.let {
            try {
                unregisterReceiver(it)
            } catch (e: Exception) {
                // 이미 해제되었을 수 있음
            }
        }
    }
    
    override fun onPause() {
        super.onPause()
        // 앱이 백그라운드로 갈 때도 서비스는 계속 실행되어야 함
        // 서비스를 중지하지 않음
    }
    
    private fun startForegroundService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceIntent = Intent(this, ChatNotificationService::class.java)
            startForegroundService(serviceIntent)
        } else {
            val serviceIntent = Intent(this, ChatNotificationService::class.java)
            startService(serviceIntent)
        }
    }
    
    override fun onBackPressed() {
        val navController = findNavController(R.id.nav_host_fragment)
        
        // 홈 화면이면 앱을 백그라운드로 보냄
        if (navController.currentDestination?.id == R.id.navigation_home) {
            moveTaskToBack(true)
        } else {
            // 다른 화면이면 기본 뒤로가기 동작
            super.onBackPressed()
        }
    }
}

