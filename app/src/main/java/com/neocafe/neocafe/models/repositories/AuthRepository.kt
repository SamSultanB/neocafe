package com.neocafe.neocafe.models.repositories

import com.neocafe.neocafe.models.api.retrofit.RetrofitInstance
import com.neocafe.neocafe.models.entities.LoginForm
import com.neocafe.neocafe.models.entities.OTPForm
import com.neocafe.neocafe.models.entities.RegistrationForm

class AuthRepository {

    private val authApi = RetrofitInstance.authApi

    suspend fun register(registrationForm: RegistrationForm) = authApi.register(registrationForm)

    suspend fun otpCheck(otpForm: OTPForm) = authApi.otpCheck(otpForm)

    suspend fun login(loginForm: LoginForm) = authApi.login(loginForm)

    suspend fun otpLoginCheck(otpForm: OTPForm) = authApi.otpLoginCheck(otpForm)

}