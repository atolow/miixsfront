package com.atolow.miixs.util

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException

class ImagePicker(private val activity: Activity) {
    companion object {
        const val REQUEST_CODE_GALLERY = 1001
        const val REQUEST_CODE_CAMERA = 1002
        const val REQUEST_CODE_PERMISSIONS = 1003
        
        private const val PERMISSION_CAMERA = Manifest.permission.CAMERA
        private const val PERMISSION_READ_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE
        private const val PERMISSION_READ_MEDIA_IMAGES = Manifest.permission.READ_MEDIA_IMAGES
    }
    
    private var cameraImageUri: Uri? = null
    private var onImageSelected: ((Uri) -> Unit)? = null
    
    fun pickImageFromGallery(onImageSelected: (Uri) -> Unit) {
        this.onImageSelected = onImageSelected
        
        if (checkPermissions()) {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activity.startActivityForResult(intent, REQUEST_CODE_GALLERY)
        } else {
            requestPermissions()
        }
    }
    
    fun pickImageFromCamera(onImageSelected: (Uri) -> Unit) {
        this.onImageSelected = onImageSelected
        
        if (checkPermissions()) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val photoFile = createImageFile()
            
            photoFile?.let { file ->
                val photoURI = FileProvider.getUriForFile(
                    activity,
                    "${activity.packageName}.fileprovider",
                    file
                )
                cameraImageUri = photoURI
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                activity.startActivityForResult(intent, REQUEST_CODE_CAMERA)
            }
        } else {
            requestPermissions()
        }
    }
    
    fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (resultCode != Activity.RESULT_OK) return false
        
        return when (requestCode) {
            REQUEST_CODE_GALLERY -> {
                data?.data?.let { uri ->
                    onImageSelected?.invoke(uri)
                    true
                } ?: false
            }
            REQUEST_CODE_CAMERA -> {
                cameraImageUri?.let { uri ->
                    onImageSelected?.invoke(uri)
                    true
                } ?: false
            }
            else -> false
        }
    }
    
    fun handleRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ): Boolean {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            return grantResults.all { it == PackageManager.PERMISSION_GRANTED }
        }
        return false
    }
    
    private fun checkPermissions(): Boolean {
        val permissions = mutableListOf<String>()
        
        if (ContextCompat.checkSelfPermission(activity, PERMISSION_CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(PERMISSION_CAMERA)
        }
        
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(activity, PERMISSION_READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(PERMISSION_READ_MEDIA_IMAGES)
            }
        } else {
            if (ContextCompat.checkSelfPermission(activity, PERMISSION_READ_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(PERMISSION_READ_STORAGE)
            }
        }
        
        return permissions.isEmpty()
    }
    
    private fun requestPermissions() {
        val permissions = mutableListOf<String>()
        
        if (ContextCompat.checkSelfPermission(activity, PERMISSION_CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(PERMISSION_CAMERA)
        }
        
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(activity, PERMISSION_READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(PERMISSION_READ_MEDIA_IMAGES)
            }
        } else {
            if (ContextCompat.checkSelfPermission(activity, PERMISSION_READ_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(PERMISSION_READ_STORAGE)
            }
        }
        
        if (permissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                activity,
                permissions.toTypedArray(),
                REQUEST_CODE_PERMISSIONS
            )
        }
    }
    
    private fun createImageFile(): File? {
        return try {
            val storageDir = activity.getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES)
            File.createTempFile(
                "JPEG_${System.currentTimeMillis()}_",
                ".jpg",
                storageDir
            )
        } catch (e: IOException) {
            null
        }
    }
}

