package com.neocafe.neocafe.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.entities.login.LoginForm
import com.neocafe.neocafe.entities.login.OTPForm
import com.neocafe.neocafe.entities.registration.RegistrationForm
import com.neocafe.neocafe.entities.login.TokenRefresh
import com.neocafe.neocafe.models.repositories.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private  val repository: AuthRepository): ViewModel() {


    val registrationResponse: MutableLiveData<Resource<RegistrationForm>> = MutableLiveData()

    val otpResponse: MutableLiveData<Resource<TokenRefresh>> = MutableLiveData()

    val loginResponse: MutableLiveData<Resource<TokenRefresh>> = MutableLiveData()

    fun registration(registrationForm: RegistrationForm){
        viewModelScope.launch {
            registrationResponse.postValue(Resource.Loading())
            val response = repository.register(registrationForm)
            if(response.isSuccessful){
                response.body()?.let {
                    registrationResponse.postValue(Resource.Success(it))
                }
            }else{
                registrationResponse.postValue(Resource.Error(response.message()))
            }
        }
    }

    fun otpCheck(otpForm: OTPForm){
        viewModelScope.launch {
            otpResponse.postValue(Resource.Loading())
            val response = repository.otpCheck(otpForm)
            if(response.isSuccessful){
                response.body()?.let {
                    otpResponse.postValue(Resource.Success(it))
                }
            }else{
                otpResponse.postValue(Resource.Error(response.message()))
            }
        }
    }

    fun login(loginForm: LoginForm){
        viewModelScope.launch {
            otpResponse.postValue(Resource.Loading())
            val response = repository.login(loginForm)
            if(response.isSuccessful){
                response.body()?.let {
                    loginResponse.postValue(Resource.Success(it))
                }
            }else{
                loginResponse.postValue(Resource.Error(response.message()))
            }
        }
    }

    //validations
    fun validPhoneNumber(actualLength: Int, expectedLength: Int): String?{
        if (actualLength != expectedLength){
            return "Номер телефона введён неверно"
        }
        return null
    }

}