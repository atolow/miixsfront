package com.atolow.miixs.ui.auth

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.atolow.miixs.databinding.ActivityFindPasswordBinding
import com.atolow.miixs.ui.auth.viewmodel.FindPasswordViewModel
import kotlinx.coroutines.launch

class FindPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFindPasswordBinding
    private val viewModel: FindPasswordViewModel by viewModels()
    private var countDownTimer: CountDownTimer? = null
    private var currentStep = 1 // 1: 이메일 입력, 2: 인증 코드 확인, 3: 비밀번호 재설정
    private var verifiedEmail: String? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupClickListeners()
        setupObservers()
        setupPasswordValidation()
        showStep1()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupClickListeners() {
        // 인증 코드 전송 버튼
        binding.btnSendCode.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "올바른 이메일 형식을 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.findPassword(email)
        }
        
        // 인증 코드 확인 버튼
        binding.btnVerifyCode.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val code = binding.etVerificationCode.text.toString().trim()
            
            if (email.isEmpty() || code.isEmpty()) {
                Toast.makeText(this, "이메일과 인증 코드를 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (code.length != 6) {
                Toast.makeText(this, "인증 코드는 6자리입니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.verifyPasswordResetCode(email, code)
        }
        
        // 비밀번호 재설정 버튼
        binding.btnResetPassword.setOnClickListener {
            val email = verifiedEmail ?: return@setOnClickListener
            val newPassword = binding.etNewPassword.text.toString().trim()
            val newPasswordConfirm = binding.etNewPasswordConfirm.text.toString().trim()
            
            if (newPassword.isEmpty()) {
                Toast.makeText(this, "새 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!validatePassword(newPassword)) {
                Toast.makeText(this, "비밀번호는 8자 이상 20자 이하이며, 영소문자와 숫자를 포함해야 합니다", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (newPasswordConfirm.isEmpty()) {
                Toast.makeText(this, "비밀번호 확인을 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (newPassword != newPasswordConfirm) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.resetPassword(email, newPassword)
        }
    }
    
    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.findPasswordResult.collect { result ->
                result?.let { res ->
                    res.fold(
                        onSuccess = {
                            Toast.makeText(this@FindPasswordActivity, "인증 코드가 전송되었습니다", Toast.LENGTH_SHORT).show()
                            showStep2()
                            binding.btnSendCode.isEnabled = false
                            startResendTimer(60)
                        },
                        onFailure = { error ->
                            val errorMessage = error.message ?: "인증 코드 전송 실패"
                            Toast.makeText(this@FindPasswordActivity, errorMessage, Toast.LENGTH_LONG).show()
                        }
                    )
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.verifyCodeResult.collect { result ->
                result?.let { res ->
                    res.fold(
                        onSuccess = {
                            Toast.makeText(this@FindPasswordActivity, "인증 코드 확인 완료", Toast.LENGTH_SHORT).show()
                            verifiedEmail = binding.etEmail.text.toString().trim()
                            showStep3()
                        },
                        onFailure = { error ->
                            val errorMessage = error.message ?: "인증 코드 확인 실패"
                            Toast.makeText(this@FindPasswordActivity, errorMessage, Toast.LENGTH_LONG).show()
                        }
                    )
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.resetPasswordResult.collect { result ->
                result?.let { res ->
                    res.fold(
                        onSuccess = {
                            Toast.makeText(this@FindPasswordActivity, "비밀번호가 재설정되었습니다", Toast.LENGTH_SHORT).show()
                            finish()
                        },
                        onFailure = { error ->
                            val errorMessage = error.message ?: "비밀번호 재설정 실패"
                            Toast.makeText(this@FindPasswordActivity, errorMessage, Toast.LENGTH_LONG).show()
                        }
                    )
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading) android.view.View.VISIBLE else android.view.View.GONE
            }
        }
    }
    
    private fun setupPasswordValidation() {
        // 비밀번호와 비밀번호 확인 비교
        binding.etNewPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                checkPasswordMatch()
            }
        })
        
        binding.etNewPasswordConfirm.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                checkPasswordMatch()
            }
        })
    }
    
    private fun checkPasswordMatch() {
        val password = binding.etNewPassword.text.toString()
        val passwordConfirm = binding.etNewPasswordConfirm.text.toString()
        
        if (passwordConfirm.isEmpty()) {
            binding.tvPasswordMatchMessage.visibility = android.view.View.GONE
            return
        }
        
        if (password == passwordConfirm) {
            binding.tvPasswordMatchMessage.text = "비밀번호가 일치합니다"
            binding.tvPasswordMatchMessage.setTextColor(android.graphics.Color.parseColor("#4CAF50"))
            binding.tvPasswordMatchMessage.visibility = android.view.View.VISIBLE
        } else {
            binding.tvPasswordMatchMessage.text = "비밀번호가 일치하지 않습니다"
            binding.tvPasswordMatchMessage.setTextColor(android.graphics.Color.parseColor("#F44336"))
            binding.tvPasswordMatchMessage.visibility = android.view.View.VISIBLE
        }
    }
    
    private fun validatePassword(password: String): Boolean {
        // 최소 8자, 최대 20자
        if (password.length < 8 || password.length > 20) {
            return false
        }
        // 영소문자와 숫자 포함 확인
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        return hasLowerCase && hasDigit
    }
    
    private fun showStep1() {
        currentStep = 1
        binding.llStep1.visibility = android.view.View.VISIBLE
        binding.llStep2.visibility = android.view.View.GONE
        binding.llStep3.visibility = android.view.View.GONE
    }
    
    private fun showStep2() {
        currentStep = 2
        binding.llStep1.visibility = android.view.View.GONE
        binding.llStep2.visibility = android.view.View.VISIBLE
        binding.llStep3.visibility = android.view.View.GONE
    }
    
    private fun showStep3() {
        currentStep = 3
        binding.llStep1.visibility = android.view.View.GONE
        binding.llStep2.visibility = android.view.View.GONE
        binding.llStep3.visibility = android.view.View.VISIBLE
    }
    
    private fun startResendTimer(seconds: Long) {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(seconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val remainingSeconds = millisUntilFinished / 1000
                binding.btnSendCode.text = "재전송 (${remainingSeconds}초)"
            }
            
            override fun onFinish() {
                binding.btnSendCode.isEnabled = true
                binding.btnSendCode.text = "인증코드 전송"
            }
        }.start()
    }
}

