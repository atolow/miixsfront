package com.atolow.miixs.ui.post

import android.os.Bundle
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.atolow.miixs.databinding.ActivityCreatePostBinding
import com.atolow.miixs.ui.post.viewmodel.PostViewModel
import kotlinx.coroutines.launch

class CreatePostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreatePostBinding
    private val viewModel = PostViewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupClickListeners()
        setupObservers()
        setupTextWatcher()
    }
    
    private fun setupClickListeners() {
        // 취소 버튼 클릭
        binding.btnCancel.setOnClickListener {
            finish()
        }
        
        // 확인 버튼 클릭
        binding.btnSubmit.setOnClickListener {
            val content = binding.etPostContent.text.toString().trim()
            if (content.isNotEmpty()) {
                viewModel.createPost(content)
            } else {
                Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun setupTextWatcher() {
        // 초기 상태: 확인 버튼 비활성화
        binding.btnSubmit.isEnabled = false
        binding.btnSubmit.alpha = 0.5f
        
        // 텍스트 변경 감지하여 버튼 활성화/비활성화 및 문자 카운터 업데이트
        binding.etPostContent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {
                val text = s?.toString() ?: ""
                val charCount = text.length
                
                // 문자 카운터 업데이트
                binding.tvCharCount.text = "$charCount/40"
                
                // 40자 제한
                if (charCount > 40) {
                    binding.etPostContent.setText(text.substring(0, 40))
                    binding.etPostContent.setSelection(40)
                    Toast.makeText(this@CreatePostActivity, "40자까지 입력 가능합니다.", Toast.LENGTH_SHORT).show()
                    return
                }
                
                // 버튼 활성화/비활성화
                val hasContent = text.trim().isNotEmpty()
                binding.btnSubmit.isEnabled = hasContent
                binding.btnSubmit.alpha = if (hasContent) 1.0f else 0.5f
            }
        })
    }
    
    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.createPostResult.collect { result ->
                result?.let { res ->
                    res.onSuccess {
                        Toast.makeText(this@CreatePostActivity, "게시물이 작성되었습니다.", Toast.LENGTH_SHORT).show()
                        setResult(RESULT_OK)
                        finish()
                        viewModel.clearCreatePostResult()
                    }.onFailure { error ->
                        Toast.makeText(this@CreatePostActivity, error.message ?: "게시물 작성 실패", Toast.LENGTH_SHORT).show()
                        viewModel.clearCreatePostResult()
                    }
                }
            }
        }
    }
}

