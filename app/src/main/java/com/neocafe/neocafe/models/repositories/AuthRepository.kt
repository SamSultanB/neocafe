package com.neocafe.neocafe.models.repositories

import com.neocafe.neocafe.models.api.connections.AuthApi
import com.neocafe.neocafe.entities.login.LoginForm
import com.neocafe.neocafe.entities.login.OTPForm
import com.neocafe.neocafe.entities.registration.RegistrationForm

class AuthRepository(private val authApi: AuthApi) {

//    private val authApi = RetrofitInstance.authApi

    suspend fun register(registrationForm: RegistrationForm) = authApi.register(registrationForm)

    suspend fun otpCheck(otpForm: OTPForm) = authApi.otpCheck(otpForm)

    suspend fun login(loginForm: LoginForm) = authApi.login(loginForm)

    suspend fun otpLoginCheck(otpForm: OTPForm) = authApi.otpLoginCheck(otpForm)

}