package com.neocafe.neocafe.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.models.entities.LoginForm
import com.neocafe.neocafe.models.entities.OTPForm
import com.neocafe.neocafe.models.entities.RegistrationForm
import com.neocafe.neocafe.models.entities.TokenRefresh
import com.neocafe.neocafe.models.repositories.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {

    val repository = AuthRepository()

    val registrationResponse: MutableLiveData<Resource<RegistrationForm>> = MutableLiveData()

    val otpResponse: MutableLiveData<Resource<TokenRefresh>> = MutableLiveData()

    val loginResponse: MutableLiveData<Resource<LoginForm>> = MutableLiveData()

    val otpLoginResponse: MutableLiveData<Resource<TokenRefresh>> = MutableLiveData()

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
//                registrationResponse.postValue(response.errorBody()
//                    ?.let { Resource.Error(it.string()) })
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

    fun otpLoginCheck(otpForm: OTPForm){
        viewModelScope.launch {
            otpLoginResponse.postValue(Resource.Loading())
            val response = repository.otpLoginCheck(otpForm)
            if(response.isSuccessful){
                response.body()?.let {
                    otpLoginResponse.postValue(Resource.Success(it))
                }
            }else{
                loginResponse.postValue(Resource.Error(response.message()))
            }
        }
    }


    //validations
    fun validPhoneNumber(actualLength: Int, expectedLength: Int): String?{
        if (actualLength != expectedLength){
            return "Номер телефона введён неверно, попробуйте еще раз"
        }
        return null
    }

}