package com.alexchan.finite_state_machine.api

import com.alexchan.finite_state_machine.BuildConfig
import com.alexchan.finite_state_machine.BuildConfig.DEBUG
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ClientGenerator {

    private const val connectTimeout: Long = 60
    private const val readTimeout: Long = 60

    private val okHttpClientBuilder = OkHttpClient.Builder()
        .connectTimeout(connectTimeout, TimeUnit.SECONDS)
        .readTimeout(readTimeout, TimeUnit.SECONDS)
        .addInterceptor(RequestInterceptor())

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createClient(serviceClass: Class<S>): S {
        if (DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }

        val httpClient = okHttpClientBuilder.build()

        val retrofit = retrofitBuilder
            .client(httpClient)
            .build()

        return retrofit.create(serviceClass)
    }
}
