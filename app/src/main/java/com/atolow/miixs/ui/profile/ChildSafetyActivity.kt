package com.atolow.miixs.ui.profile

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.atolow.miixs.BuildConfig
import com.atolow.miixs.databinding.ActivityChildSafetyBinding

class ChildSafetyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChildSafetyBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildSafetyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupWebView()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupWebView() {
        binding.webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            builtInZoomControls = false
            displayZoomControls = false
        }
        
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                // 같은 도메인 내에서만 WebView에서 로드
                url?.let {
                    if (it.contains("miixs.com")) {
                        return false // WebView에서 계속 로드
                    }
                }
                return super.shouldOverrideUrlLoading(view, url)
            }
        }
        
        val childSafetyUrl = BuildConfig.BASE_URL.trimEnd('/') + "/safety/child-safety"
        binding.webView.loadUrl(childSafetyUrl)
    }
    
    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
