package com.atolow.miixs.ui.auth

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.atolow.miixs.R
import com.atolow.miixs.data.model.Location
import com.atolow.miixs.data.repository.AuthRepository
import com.atolow.miixs.data.repository.UserRepository
import com.atolow.miixs.databinding.ActivityOauthAdditionalInfoBinding
import com.atolow.miixs.ui.main.MainActivity
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class OAuthAdditionalInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOauthAdditionalInfoBinding
    private val authRepository = AuthRepository()
    private val userRepository = UserRepository()
    private val selectedImageUris = mutableListOf<Uri?>()
    
    private lateinit var imageLauncher1: androidx.activity.result.ActivityResultLauncher<String>
    private lateinit var imageLauncher2: androidx.activity.result.ActivityResultLauncher<String>
    private lateinit var imageLauncher3: androidx.activity.result.ActivityResultLauncher<String>
    
    private var selectedLocation: Location? = null
    private var isPhoneVerified = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOauthAdditionalInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupImageLaunchers()
        setupClickListeners()
        updateProfileImageDisplay()
        
        // 뒤로가기 버튼 처리
        binding.toolbar.setNavigationOnClickListener {
            // 뒤로가기 시 토큰 유지하고 앱 종료 (다시 앱을 열면 추가 정보 입력 화면으로 리다이렉트됨)
            finish()
        }
    }
    
    override fun onBackPressed() {
        // 뒤로가기 시 토큰 유지하고 앱 종료 (다시 앱을 열면 추가 정보 입력 화면으로 리다이렉트됨)
        finish()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        // 앱 종료 시 토큰은 유지 (다시 앱을 열면 추가 정보 입력 화면으로 리다이렉트됨)
    }
    
    private fun setupImageLaunchers() {
        imageLauncher1 = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let {
                if (isValidImageFormat(it)) {
                    while (selectedImageUris.size <= 0) {
                        selectedImageUris.add(null)
                    }
                    selectedImageUris[0] = it
                    updateProfileImageDisplay()
                } else {
                    Toast.makeText(this, "이미지 형식이 아닙니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
        
        imageLauncher2 = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let {
                if (isValidImageFormat(it)) {
                    while (selectedImageUris.size <= 1) {
                        selectedImageUris.add(null)
                    }
                    selectedImageUris[1] = it
                    updateProfileImageDisplay()
                } else {
                    Toast.makeText(this, "이미지 형식이 아닙니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
        
        imageLauncher3 = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let {
                if (isValidImageFormat(it)) {
                    while (selectedImageUris.size <= 2) {
                        selectedImageUris.add(null)
                    }
                    selectedImageUris[2] = it
                    updateProfileImageDisplay()
                } else {
                    Toast.makeText(this, "이미지 형식이 아닙니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    private fun isValidImageFormat(uri: Uri): Boolean {
        return try {
            val mimeType = contentResolver.getType(uri)
            val allowedTypes = listOf("image/jpeg", "image/jpg", "image/png", "image/gif", "image/webp")
            val isValid = mimeType != null && allowedTypes.contains(mimeType.lowercase())
            
            // MIME 타입이 없으면 파일명으로 확인
            if (!isValid && mimeType == null) {
                val fileName = getFileNameFromUri(uri)
                val extension = fileName?.substringAfterLast('.', "")?.lowercase()
                extension in listOf("jpg", "jpeg", "png", "gif", "webp")
            } else {
                isValid
            }
        } catch (e: Exception) {
            android.util.Log.e("OAuthAdditionalInfoActivity", "이미지 형식 확인 실패", e)
            false
        }
    }
    
    private fun getFileNameFromUri(uri: Uri): String? {
        return try {
            var result: String? = null
            if (uri.scheme == "content") {
                val cursor = contentResolver.query(uri, null, null, null, null)
                cursor?.use {
                    if (it.moveToFirst()) {
                        val nameIndex = it.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
                        if (nameIndex != -1) {
                            result = it.getString(nameIndex)
                        }
                    }
                }
            }
            if (result == null) {
                result = uri.path
                val cut = result?.lastIndexOf('/')
                if (cut != -1) {
                    result = result?.substring(cut!! + 1)
                }
            }
            result
        } catch (e: Exception) {
            android.util.Log.e("OAuthAdditionalInfoActivity", "파일명 가져오기 실패", e)
            null
        }
    }
    
    private fun updateProfileImageDisplay() {
        selectedImageUris.getOrNull(0)?.let { uri ->
            val imageSource = if (uri.scheme == "http" || uri.scheme == "https") {
                uri.toString()
            } else {
                uri
            }
            Glide.with(this)
                .load(imageSource)
                .centerCrop()
                .placeholder(R.drawable.bg_profile_circle)
                .error(R.drawable.bg_profile_circle)
                .into(binding.ivProfileImage1)
            binding.ivPlusIcon1.visibility = android.view.View.GONE
        } ?: run {
            binding.ivPlusIcon1.visibility = android.view.View.VISIBLE
        }
        binding.cardImage1.visibility = android.view.View.VISIBLE
        
        selectedImageUris.getOrNull(1)?.let { uri ->
            val imageSource = if (uri.scheme == "http" || uri.scheme == "https") {
                uri.toString()
            } else {
                uri
            }
            Glide.with(this)
                .load(imageSource)
                .centerCrop()
                .placeholder(R.drawable.bg_profile_circle)
                .error(R.drawable.bg_profile_circle)
                .into(binding.ivProfileImage2)
            binding.ivPlusIcon2.visibility = android.view.View.GONE
        } ?: run {
            binding.ivPlusIcon2.visibility = android.view.View.VISIBLE
        }
        binding.cardImage2.visibility = android.view.View.VISIBLE
        
        selectedImageUris.getOrNull(2)?.let { uri ->
            val imageSource = if (uri.scheme == "http" || uri.scheme == "https") {
                uri.toString()
            } else {
                uri
            }
            Glide.with(this)
                .load(imageSource)
                .centerCrop()
                .placeholder(R.drawable.bg_profile_circle)
                .error(R.drawable.bg_profile_circle)
                .into(binding.ivProfileImage3)
            binding.ivPlusIcon3.visibility = android.view.View.GONE
        } ?: run {
            binding.ivPlusIcon3.visibility = android.view.View.VISIBLE
        }
        binding.cardImage3.visibility = android.view.View.VISIBLE
    }
    
    private fun setupClickListeners() {
        binding.cardImage1.setOnClickListener {
            imageLauncher1.launch("image/*")
        }
        
        binding.cardImage2.setOnClickListener {
            imageLauncher2.launch("image/*")
        }
        
        binding.cardImage3.setOnClickListener {
            imageLauncher3.launch("image/*")
        }
        
        binding.tvLocation.setOnClickListener {
            showLocationDialog()
        }
        
        binding.btnSendVerificationCode.setOnClickListener {
            val phoneNumber = binding.etPhoneNumber.text.toString().trim()
            if (phoneNumber.isEmpty() || !phoneNumber.matches(Regex("^010[0-9]{8}$"))) {
                Toast.makeText(this, "올바른 휴대폰 번호를 입력해주세요. (01012345678 형식)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            sendVerificationCode(phoneNumber)
        }
        
        binding.btnVerifyCode.setOnClickListener {
            val phoneNumber = binding.etPhoneNumber.text.toString().trim()
            val code = binding.etVerificationCode.text.toString().trim()
            
            if (phoneNumber.isEmpty() || code.isEmpty()) {
                Toast.makeText(this, "휴대폰 번호와 인증번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            verifyCode(phoneNumber, code)
        }
        
        binding.btnComplete.setOnClickListener {
            completeAdditionalInfo()
        }
    }
    
    private fun showLocationDialog() {
        val locations = Location.values()
        val locationNames = locations.map { it.displayName }.toTypedArray()
        
        AlertDialog.Builder(this)
            .setTitle("지역 선택")
            .setItems(locationNames) { _, which ->
                selectedLocation = locations[which]
                binding.tvLocation.text = selectedLocation?.displayName
            }
            .setNegativeButton("취소", null)
            .show()
    }
    
    private fun sendVerificationCode(phoneNumber: String) {
        lifecycleScope.launch {
            binding.btnSendVerificationCode.isEnabled = false
            binding.progressBar.visibility = android.view.View.VISIBLE
            
            authRepository.sendPhoneVerificationCode(phoneNumber).fold(
                onSuccess = {
                    Toast.makeText(this@OAuthAdditionalInfoActivity, "인증번호가 전송되었습니다.", Toast.LENGTH_SHORT).show()
                    binding.llVerificationCode.visibility = android.view.View.VISIBLE
                },
                onFailure = { error ->
                    Toast.makeText(this@OAuthAdditionalInfoActivity, error.message ?: "인증번호 전송 실패", Toast.LENGTH_SHORT).show()
                }
            )
            
            binding.btnSendVerificationCode.isEnabled = true
            binding.progressBar.visibility = android.view.View.GONE
        }
    }
    
    private fun verifyCode(phoneNumber: String, code: String) {
        lifecycleScope.launch {
            binding.btnVerifyCode.isEnabled = false
            binding.progressBar.visibility = android.view.View.VISIBLE
            
            authRepository.verifyPhoneCode(phoneNumber, code).fold(
                onSuccess = {
                    isPhoneVerified = true
                    Toast.makeText(this@OAuthAdditionalInfoActivity, "인증이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    binding.btnVerifyCode.isEnabled = false
                    binding.etPhoneNumber.isEnabled = false
                    binding.etVerificationCode.isEnabled = false
                },
                onFailure = { error ->
                    val errorMessage = error.message ?: "인증번호 확인 실패"
                    // 백엔드에서 "인증번호가 일치하지 않습니다." 메시지를 반환하면 그대로 표시
                    val displayMessage = if (errorMessage.contains("일치하지 않습니다") || errorMessage.contains("틀렸")) {
                        "인증번호가 틀렸습니다"
                    } else {
                        errorMessage
                    }
                    Toast.makeText(this@OAuthAdditionalInfoActivity, displayMessage, Toast.LENGTH_SHORT).show()
                }
            )
            
            binding.btnVerifyCode.isEnabled = true
            binding.progressBar.visibility = android.view.View.GONE
        }
    }
    
    private fun completeAdditionalInfo() {
        val nickname = binding.etUsername.text.toString().trim()
        val phoneNumber = binding.etPhoneNumber.text.toString().trim()
        val verificationCode = binding.etVerificationCode.text.toString().trim()
        val bio = binding.etBio.text.toString().trim()
        
        if (nickname.isEmpty()) {
            Toast.makeText(this, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        
        // 닉네임 한글만 입력 검증
        val koreanPattern = Regex("^[가-힣]+$")
        if (!nickname.matches(koreanPattern)) {
            Toast.makeText(this, "닉네임은 한글만 입력할 수 있습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (nickname.length < 2 || nickname.length > 8) {
            Toast.makeText(this, "닉네임은 2자 이상 8자 이하여야 합니다.", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (selectedLocation == null) {
            Toast.makeText(this, "지역을 선택해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (!isPhoneVerified) {
            Toast.makeText(this, "휴대폰 인증을 완료해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (phoneNumber.isEmpty() || verificationCode.isEmpty()) {
            Toast.makeText(this, "휴대폰 번호와 인증번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (bio.length > 200) {
            Toast.makeText(this, "자기소개는 최대 200자까지 입력할 수 있습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        
        lifecycleScope.launch {
            binding.btnComplete.isEnabled = false
            binding.progressBar.visibility = android.view.View.VISIBLE
            
            val imagePaths = selectedImageUris.mapNotNull { uri ->
                uri?.let { getRealPathFromURI(it) }
            }
            
            userRepository.completeOAuthAdditionalInfo(
                nickname,
                selectedLocation!!,
                phoneNumber,
                verificationCode,
                if (bio.isEmpty()) null else bio,
                imagePaths
            ).fold(
                onSuccess = {
                    Toast.makeText(this@OAuthAdditionalInfoActivity, "추가 정보 입력이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@OAuthAdditionalInfoActivity, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                    finish()
                },
                onFailure = { error ->
                    Toast.makeText(this@OAuthAdditionalInfoActivity, error.message ?: "추가 정보 입력 실패", Toast.LENGTH_SHORT).show()
                }
            )
            
            binding.btnComplete.isEnabled = true
            binding.progressBar.visibility = android.view.View.GONE
        }
    }
    
    private fun getRealPathFromURI(uri: Uri): String? {
        return try {
            // Android 10 (API 29) 이상에서는 _data 컬럼을 사용할 수 없음
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // ContentResolver를 사용하여 임시 파일로 복사
                copyUriToTempFile(uri)
            } else {
                // Android 9 이하에서는 기존 방식 사용
                val cursor = contentResolver.query(uri, null, null, null, null)
                cursor?.use {
                    val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    if (it.moveToFirst()) {
                        it.getString(columnIndex)
                    } else {
                        null
                    }
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("OAuthAdditionalInfoActivity", "파일 경로 가져오기 실패", e)
            // 실패 시 임시 파일로 복사 시도
            copyUriToTempFile(uri)
        }
    }
    
    private fun copyUriToTempFile(uri: Uri): String? {
        return try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            inputStream?.use { input ->
                // 임시 파일 생성
                val tempFile = File.createTempFile(
                    "image_${System.currentTimeMillis()}",
                    ".jpg",
                    cacheDir
                )
                
                FileOutputStream(tempFile).use { output ->
                    input.copyTo(output)
                }
                
                tempFile.absolutePath
            }
        } catch (e: Exception) {
            android.util.Log.e("OAuthAdditionalInfoActivity", "임시 파일 복사 실패", e)
            null
        }
    }
}

