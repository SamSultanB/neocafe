package com.neocafe.neocafe.models.api.connections

import com.neocafe.neocafe.entities.branches.Branche
import retrofit2.Response
import retrofit2.http.GET

interface BranchesApi {

    @GET("/api-branches/list-branches/")
    suspend fun getAllBranches(): Response<List<Branche>>

}