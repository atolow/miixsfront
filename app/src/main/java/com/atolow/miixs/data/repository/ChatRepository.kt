package com.atolow.miixs.data.repository

import com.atolow.miixs.data.model.ApiResponse
import com.atolow.miixs.data.model.PageResponse
import com.atolow.miixs.data.model.chat.*
import com.atolow.miixs.data.network.ApiClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ChatRepository {
    private val chatApi = ApiClient.chatApi
    
    suspend fun createChatRoom(otherUserId: Long): Result<CreateChatRoomResponseDto> {
        return try {
            android.util.Log.d("ChatRepository", "채팅방 생성 API 호출 시작: otherUserId=$otherUserId")
            val request = CreateChatRoomRequestDto(otherUserId)
            val response = chatApi.createChatRoom(request)
            
            android.util.Log.d("ChatRepository", "채팅방 생성 API 응답: code=${response.code()}, isSuccessful=${response.isSuccessful}")
            
            if (response.isSuccessful && response.body()?.success == true) {
                val chatRoomResponse = response.body()!!.data!!
                android.util.Log.d("ChatRepository", "채팅방 생성 성공: chatRoomId=${chatRoomResponse.chatRoomId}")
                Result.success(chatRoomResponse)
            } else {
                // 에러 본문 파싱
                val errorBody = try {
                    response.errorBody()?.string()
                } catch (e: Exception) {
                    null
                }
                android.util.Log.e("ChatRepository", "에러 본문: $errorBody")
                
                // ErrorResponse 형식으로 파싱 시도
                val errorMessage = try {
                    if (errorBody != null) {
                        val errorResponse = com.google.gson.Gson().fromJson(errorBody, com.atolow.miixs.data.model.ErrorResponse::class.java)
                        errorResponse.message
                    } else {
                        response.body()?.message ?: "채팅방 생성 실패 (코드: ${response.code()})"
                    }
                } catch (e: Exception) {
                    response.body()?.message ?: errorBody ?: "채팅방 생성 실패 (코드: ${response.code()})"
                }
                
                android.util.Log.e("ChatRepository", "채팅방 생성 실패: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            android.util.Log.e("ChatRepository", "채팅방 생성 예외 발생", e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
    
    suspend fun getChatRoomList(page: Int = 0, size: Int = 20): Result<PageResponse<ChatRoomResponseDto>> {
        return try {
            android.util.Log.d("ChatRepository", "전체 채팅방 목록 API 호출 시작: page=$page, size=$size")
            val response = chatApi.getChatRoomList(page, size)
            android.util.Log.d("ChatRepository", "전체 채팅방 목록 API 응답: code=${response.code()}, isSuccessful=${response.isSuccessful}")
            
            if (response.isSuccessful && response.body()?.success == true) {
                val chatRooms = response.body()!!.data!!
                android.util.Log.d("ChatRepository", "전체 채팅방 목록 조회 성공: ${chatRooms.content.size}개")
                Result.success(chatRooms)
            } else {
                val errorBody = try {
                    response.errorBody()?.string()
                } catch (e: Exception) {
                    null
                }
                android.util.Log.e("ChatRepository", "전체 채팅방 목록 조회 실패: code=${response.code()}, errorBody=$errorBody")
                
                val errorMessage = try {
                    if (errorBody != null) {
                        val errorResponse = com.google.gson.Gson().fromJson(errorBody, com.atolow.miixs.data.model.ErrorResponse::class.java)
                        errorResponse.message
                    } else {
                        response.body()?.message ?: "채팅방 목록 조회 실패 (코드: ${response.code()})"
                    }
                } catch (e: Exception) {
                    response.body()?.message ?: errorBody ?: "채팅방 목록 조회 실패 (코드: ${response.code()})"
                }
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            android.util.Log.e("ChatRepository", "전체 채팅방 목록 조회 예외 발생", e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
    
    suspend fun getMessages(chatRoomId: Long, page: Int = 0, size: Int = 50): Result<PageResponse<MessageResponseDto>> {
        return try {
            val response = chatApi.getMessages(chatRoomId, page, size)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "메시지 목록 조회 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun sendMessage(chatRoomId: Long, content: String): Result<MessageResponseDto> {
        return try {
            val request = SendMessageRequestDto(chatRoomId, content)
            val response = chatApi.sendMessage(request)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "메시지 전송 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun sendImageMessage(chatRoomId: Long, imagePath: String): Result<MessageResponseDto> {
        return try {
            val file = File(imagePath)
            val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
            val imagePart = MultipartBody.Part.createFormData("image", file.name, requestFile)
            
            val response = chatApi.sendImageMessage(chatRoomId, imagePart)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "이미지 메시지 전송 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun markMessageAsRead(chatRoomId: Long, messageId: Long): Result<Unit> {
        return try {
            val response = chatApi.markMessageAsRead(chatRoomId, messageId)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.body()?.message ?: "읽음 처리 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getUnreadMessageCount(chatRoomId: Long): Result<Long> {
        return try {
            val response = chatApi.getUnreadMessageCount(chatRoomId)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "읽지 않은 메시지 수 조회 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getTotalUnreadMessageCount(): Result<Long> {
        return try {
            val response = chatApi.getTotalUnreadMessageCount()
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "전체 읽지 않은 메시지 수 조회 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun leaveChatRoom(chatRoomId: Long): Result<Unit> {
        return try {
            val response = chatApi.leaveChatRoom(chatRoomId)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.body()?.message ?: "채팅방 나가기 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun toggleFavorite(chatRoomId: Long): Result<Unit> {
        return try {
            val response = chatApi.toggleFavorite(chatRoomId)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.body()?.message ?: "즐겨찾기 토글 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getFavoriteChatRoomList(page: Int = 0, size: Int = 20): Result<PageResponse<ChatRoomResponseDto>> {
        return try {
            android.util.Log.d("ChatRepository", "즐겨찾기 채팅방 목록 API 호출 시작")
            val response = chatApi.getFavoriteChatRoomList(page, size)
            android.util.Log.d("ChatRepository", "즐겨찾기 채팅방 목록 API 응답: code=${response.code()}, isSuccessful=${response.isSuccessful}")
            
            if (response.isSuccessful && response.body()?.success == true) {
                val pageResponse = response.body()!!.data!!
                android.util.Log.d("ChatRepository", "즐겨찾기 채팅방 목록 로드 성공: ${pageResponse.content.size}개")
                Result.success(pageResponse)
            } else {
                val errorBody = try {
                    response.errorBody()?.string()
                } catch (e: Exception) {
                    null
                }
                android.util.Log.e("ChatRepository", "즐겨찾기 채팅방 목록 조회 실패: ${response.body()?.message}, 에러 본문: $errorBody")
                Result.failure(Exception(response.body()?.message ?: "즐겨찾기 채팅방 목록 조회 실패"))
            }
        } catch (e: Exception) {
            android.util.Log.e("ChatRepository", "즐겨찾기 채팅방 목록 예외 발생", e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
}

