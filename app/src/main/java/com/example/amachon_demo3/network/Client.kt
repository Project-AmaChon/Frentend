package com.example.amachon_demo3.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
object Client {
    var retrofitService: API
    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val logger = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://13.125.98.158:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(logger)
            .build()

        retrofitService = retrofit.create(API::class.java)
    }
}
*/

object Client {
    var value: String? = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6Im1lbWJlcjFAbmF2ZXIuY29tIiwiaWF0IjoxNjg1NDM1MzE4LCJleHAiOjE2OTMyMTEzMTh9.87-xvcsmo9xJPMVxsCQaJpuBVkR6fQp01tIMYNQVTSg"

    var retrofitService: API
    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val headerInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $value")
                .build()
            chain.proceed(request)
        }

        val logger = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(headerInterceptor)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://15.165.204.209:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(logger)
            .build()

        retrofitService = retrofit.create(API::class.java)
    }
}
