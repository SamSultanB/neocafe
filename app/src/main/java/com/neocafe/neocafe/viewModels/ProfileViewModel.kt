package com.neocafe.neocafe.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neocafe.neocafe.entities.profile.requests.ProfileRequest
import com.neocafe.neocafe.entities.profile.responses.Profile
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.models.repositories.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ProfileRepository): ViewModel() {

    val getProfileResponse: MutableLiveData<Resource<Profile>> = MutableLiveData()

    val setProfileResponse: MutableLiveData<Resource<Profile>> = MutableLiveData()

    fun getProfile(){
        viewModelScope.launch {
            getProfileResponse.postValue(Resource.Loading())
            val response = repository.getProfile()
            if(response.isSuccessful){
                response.body()?.let {
                    getProfileResponse.postValue(Resource.Success(it))
                }
            }else{
                getProfileResponse.postValue(Resource.Error(response.message()))
            }
        }
    }

    fun setProfile(profileRequest: ProfileRequest){
        viewModelScope.launch {
            setProfileResponse.postValue(Resource.Loading())
            val response = repository.setProfile(profileRequest)
            if(response.isSuccessful){
                response.body()?.let {
                    setProfileResponse.postValue(Resource.Success(it))
                }
            }else{
                setProfileResponse.postValue(Resource.Error(response.message()))
            }
        }
    }

}