package com.neocafe.neocafe.models.api.connections

import retrofit2.Response
import retrofit2.http.GET

interface ProfileApi {

    @GET("/api-customers/customer/profile//")
    suspend fun getProfile(): Response<>

}