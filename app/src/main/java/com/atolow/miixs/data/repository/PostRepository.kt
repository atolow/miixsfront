package com.atolow.miixs.data.repository

import android.util.Log
import com.atolow.miixs.data.model.ApiResponse
import com.atolow.miixs.data.model.ErrorResponse
import com.atolow.miixs.data.model.PageResponse
import com.atolow.miixs.data.model.post.*
import com.atolow.miixs.data.network.ApiClient
import com.google.gson.Gson

class PostRepository {
    private val postApi = ApiClient.postApi
    private val TAG = "PostRepository"
    private val gson = Gson()
    
    suspend fun createPost(content: String): Result<PostResponseDto> {
        Log.d(TAG, "========== createPost 함수 시작 ==========")
        Log.d(TAG, "게시글 작성 시도: content=$content")
        return try {
            Log.d(TAG, "try 블록 시작")
            val request = CreatePostRequestDto(content)
            Log.d(TAG, "CreatePostRequestDto 생성 완료: $request")
            Log.d(TAG, "API 요청 시작 - postApi: $postApi")
            val response = postApi.createPost(request)
            Log.d(TAG, "API 요청 완료")
            
            Log.d(TAG, "게시글 작성 응답 코드: ${response.code()}")
            Log.d(TAG, "게시글 작성 응답 성공: ${response.isSuccessful}")
            Log.d(TAG, "응답 본문: ${response.body()}")
            
            if (response.isSuccessful && response.body()?.success == true) {
                val postResponse = response.body()?.data
                if (postResponse != null) {
                    Log.d(TAG, "게시글 작성 성공: postId=${postResponse.postId}")
                    Result.success(postResponse)
                } else {
                    Log.e(TAG, "게시글 작성 응답 데이터가 null입니다")
                    Log.e(TAG, "응답 본문 전체: ${response.body()}")
                    Result.failure(Exception("게시글 작성 응답 데이터가 없습니다"))
                }
            } else {
                val errorBody = try {
                    response.errorBody()?.string()
                } catch (e: Exception) {
                    Log.e(TAG, "에러 본문 읽기 실패", e)
                    null
                }
                
                Log.e(TAG, "게시글 작성 실패 - 응답 코드: ${response.code()}")
                Log.e(TAG, "에러 본문: $errorBody")
                Log.e(TAG, "응답 메시지: ${response.body()?.message}")
                
                // ErrorResponse 형식으로 파싱 시도
                val errorMessage = try {
                    if (errorBody != null && errorBody.isNotEmpty()) {
                        try {
                            val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                            errorResponse.message ?: "게시글 작성 실패"
                        } catch (e: Exception) {
                            Log.e(TAG, "ErrorResponse 파싱 실패, 원본 에러 본문 사용", e)
                            errorBody
                        }
                    } else {
                        response.body()?.message ?: "게시글 작성 실패 (코드: ${response.code()})"
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "에러 메시지 파싱 실패", e)
                    response.body()?.message ?: errorBody ?: "게시글 작성 실패 (코드: ${response.code()})"
                }
                
                Log.e(TAG, "최종 에러 메시지: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: java.net.SocketTimeoutException) {
            Log.e(TAG, "네트워크 타임아웃", e)
            Result.failure(Exception("네트워크 연결 시간이 초과되었습니다. 다시 시도해주세요."))
        } catch (e: java.net.UnknownHostException) {
            Log.e(TAG, "서버 연결 실패", e)
            Result.failure(Exception("서버에 연결할 수 없습니다. 인터넷 연결을 확인해주세요."))
        } catch (e: java.io.IOException) {
            Log.e(TAG, "네트워크 IO 예외", e)
            Result.failure(Exception("네트워크 오류가 발생했습니다: ${e.message}"))
        } catch (e: Exception) {
            Log.e(TAG, "게시글 작성 예외 발생", e)
            Log.e(TAG, "예외 타입: ${e.javaClass.name}")
            Log.e(TAG, "예외 메시지: ${e.message}")
            Log.e(TAG, "예외 스택 트레이스:")
            e.printStackTrace()
            val errorMsg = e.message ?: "알 수 없는 오류가 발생했습니다"
            Result.failure(Exception(errorMsg))
        }
    }
    
    suspend fun getPost(postId: Long): Result<PostResponseDto> {
        return try {
            val response = postApi.getPost(postId)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "게시글 조회 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getPostList(page: Int = 0, size: Int = 20): Result<PageResponse<PostResponseDto>> {
        return try {
            val response = postApi.getPostList(page, size)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "게시글 목록 조회 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getPostListWithFilters(
        location: com.atolow.miixs.data.model.Location?,
        gender: com.atolow.miixs.data.model.Gender?,
        minAge: Int?,
        maxAge: Int?,
        page: Int = 0,
        size: Int = 20
    ): Result<PageResponse<PostResponseDto>> {
        return try {
            val locationStr = location?.name
            val genderStr = gender?.name
            val response = postApi.getPostList(page, size, locationStr, genderStr, minAge, maxAge)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "게시글 목록 조회 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getMyPosts(page: Int = 0, size: Int = 20): Result<PageResponse<PostResponseDto>> {
        return try {
            val response = postApi.getMyPosts(page, size)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "내 게시글 목록 조회 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updatePost(postId: Long, content: String): Result<PostResponseDto> {
        return try {
            val request = CreatePostRequestDto(content)
            val response = postApi.updatePost(postId, request)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "게시글 수정 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun deletePost(postId: Long): Result<Unit> {
        return try {
            val response = postApi.deletePost(postId)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.body()?.message ?: "게시글 삭제 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

