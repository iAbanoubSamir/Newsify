package com.abanoub.newsify.data.remote.okhttp

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .header("X-Api-Key", apiKey)
            .build()

        return chain.proceed(newRequest)
    }
}