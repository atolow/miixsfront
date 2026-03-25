package com.atolow.miixs.ui.chat

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.atolow.miixs.R
import com.atolow.miixs.databinding.ActivityImageUploadBinding
import com.atolow.miixs.ui.chat.viewmodel.ChatRoomViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class ImageUploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageUploadBinding
    private val viewModel: ChatRoomViewModel by viewModels()
    private var chatRoomId: Long = -1L
    private var selectedImageUri: Uri? = null
    
    // 갤러리에서 이미지 선택하는 launcher
    private val imagePickerLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            showImagePreview(it)
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityImageUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        chatRoomId = intent.getLongExtra("chatRoomId", -1L)
        if (chatRoomId == -1L) {
            Toast.makeText(this, "채팅방 정보가 없습니다", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        setupToolbar()
        setupClickListeners()
        setupObservers()
        
        // 자동으로 이미지 선택 화면 열기
        openImagePicker()
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
        // 이미지 다시 선택 버튼
        binding.btnSelectImage.setOnClickListener {
            openImagePicker()
        }
        
        // 업로드 버튼
        binding.btnUpload.setOnClickListener {
            selectedImageUri?.let { uri ->
                uploadImage(uri)
            } ?: run {
                Toast.makeText(this, "이미지를 선택해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading) android.view.View.VISIBLE else android.view.View.GONE
                binding.btnUpload.isEnabled = !isLoading
            }
        }
        
        lifecycleScope.launch {
            viewModel.error.collect { error ->
                error?.let {
                    Toast.makeText(this@ImageUploadActivity, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    private fun openImagePicker() {
        imagePickerLauncher.launch("image/*")
    }
    
    private fun showImagePreview(uri: Uri) {
        Glide.with(this)
            .load(uri)
            .into(binding.ivPreview)
        
        binding.ivPreview.visibility = android.view.View.VISIBLE
        binding.btnUpload.visibility = android.view.View.VISIBLE
    }
    
    private fun uploadImage(uri: Uri) {
        lifecycleScope.launch {
            try {
                // URI를 File로 변환
                val imageFile = uriToFile(uri)
                if (imageFile == null) {
                    Toast.makeText(this@ImageUploadActivity, "이미지를 불러올 수 없습니다", Toast.LENGTH_SHORT).show()
                    return@launch
                }
                
                // 이미지 전송
                viewModel.sendImageMessage(chatRoomId, imageFile.absolutePath) { result ->
                    result.fold(
                        onSuccess = { message ->
                            // 메시지 전송 성공
                            Toast.makeText(this@ImageUploadActivity, "이미지가 전송되었습니다", Toast.LENGTH_SHORT).show()
                            // 결과를 ChatRoomActivity로 전달
                            val resultIntent = Intent().apply {
                                putExtra("imageUploaded", true)
                            }
                            setResult(RESULT_OK, resultIntent)
                            finish()
                        },
                        onFailure = { exception ->
                            val errorMessage = exception.message ?: "이미지 전송 실패"
                            Toast.makeText(this@ImageUploadActivity, errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            } catch (e: Exception) {
                android.util.Log.e("ImageUploadActivity", "이미지 전송 중 오류 발생", e)
                Toast.makeText(this@ImageUploadActivity, "이미지 전송 중 오류가 발생했습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun uriToFile(uri: Uri): File? {
        return try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            inputStream?.use { input ->
                // 임시 파일 생성
                val tempFile = File(cacheDir, "chat_image_${System.currentTimeMillis()}.jpg")
                FileOutputStream(tempFile).use { output ->
                    input.copyTo(output)
                }
                tempFile
            }
        } catch (e: Exception) {
            android.util.Log.e("ImageUploadActivity", "URI를 File로 변환 실패", e)
            null
        }
    }
}

