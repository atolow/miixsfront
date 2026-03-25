package com.atolow.miixs.ui.common

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.atolow.miixs.data.network.AuthInterceptor
import com.atolow.miixs.ui.auth.LoginActivity
import com.atolow.miixs.util.AuthUtil
import com.atolow.miixs.util.ErrorHandler
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseActivity : AppCompatActivity() {
    private var loadingDialog: LoadingDialog? = null
    private var tokenExpiredReceiver: BroadcastReceiver? = null
    private var userBannedReceiver: BroadcastReceiver? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 화면 캡처 방지
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
        
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
    
    override fun onResume() {
        super.onResume()
        // Activity가 재개될 때마다 화면 캡처 방지 재확인
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
        
        // 토큰 만료 체크
        AuthUtil.checkAndHandleTokenExpiration(this)
    }
    
    private fun handleTokenExpiration() {
        // 이미 로그인 화면이면 처리하지 않음
        if (this::class.java.name == LoginActivity::class.java.name) {
            return
        }
        
        AuthUtil.handleTokenExpiration(this)
    }
    
    private fun handleUserBanned(banMessage: String) {
        // 이미 로그인 화면이면 처리하지 않음
        if (this::class.java.name == LoginActivity::class.java.name) {
            return
        }
        
        // 정지 메시지 표시
        Toast.makeText(this, banMessage, Toast.LENGTH_LONG).show()
        
        // 로그인 페이지로 이동
        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
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
        hideLoading()
    }
    
    protected fun observeLoading(isLoading: StateFlow<Boolean>) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                isLoading.collect { loading ->
                    if (loading) {
                        showLoading()
                    } else {
                        hideLoading()
                    }
                }
            }
        }
    }
    
    protected fun observeError(error: StateFlow<String?>) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                error.collect { errorMessage ->
                    errorMessage?.let {
                        ErrorHandler.handleError(this@BaseActivity, Exception(it))
                    }
                }
            }
        }
    }
    
    protected fun observeErrorFlow(errorFlow: StateFlow<Throwable?>) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                errorFlow.collect { throwable ->
                    throwable?.let {
                        ErrorHandler.handleError(this@BaseActivity, it)
                    }
                }
            }
        }
    }
    
    private fun showLoading(message: String = "로딩 중...") {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
        }
        loadingDialog?.setMessage(message)
        loadingDialog?.show()
    }
    
    private fun hideLoading() {
        loadingDialog?.dismiss()
        loadingDialog = null
    }
}

