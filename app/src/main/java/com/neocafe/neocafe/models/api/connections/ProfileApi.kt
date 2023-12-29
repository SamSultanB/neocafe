package com.neocafe.neocafe.models.api.connections

import com.neocafe.neocafe.entities.profile.requests.ProfileRequest
import com.neocafe.neocafe.entities.profile.responses.Profile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface ProfileApi {

    @GET("/api-customers/customer/profile/")
    suspend fun getProfile(): Response<Profile>

    @PUT("/api-customers/customer/profile/")
    suspend fun setProfile(@Body profile: ProfileRequest): Response<Profile>

}