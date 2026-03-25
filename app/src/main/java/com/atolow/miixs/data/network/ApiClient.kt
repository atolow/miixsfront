package com.atolow.miixs.data.network

import android.content.Context
import com.atolow.miixs.MiixsApplication
import com.atolow.miixs.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private val BASE_URL = BuildConfig.BASE_URL
    
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
    
    private val okHttpClient by lazy {
        val context = try {
            MiixsApplication.getInstance()
        } catch (e: IllegalStateException) {
            // Application이 아직 초기화되지 않은 경우 (테스트 등)
            return@lazy OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        }
        
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthInterceptor(context.applicationContext))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
    val authApi: AuthApi = retrofit.create(AuthApi::class.java)
    val userApi: UserApi = retrofit.create(UserApi::class.java)
    val chatApi: ChatApi = retrofit.create(ChatApi::class.java)
    val postApi: PostApi = retrofit.create(PostApi::class.java)
    val paymentApi: PaymentApi = retrofit.create(PaymentApi::class.java)
    val blockApi: BlockApi = retrofit.create(BlockApi::class.java)
    val reportApi: ReportApi = retrofit.create(ReportApi::class.java)
}

