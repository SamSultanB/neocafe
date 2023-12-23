package com.neocafe.neocafe.models.repositories

import com.neocafe.neocafe.entities.profile.requests.ProfileRequest
import com.neocafe.neocafe.models.api.connections.ProfileApi

class ProfileRepository(private val profileApi: ProfileApi) {

    suspend fun getProfile() = profileApi.getProfile()

    suspend fun setProfile(profileRequest: ProfileRequest) = profileApi.setProfile(profileRequest)

}