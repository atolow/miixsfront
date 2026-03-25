package com.atolow.miixs.ui.auth

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.atolow.miixs.BuildConfig
import com.atolow.miixs.R
import com.atolow.miixs.databinding.ActivityLoginBinding
import com.atolow.miixs.ui.main.MainActivity
import com.atolow.miixs.ui.auth.viewmodel.LoginViewModel
import com.atolow.miixs.ui.profile.PrivacyPolicyActivity
import com.atolow.miixs.ui.profile.TermsOfServiceActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences
    private var oauthCallbackProcessed = false // OAuth 콜백 처리 플래그
    
    companion object {
        private const val PREFS_NAME = "MiixsPrefs"
        private const val KEY_AGREEMENT_ACCEPTED = "agreement_accepted"
        private val NAVER_OAUTH_URL = BuildConfig.BASE_URL.trimEnd('/') + "/oauth2/authorization/naver"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        
        checkAgreementStatus()
        setupObservers()
        setupClickListeners()
        
        // OAuth 콜백 처리
        handleOAuthCallback()
        
        // 에러 메시지 처리 (URL 파라미터에서)
        handleErrorMessage()
    }
    
    private fun handleErrorMessage() {
        val intent = intent
        val data = intent.data
        if (data != null) {
            val errorMessage = data.getQueryParameter("error")
            if (!errorMessage.isNullOrBlank()) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun checkAgreementStatus() {
        val isAgreed = sharedPreferences.getBoolean(KEY_AGREEMENT_ACCEPTED, false)
        
        if (!isAgreed) {
            // 동의하지 않은 경우 동의 화면 표시
            showAgreementScreen()
        } else {
            // 동의한 경우 로그인 폼 표시
            showLoginForm()
        }
    }
    
    private fun showAgreementScreen() {
        binding.llAgreementContainer.visibility = android.view.View.VISIBLE
        binding.llLoginForm.visibility = android.view.View.GONE
        // 헤더 숨기기
        binding.root.findViewById<android.view.View>(com.atolow.miixs.R.id.llHeader)?.visibility = android.view.View.GONE
        
        // 이용약관 및 개인정보취급방침 클릭 리스너 설정
        binding.tvTermsOfService.setOnClickListener {
            val intent = Intent(this, TermsOfServiceActivity::class.java)
            startActivity(intent)
        }
        
        binding.tvPrivacyPolicy.setOnClickListener {
            val intent = Intent(this, PrivacyPolicyActivity::class.java)
            startActivity(intent)
        }
        
        // 동의하고 진행하기 버튼 클릭 리스너
        binding.btnAgree.setOnClickListener {
            // 동의 상태 저장
            sharedPreferences.edit().putBoolean(KEY_AGREEMENT_ACCEPTED, true).apply()
            // 로그인 화면으로 이동
            showLoginForm()
        }
    }
    
    private fun showLoginForm() {
        binding.llAgreementContainer.visibility = android.view.View.GONE
        binding.llLoginForm.visibility = android.view.View.VISIBLE
        // 헤더 숨기기
        binding.root.findViewById<android.view.View>(com.atolow.miixs.R.id.llHeader)?.visibility = android.view.View.GONE
    }
    
    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.loginResult.collect { result ->
                result?.let { res ->
                    res.onSuccess {
                        Toast.makeText(this@LoginActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }.onFailure { error ->
                        val errorMessage = error.message ?: "로그인 실패"
                        Toast.makeText(this@LoginActivity, errorMessage, Toast.LENGTH_LONG).show()
                        android.util.Log.e("LoginActivity", "로그인 실패", error)
                    }
                }
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.btnNaverLogin.setOnClickListener {
            startNaverOAuthLogin()
        }
        
        binding.btnKakaoLogin.setOnClickListener {
            startKakaoOAuthLogin()
        }
    }
    
    private fun startNaverOAuthLogin() {
        try {
            val builder = CustomTabsIntent.Builder()
            builder.setToolbarColor(ContextCompat.getColor(this, android.R.color.transparent))
            builder.setShowTitle(true)
            builder.setStartAnimations(this, android.R.anim.fade_in, android.R.anim.fade_out)
            builder.setExitAnimations(this, android.R.anim.fade_in, android.R.anim.fade_out)
            
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(NAVER_OAUTH_URL))
        } catch (e: Exception) {
            android.util.Log.e("LoginActivity", "네이버 로그인 시작 실패", e)
            Toast.makeText(this, "네이버 로그인을 시작할 수 없습니다", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun startKakaoOAuthLogin() {
        Toast.makeText(this, "아직 개발중입니다", Toast.LENGTH_SHORT).show()
    }
    
    private fun handleOAuthCallback() {
        // 이미 처리된 경우 중복 처리 방지
        if (oauthCallbackProcessed) {
            android.util.Log.d("LoginActivity", "OAuth 콜백이 이미 처리되었습니다. 건너뜁니다.")
            return
        }
        
        val intent = intent
        val data = intent.data
        
        android.util.Log.d("LoginActivity", "handleOAuthCallback 호출됨, data=${data}, action=${intent.action}")
        
        if (data != null) {
            val uri = data.toString()
            android.util.Log.d("LoginActivity", "OAuth 콜백 URI: $uri")
            android.util.Log.d("LoginActivity", "URI scheme: ${data.scheme}, host: ${data.host}, path: ${data.path}")
            
            // OAuth 성공 시 토큰 처리 (/oauth/success 또는 /oauth로 시작하는 경로)
            if (uri.contains("/oauth/success") || uri.contains("/oauth") && uri.contains("token=")) {
                val token = data.getQueryParameter("token")
                val refreshToken = data.getQueryParameter("refreshToken")
                
                if (token != null) {
                    // 중복 처리 방지 플래그 설정
                    oauthCallbackProcessed = true
                    
                    android.util.Log.d("LoginActivity", "OAuth 토큰 받음: token=${token.take(20)}..., refreshToken=${refreshToken?.take(20)}...")
                    // 토큰 저장 및 메인 화면으로 이동
                    lifecycleScope.launch {
                        try {
                            // TokenManager 초기화
                            com.atolow.miixs.data.local.TokenManager.init(this@LoginActivity)
                            
                            // 토큰 저장 (refreshToken이 없으면 accessToken을 refreshToken으로도 사용)
                            val refreshTokenToSave = refreshToken ?: token
                            com.atolow.miixs.data.local.TokenManager.saveTokens(token, refreshTokenToSave)
                            
                            // 토큰 저장 확인 (commit()을 사용하므로 즉시 확인 가능)
                            val savedToken = com.atolow.miixs.data.local.TokenManager.getAccessToken()
                            if (savedToken != null) {
                                android.util.Log.d("LoginActivity", "토큰 저장 완료 확인됨")
                                // 메인 화면으로 이동
                                val mainIntent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                }
                                startActivity(mainIntent)
                                finish()
                            } else {
                                android.util.Log.e("LoginActivity", "토큰 저장 실패")
                                oauthCallbackProcessed = false // 실패 시 다시 시도 가능하도록
                                Toast.makeText(this@LoginActivity, "토큰 저장에 실패했습니다", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            android.util.Log.e("LoginActivity", "OAuth 콜백 처리 실패", e)
                            oauthCallbackProcessed = false // 실패 시 다시 시도 가능하도록
                            Toast.makeText(this@LoginActivity, "로그인 처리 중 오류가 발생했습니다", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            
            // OAuth 추가 정보 입력 필요 시
            if (uri.contains("/oauth/additional-info") || uri.contains("additional-info")) {
                val token = data.getQueryParameter("token")
                val refreshToken = data.getQueryParameter("refreshToken")
                val email = data.getQueryParameter("email")
                
                if (token != null) {
                    // 중복 처리 방지 플래그 설정
                    oauthCallbackProcessed = true
                    
                    android.util.Log.d("LoginActivity", "OAuth 추가 정보 입력 필요: token=${token.take(20)}...")
                    // 토큰 저장 및 추가 정보 입력 화면으로 이동
                    lifecycleScope.launch {
                        try {
                            // TokenManager 초기화
                            com.atolow.miixs.data.local.TokenManager.init(this@LoginActivity)
                            
                            // 토큰 저장
                            val refreshTokenToSave = refreshToken ?: token
                            com.atolow.miixs.data.local.TokenManager.saveTokens(token, refreshTokenToSave)
                            
                            // 추가 정보 입력 화면으로 이동
                            val additionalInfoIntent = Intent(this@LoginActivity, com.atolow.miixs.ui.auth.OAuthAdditionalInfoActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                putExtra("email", email)
                            }
                            startActivity(additionalInfoIntent)
                            finish()
                        } catch (e: Exception) {
                            android.util.Log.e("LoginActivity", "OAuth 추가 정보 입력 화면 이동 실패", e)
                            oauthCallbackProcessed = false
                            Toast.makeText(this@LoginActivity, "화면 이동 중 오류가 발생했습니다", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    android.util.Log.w("LoginActivity", "토큰이 URL 파라미터에 없습니다")
                    Toast.makeText(this, "로그인 토큰을 받지 못했습니다", Toast.LENGTH_SHORT).show()
                }
            } else if (uri.contains("/oauth/failure")) {
                oauthCallbackProcessed = true // 실패도 처리된 것으로 표시
                val errorMessage = data.getQueryParameter("error") ?: "로그인에 실패했습니다"
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
    
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        android.util.Log.d("LoginActivity", "onNewIntent 호출됨")
        setIntent(intent)
        oauthCallbackProcessed = false // 새로운 Intent가 오면 다시 처리 가능하도록
        handleOAuthCallback()
    }
    
    override fun onResume() {
        super.onResume()
        android.util.Log.d("LoginActivity", "onResume 호출됨, oauthCallbackProcessed=$oauthCallbackProcessed")
        // onCreate에서 이미 처리하지 않은 경우에만 콜백 처리
        // (onResume은 여러 번 호출될 수 있으므로 중복 처리 방지)
        // 단, Intent에 데이터가 있는 경우에만 처리 (새로운 콜백인 경우)
        val intent = intent
        val data = intent.data
        if (!oauthCallbackProcessed && data != null && (data.toString().contains("/oauth") || data.getQueryParameter("token") != null)) {
            android.util.Log.d("LoginActivity", "onResume에서 OAuth 콜백 처리 시도")
            handleOAuthCallback()
        }
    }
}

