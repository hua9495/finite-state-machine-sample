package com.alexchan.finite_state_machine.api

import com.alexchan.finite_state_machine.BuildConfig
import okhttp3.Interceptor

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val url = request.url
            .newBuilder()
            .build()
        val builder = request.newBuilder().url(url)
        builder.addHeader("Authorization", BuildConfig.CLIENT_ID)
        builder.addHeader("Accept-Version", "v1")
        return chain.proceed(builder.build())
    }
}
