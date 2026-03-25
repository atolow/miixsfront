package com.atolow.miixs.data.repository

import android.util.Log
import com.atolow.miixs.data.model.ApiResponse
import com.atolow.miixs.data.model.ErrorResponse
import com.atolow.miixs.data.model.PageResponse
import com.atolow.miixs.data.model.user.*
import com.atolow.miixs.data.network.ApiClient
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class UserRepository {
    private val userApi = ApiClient.userApi
    private val gson = Gson()
    private val TAG = "UserRepository"
    
    suspend fun getProfile(): Result<ProfileResponseDto> {
        return try {
            val response = userApi.getProfile()
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "프로필 조회 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateProfile(request: UpdateProfileRequestDto): Result<ProfileResponseDto> {
        return try {
            val response = userApi.updateProfile(request)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                val errorBody = try {
                    response.errorBody()?.string()
                } catch (e: Exception) {
                    null
                }
                
                val errorMessage = try {
                    if (errorBody != null) {
                        val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                        errorResponse.message
                    } else {
                        response.body()?.message ?: "프로필 수정 실패"
                    }
                } catch (e: Exception) {
                    response.body()?.message ?: errorBody ?: "프로필 수정 실패"
                }
                
                Log.e(TAG, "프로필 수정 실패: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun uploadProfileImage(imagePath: String): Result<String> {
        return try {
            val file = File(imagePath)
            val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
            val imagePart = MultipartBody.Part.createFormData("image", file.name, requestFile)
            
            val response = userApi.uploadProfileImage(imagePart)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "이미지 업로드 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun uploadProfileImages(imagePaths: List<String>): Result<List<String>> {
        return try {
            val imageParts = imagePaths.take(3).mapIndexed { index, imagePath ->
                val file = File(imagePath)
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("images", file.name, requestFile)
            }
            
            val response = userApi.uploadProfileImages(imageParts)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "이미지 업로드 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun deleteProfileImage(): Result<Unit> {
        return try {
            val response = userApi.deleteProfileImage()
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.body()?.message ?: "이미지 삭제 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getDashboard(page: Int = 0, size: Int = 20): Result<DashboardResponseDto> {
        return try {
            val response = userApi.getDashboard(page, size)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "대시보드 조회 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getUserProfile(userId: Long): Result<ProfileResponseDto> {
        return try {
            val response = userApi.getUserProfile(userId)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "사용자 프로필 조회 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun deactivate(): Result<Unit> {
        return try {
            val authApi = ApiClient.authApi
            val response = authApi.deactivate()
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.body()?.message ?: "회원탈퇴 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getUserList(
        page: Int = 0,
        size: Int = 20,
        location: com.atolow.miixs.data.model.Location? = null,
        gender: com.atolow.miixs.data.model.Gender? = null,
        minAge: Int? = null,
        maxAge: Int? = null
    ): Result<PageResponse<ProfileResponseDto>> {
        return try {
            val locationStr = location?.name
            val genderStr = gender?.name
            val response = userApi.getUserList(page, size, locationStr, genderStr, minAge, maxAge)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "사용자 목록 조회 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun completeOAuthAdditionalInfo(
        nickname: String,
        location: com.atolow.miixs.data.model.Location,
        phoneNumber: String,
        verificationCode: String,
        bio: String?,
        imagePaths: List<String>
    ): Result<Unit> {
        return try {
            val nicknameBody = okhttp3.RequestBody.create(
                "text/plain".toMediaTypeOrNull(), nickname
            )
            val locationBody = okhttp3.RequestBody.create(
                "text/plain".toMediaTypeOrNull(), location.name
            )
            val phoneNumberBody = okhttp3.RequestBody.create(
                "text/plain".toMediaTypeOrNull(), phoneNumber
            )
            val verificationCodeBody = okhttp3.RequestBody.create(
                "text/plain".toMediaTypeOrNull(), verificationCode
            )
            val bioBody = bio?.let {
                okhttp3.RequestBody.create("text/plain".toMediaTypeOrNull(), it)
            }
            
            val imageParts = imagePaths.take(3).mapIndexed { index, imagePath ->
                val file = File(imagePath)
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("profileImages", file.name, requestFile)
            }
            
            val response = userApi.completeOAuthAdditionalInfo(
                nicknameBody,
                locationBody,
                phoneNumberBody,
                verificationCodeBody,
                bioBody,
                if (imageParts.isEmpty()) null else imageParts
            )
            
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.body()?.message ?: "추가 정보 입력 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateBlockNewChats(blockNewChats: Boolean): Result<Unit> {
        return try {
            val request = com.atolow.miixs.data.model.user.UpdateChatSettingsRequestDto(blockNewChats)
            val response = userApi.updateChatSettings(request)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.body()?.message ?: "채팅 설정 업데이트 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

