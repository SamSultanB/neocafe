package com.neocafe.neocafe.models.api.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(val sessionManager: SessionManager): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val isLoginOrRegistration = request.url.pathSegments.contains("login") ||
                request.url.pathSegments.contains("register") || request.url.pathSegments.contains("check-verification-code")

        val modifiedRequest = if (isLoginOrRegistration) {
            request.newBuilder().build()
        } else {
            request.newBuilder()
                .addHeader("Authorization", "Bearer ${sessionManager.fetchAuthToken()}")
                .build()
        }

        return chain.proceed(modifiedRequest)
    }

}