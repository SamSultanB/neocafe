package com.neocafe.neocafe.models.repositories

import com.neocafe.neocafe.models.api.connections.BranchesApi

class BranchesRepository(private val branchesApi: BranchesApi) {
    suspend fun getAllBranches() = branchesApi.getAllBranches()

}