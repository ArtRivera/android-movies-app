package com.artrivera.moviesapp.movie.data.network

import android.util.Log
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory


object TheMovieDbClient {

    val instance: TheMovieDbService by lazy {
        val contentType = "application/json".toMediaType()

        val loggingInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            Log.d("Request", originalRequest.toString())
            chain.proceed(originalRequest)
        }
        val authInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer ${TheMovieDbService.API_KEY}")
                .build()
            chain.proceed(newRequest)
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        val json = Json { ignoreUnknownKeys = true }

        Retrofit.Builder()
            .baseUrl(TheMovieDbService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(TheMovieDbService::class.java)
    }

}