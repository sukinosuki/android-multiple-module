package com.example.app_paging3_demo

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface Service {

    @GET("search/repositories?sort=stars&q=Android")
    suspend fun searchRepos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): RepoRes<Repo>

    companion object {
        private const val BASE_URL = "https://api.github.com"

        private val okHttpClient by lazy {
            OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)// 设置超时时间
                .retryOnConnectionFailure(true)// 错误重连
                .addInterceptor(OkHttpInterceptor())
                .build()
        }

        val github by lazy {
            Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Service::class.java)
        }

        fun create(): Service {
            return Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Service::class.java)
        }
    }
}

class OkHttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.i("hanami", "<OkHttpInterceptor> intercept: 请求开始")
        val request = chain.request()
        Log.i("hanami", "<OkHttpInterceptor> intercept: 请求开始 url: ${request.url()}")

        val response = chain.proceed(request)

        Log.i("hanami", "<OkHttpInterceptor> intercept: 请求结束")

        return response
    }
}