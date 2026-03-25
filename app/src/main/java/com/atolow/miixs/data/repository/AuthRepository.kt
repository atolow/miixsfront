package com.atolow.miixs.data.repository

import android.util.Log
import com.atolow.miixs.data.local.TokenManager
import com.atolow.miixs.data.model.ApiResponse
import com.atolow.miixs.data.model.ErrorResponse
import com.atolow.miixs.data.model.auth.*
import com.atolow.miixs.data.model.auth.SendPhoneVerificationCodeRequestDto
import com.atolow.miixs.data.model.auth.VerifyPhoneCodeRequestDto
import com.atolow.miixs.data.network.ApiClient
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import android.content.Context
import android.net.Uri
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class AuthRepository {
    private val authApi = ApiClient.authApi
    private val TAG = "AuthRepository"
    private val gson = Gson()
    
    suspend fun login(username: String, password: String): Result<LoginResponseDto> {
        return try {
            Log.d(TAG, "로그인 시도: username=$username")
            val request = LoginRequestDto(username, password)
            val response = authApi.login(request)
            
            Log.d(TAG, "응답 코드: ${response.code()}")
            Log.d(TAG, "응답 성공: ${response.isSuccessful}")
            Log.d(TAG, "응답 메시지: ${response.message()}")
            
            if (response.isSuccessful && response.body()?.success == true) {
                val loginResponse = response.body()?.data
                if (loginResponse != null) {
                    // 토큰 저장 (백엔드에서 쿠키로 전달되지만, 여기서는 응답에 포함된 토큰 사용)
                    // saveTokens 내부에서 JWT 토큰을 파싱하여 사용자 정보를 자동으로 저장합니다
                    TokenManager.saveTokens(loginResponse.token, loginResponse.token) // TODO: 실제 refresh token 사용
                    Log.d(TAG, "로그인 성공, 토큰 저장 완료")
                    Log.d(TAG, "저장된 사용자 ID: ${TokenManager.getUserId()}")
                    Result.success(loginResponse)
                } else {
                    Log.e(TAG, "로그인 응답 데이터가 null입니다")
                    Result.failure(Exception("로그인 응답 데이터가 없습니다"))
                }
            } else {
                val errorBody = try {
                    response.errorBody()?.string()
                } catch (e: Exception) {
                    null
                }
                
                // ErrorResponse 형식으로 파싱 시도
                val errorMessage = try {
                    if (errorBody != null) {
                        val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                        errorResponse.message
                    } else {
                        response.body()?.message ?: "로그인 실패 (코드: ${response.code()})"
                    }
                } catch (e: Exception) {
                    response.body()?.message ?: errorBody ?: "로그인 실패 (코드: ${response.code()})"
                }
                
                Log.e(TAG, "로그인 실패: $errorMessage")
                Log.e(TAG, "에러 본문: $errorBody")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e(TAG, "로그인 예외 발생", e)
            Log.e(TAG, "예외 타입: ${e.javaClass.simpleName}")
            Log.e(TAG, "예외 메시지: ${e.message}")
            e.printStackTrace()
            Result.failure(e)
        }
    }
    
    suspend fun logout(): Result<LogoutResponseDto> {
        return try {
            val response = authApi.logout()
            if (response.isSuccessful && response.body()?.success == true) {
                TokenManager.clear()
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception(response.body()?.message ?: "로그아웃 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun refreshToken(): Result<RefreshTokenResponseDto> {
        return try {
            val refreshToken = TokenManager.getRefreshToken()
                ?: return Result.failure(Exception("리프레시 토큰이 없습니다."))
            
            val request = RefreshTokenRequestDto(refreshToken)
            val response = authApi.refreshToken(request)
            
            if (response.isSuccessful && response.body()?.success == true) {
                val refreshResponse = response.body()!!.data!!
                TokenManager.saveTokens(refreshResponse.accessToken, refreshResponse.refreshToken)
                Result.success(refreshResponse)
            } else {
                Result.failure(Exception(response.body()?.message ?: "토큰 갱신 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun sendVerificationCode(email: String): Result<Unit> {
        return try {
            Log.d(TAG, "인증 코드 전송 시도: email=$email")
            val request = SendVerificationCodeRequestDto(email)
            val response = authApi.sendVerificationCode(request)
            
            Log.d(TAG, "인증 코드 전송 응답 코드: ${response.code()}")
            Log.d(TAG, "인증 코드 전송 응답 성공: ${response.isSuccessful}")
            
            if (response.isSuccessful && response.body()?.success == true) {
                Log.d(TAG, "인증 코드 전송 성공")
                Result.success(Unit)
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
                        response.body()?.message ?: "인증 코드 전송 실패 (코드: ${response.code()})"
                    }
                } catch (e: Exception) {
                    response.body()?.message ?: errorBody ?: "인증 코드 전송 실패 (코드: ${response.code()})"
                }
                
                Log.e(TAG, "인증 코드 전송 실패: $errorMessage")
                Log.e(TAG, "에러 본문: $errorBody")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e(TAG, "인증 코드 전송 예외 발생", e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
    
    suspend fun verifyEmail(email: String, code: String): Result<EmailVerificationResponseDto> {
        return try {
            Log.d(TAG, "이메일 인증 시도: email=$email, code=$code")
            val request = EmailVerificationRequestDto(email, code)
            val response = authApi.verifyEmail(request)
            
            Log.d(TAG, "이메일 인증 응답 코드: ${response.code()}")
            Log.d(TAG, "이메일 인증 응답 성공: ${response.isSuccessful}")
            
            if (response.isSuccessful && response.body()?.success == true) {
                val verificationResponse = response.body()?.data
                if (verificationResponse != null) {
                    Log.d(TAG, "이메일 인증 성공: verified=${verificationResponse.verified}")
                    Result.success(verificationResponse)
                } else {
                    Log.e(TAG, "이메일 인증 응답 데이터가 null입니다")
                    Result.failure(Exception("이메일 인증 응답 데이터가 없습니다"))
                }
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
                        response.body()?.message ?: "이메일 인증 실패 (코드: ${response.code()})"
                    }
                } catch (e: Exception) {
                    response.body()?.message ?: errorBody ?: "이메일 인증 실패 (코드: ${response.code()})"
                }
                
                Log.e(TAG, "이메일 인증 실패: $errorMessage")
                Log.e(TAG, "에러 본문: $errorBody")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e(TAG, "이메일 인증 예외 발생", e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
    
    suspend fun checkResendAvailability(email: String): Result<ResendCooldownResponseDto> {
        return try {
            Log.d(TAG, "재전송 가능 여부 확인: email=$email")
            val response = authApi.checkResendAvailability(email)
            
            if (response.isSuccessful && response.body()?.success == true) {
                val resendResponse = response.body()?.data
                if (resendResponse != null) {
                    Log.d(TAG, "재전송 가능 여부: canResend=${resendResponse.canResend}, remainingSeconds=${resendResponse.remainingSeconds}")
                    Result.success(resendResponse)
                } else {
                    Result.failure(Exception("재전송 가능 여부 응답 데이터가 없습니다"))
                }
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
                        response.body()?.message ?: "재전송 가능 여부 확인 실패 (코드: ${response.code()})"
                    }
                } catch (e: Exception) {
                    response.body()?.message ?: errorBody ?: "재전송 가능 여부 확인 실패 (코드: ${response.code()})"
                }
                
                Log.e(TAG, "재전송 가능 여부 확인 실패: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e(TAG, "재전송 가능 여부 확인 예외 발생", e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
    
    suspend fun findPassword(email: String): Result<FindPasswordResponseDto> {
        return try {
            Log.d(TAG, "비밀번호 찾기 시도: email=$email")
            val request = FindPasswordRequestDto(email)
            val response = authApi.findPassword(request)
            
            if (response.isSuccessful && response.body()?.success == true) {
                val findPasswordResponse = response.body()?.data
                if (findPasswordResponse != null) {
                    Log.d(TAG, "비밀번호 찾기 성공")
                    Result.success(findPasswordResponse)
                } else {
                    Log.e(TAG, "비밀번호 찾기 응답 데이터가 null입니다")
                    Result.failure(Exception("응답 데이터가 없습니다"))
                }
            } else {
                val errorBody = try {
                    response.errorBody()?.string()
                } catch (e: Exception) {
                    null
                }
                
                val errorMessage = if (errorBody != null) {
                    try {
                        val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                        errorResponse.message ?: "비밀번호 찾기 실패"
                    } catch (e: Exception) {
                        "비밀번호 찾기 실패"
                    }
                } else {
                    "비밀번호 찾기 실패"
                }
                
                Log.e(TAG, "비밀번호 찾기 실패: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e(TAG, "비밀번호 찾기 예외 발생", e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
    
    suspend fun verifyPasswordResetCode(email: String, code: String): Result<com.atolow.miixs.data.model.auth.VerifyPasswordResetCodeResponseDto> {
        return try {
            Log.d(TAG, "비밀번호 재설정 코드 확인 시도: email=$email")
            val request = com.atolow.miixs.data.model.auth.VerifyPasswordResetCodeRequestDto(email, code)
            val response = authApi.verifyPasswordResetCode(request)
            
            if (response.isSuccessful && response.body()?.success == true) {
                val verifyResponse = response.body()?.data
                if (verifyResponse != null) {
                    Log.d(TAG, "비밀번호 재설정 코드 확인 성공")
                    Result.success(verifyResponse)
                } else {
                    Log.e(TAG, "비밀번호 재설정 코드 확인 응답 데이터가 null입니다")
                    Result.failure(Exception("응답 데이터가 없습니다"))
                }
            } else {
                val errorBody = try {
                    response.errorBody()?.string()
                } catch (e: Exception) {
                    null
                }
                
                val errorMessage = if (errorBody != null) {
                    try {
                        val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                        errorResponse.message ?: "인증 코드 확인 실패"
                    } catch (e: Exception) {
                        "인증 코드 확인 실패"
                    }
                } else {
                    "인증 코드 확인 실패"
                }
                
                Log.e(TAG, "비밀번호 재설정 코드 확인 실패: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e(TAG, "비밀번호 재설정 코드 확인 예외 발생", e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
    
    suspend fun resetPassword(email: String, newPassword: String): Result<com.atolow.miixs.data.model.auth.ResetPasswordResponseDto> {
        return try {
            Log.d(TAG, "비밀번호 재설정 시도: email=$email")
            val request = com.atolow.miixs.data.model.auth.ResetPasswordRequestDto(email, newPassword)
            val response = authApi.resetPassword(request)
            
            if (response.isSuccessful && response.body()?.success == true) {
                val resetResponse = response.body()?.data
                if (resetResponse != null) {
                    Log.d(TAG, "비밀번호 재설정 성공")
                    Result.success(resetResponse)
                } else {
                    Log.e(TAG, "비밀번호 재설정 응답 데이터가 null입니다")
                    Result.failure(Exception("응답 데이터가 없습니다"))
                }
            } else {
                val errorBody = try {
                    response.errorBody()?.string()
                } catch (e: Exception) {
                    null
                }
                
                val errorMessage = if (errorBody != null) {
                    try {
                        val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                        errorResponse.message ?: "비밀번호 재설정 실패"
                    } catch (e: Exception) {
                        "비밀번호 재설정 실패"
                    }
                } else {
                    "비밀번호 재설정 실패"
                }
                
                Log.e(TAG, "비밀번호 재설정 실패: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e(TAG, "비밀번호 재설정 예외 발생", e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
    
    suspend fun sendPhoneVerificationCode(phoneNumber: String): Result<Unit> {
        return try {
            Log.d(TAG, "휴대폰 인증번호 전송 시도: phoneNumber=$phoneNumber")
            val request = SendPhoneVerificationCodeRequestDto(phoneNumber)
            val response = authApi.sendPhoneVerificationCode(request)
            
            if (response.isSuccessful && response.body()?.success == true) {
                Log.d(TAG, "휴대폰 인증번호 전송 성공")
                Result.success(Unit)
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
                        response.body()?.message ?: "인증번호 전송 실패 (코드: ${response.code()})"
                    }
                } catch (e: Exception) {
                    response.body()?.message ?: errorBody ?: "인증번호 전송 실패 (코드: ${response.code()})"
                }
                
                Log.e(TAG, "휴대폰 인증번호 전송 실패: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e(TAG, "휴대폰 인증번호 전송 예외 발생", e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
    
    suspend fun verifyPhoneCode(phoneNumber: String, code: String): Result<Unit> {
        return try {
            Log.d(TAG, "휴대폰 인증번호 확인 시도: phoneNumber=$phoneNumber")
            val request = VerifyPhoneCodeRequestDto(phoneNumber, code)
            val response = authApi.verifyPhoneCode(request)
            
            if (response.isSuccessful && response.body()?.success == true) {
                Log.d(TAG, "휴대폰 인증번호 확인 성공")
                Result.success(Unit)
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
                        response.body()?.message ?: "인증번호 확인 실패 (코드: ${response.code()})"
                    }
                } catch (e: Exception) {
                    response.body()?.message ?: errorBody ?: "인증번호 확인 실패 (코드: ${response.code()})"
                }
                
                Log.e(TAG, "휴대폰 인증번호 확인 실패: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Log.e(TAG, "휴대폰 인증번호 확인 예외 발생", e)
            e.printStackTrace()
            Result.failure(e)
        }
    }
}

