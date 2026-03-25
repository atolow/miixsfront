package com.atolow.miixs.data.repository

import com.atolow.miixs.data.model.ApiResponse
import com.atolow.miixs.data.model.block.BlockResponseDto
import com.atolow.miixs.data.model.block.BlockUserRequestDto
import com.atolow.miixs.data.network.ApiClient

class BlockRepository {
    private val blockApi = ApiClient.blockApi
    
    suspend fun blockUser(blockedUserId: Long): Result<BlockResponseDto> {
        return try {
            val request = BlockUserRequestDto(blockedUserId)
            val response = blockApi.blockUser(request)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "차단 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun unblockUser(blockedUserId: Long): Result<Unit> {
        return try {
            val response = blockApi.unblockUser(blockedUserId)
            if (response.isSuccessful && response.body()?.success == true) {
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.body()?.message ?: "차단 해제 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getBlockedUsers(page: Int = 0, size: Int = 20): Result<List<BlockResponseDto>> {
        return try {
            val response = blockApi.getBlockedUsers(page, size)
            if (response.isSuccessful && response.body()?.success == true) {
                val blockedUsers = response.body()!!.data!!.content
                Result.success(blockedUsers)
            } else {
                Result.failure(Exception(response.body()?.message ?: "차단한 사용자 목록 조회 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

