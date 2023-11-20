package com.neocafe.neocafe.models.api.connections

import com.neocafe.neocafe.entities.login.LoginForm
import com.neocafe.neocafe.entities.login.OTPForm
import com.neocafe.neocafe.entities.registration.RegistrationForm
import com.neocafe.neocafe.entities.login.TokenRefresh
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api-customers/register")
    suspend fun register(@Body registrationForm: RegistrationForm): Response<RegistrationForm>

    @POST("/api-customers/otp-check")
    suspend fun otpCheck(@Body otpForm: OTPForm): Response<TokenRefresh>

    @POST("api-customers/login")
    suspend fun login(@Body loginForm: LoginForm): Response<LoginForm>

    @POST("api-customers/login-otp-check")
    suspend fun otpLoginCheck(@Body otpForm: OTPForm): Response<TokenRefresh>

}