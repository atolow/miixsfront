package com.atolow.miixs.ui.profile

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.atolow.miixs.R
import com.atolow.miixs.data.model.Location
import com.atolow.miixs.databinding.ActivityProfileEditBinding
import com.atolow.miixs.ui.profile.viewmodel.ProfileEditViewModel
import kotlinx.coroutines.launch

class ProfileEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileEditBinding
    private val viewModel: ProfileEditViewModel by viewModels()
    private val selectedImageUris = mutableListOf<Uri?>()
    
    // 각 이미지 슬롯에 대한 launcher를 미리 등록
    private lateinit var imageLauncher1: androidx.activity.result.ActivityResultLauncher<String>
    private lateinit var imageLauncher2: androidx.activity.result.ActivityResultLauncher<String>
    private lateinit var imageLauncher3: androidx.activity.result.ActivityResultLauncher<String>
    
    private var selectedLocation: Location? = null
    
    private fun updateProfileImageDisplay() {
        // 첫 번째 이미지
        selectedImageUris.getOrNull(0)?.let { uri ->
            // HTTP/HTTPS URL인 경우 문자열로 변환하여 Glide에 전달
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
        
        // 두 번째 이미지
        selectedImageUris.getOrNull(1)?.let { uri ->
            // HTTP/HTTPS URL인 경우 문자열로 변환하여 Glide에 전달
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
        
        // 세 번째 이미지
        selectedImageUris.getOrNull(2)?.let { uri ->
            // HTTP/HTTPS URL인 경우 문자열로 변환하여 Glide에 전달
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
    
    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            // 카메라로 촬영한 이미지는 별도 처리 필요
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // ActivityResultLauncher를 onCreate에서 미리 등록
        imageLauncher1 = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let {
                while (selectedImageUris.size <= 0) {
                    selectedImageUris.add(null)
                }
                selectedImageUris[0] = it
                updateProfileImageDisplay()
            }
        }
        
        imageLauncher2 = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let {
                while (selectedImageUris.size <= 1) {
                    selectedImageUris.add(null)
                }
                selectedImageUris[1] = it
                updateProfileImageDisplay()
            }
        }
        
        imageLauncher3 = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let {
                while (selectedImageUris.size <= 2) {
                    selectedImageUris.add(null)
                }
                selectedImageUris[2] = it
                updateProfileImageDisplay()
            }
        }
        
        setupToolbar()
        setupObservers()
        setupClickListeners()
        
        viewModel.loadProfile()
    }
    
    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.profile.collect { profile ->
                profile?.let {
                    // 닉네임 (name 필드 사용)
                    binding.etNickname.setText(it.name)
                    
                    // 자기소개
                    binding.etBio.setText(it.bio ?: "")
                    
                    // 지역
                    selectedLocation = it.location
                    binding.tvLocation.text = it.location?.displayName ?: "지역을 선택하세요"
                    
                    // 프로필 이미지 로드
                    selectedImageUris.clear()
                    // profileImageUrls가 있으면 사용, 없으면 profileImageUrl 사용
                    val imageUrls = it.profileImageUrls?.takeIf { it.isNotEmpty() } 
                        ?: (it.profileImageUrl?.let { listOf(it) } ?: emptyList())
                    
                    // 이미지 URL을 selectedImageUris에 추가 (최대 3개)
                    imageUrls.take(3).forEach { imageUrl ->
                        // URL을 Uri로 변환하여 추가
                        try {
                            val uri = android.net.Uri.parse(imageUrl)
                            selectedImageUris.add(uri)
                        } catch (e: Exception) {
                            // URL 파싱 실패 시 무시
                        }
                    }
                    
                    // 항상 3개 슬롯 모두 표시
                    updateProfileImageDisplay()
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.updateResult.collect { result ->
                result?.let { res ->
                    res.fold(
                        onSuccess = {
                            Toast.makeText(this@ProfileEditActivity, "프로필이 수정되었습니다.", Toast.LENGTH_SHORT).show()
                            finish()
                            viewModel.clearUpdateResult()
                        },
                        onFailure = { error ->
                            Toast.makeText(this@ProfileEditActivity, error.message ?: "프로필 수정 실패", Toast.LENGTH_SHORT).show()
                            viewModel.clearUpdateResult()
                        }
                    )
                }
            }
        }
        
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading) android.view.View.VISIBLE else android.view.View.GONE
                binding.btnSave.isEnabled = !isLoading
            }
        }
        
        lifecycleScope.launch {
            viewModel.error.collect { error ->
                error?.let {
                    Toast.makeText(this@ProfileEditActivity, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.cardImage1.setOnClickListener {
            pickImageFromGallery(0)
        }
        
        binding.cardImage2.setOnClickListener {
            pickImageFromGallery(1)
        }
        
        binding.cardImage3.setOnClickListener {
            pickImageFromGallery(2)
        }
        
        binding.tvLocation.setOnClickListener {
            showLocationDialog()
        }
        
        binding.btnSave.setOnClickListener {
            val nickname = binding.etNickname.text.toString().trim()
            val bio = binding.etBio.text.toString().trim()
            
            if (nickname.isEmpty()) {
                Toast.makeText(this, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            // 프로필 이미지 업로드 먼저 처리
            val imagePaths = selectedImageUris.mapNotNull { uri ->
                uri?.let { getRealPathFromURI(it) }
            }
            
            if (imagePaths.isNotEmpty()) {
                // 모든 이미지를 한 번에 업로드 (백엔드에서 첫 번째 이미지를 기본 프로필로 설정하고 모든 이미지 저장)
                viewModel.uploadProfileImages(imagePaths) { success ->
                    if (success) {
                        // 이미지 업로드 성공 후 프로필 업데이트
                        viewModel.updateProfile(nickname, bio, selectedLocation)
                    }
                }
            } else {
                // 이미지가 선택되지 않았으면 프로필만 업데이트
                viewModel.updateProfile(nickname, bio, selectedLocation)
            }
        }
    }
    
    private fun pickImageFromGallery(index: Int) {
        when (index) {
            0 -> imageLauncher1.launch("image/*")
            1 -> imageLauncher2.launch("image/*")
            2 -> imageLauncher3.launch("image/*")
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
    
    
    private fun getRealPathFromURI(uri: Uri): String? {
        // URI를 실제 파일 경로로 변환
        val cursor = contentResolver.query(uri, null, null, null, null)
        return cursor?.use {
            val columnIndex = it.getColumnIndexOrThrow(android.provider.MediaStore.Images.Media.DATA)
            it.moveToFirst()
            it.getString(columnIndex)
        }
    }
}


