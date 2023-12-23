package com.neocafe.neocafe.models.api.connections

import com.neocafe.neocafe.entities.branches.Branche
import com.neocafe.neocafe.entities.categories.Category
import com.neocafe.neocafe.entities.menu.responses.Menu
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MenuApi {

    @GET("/api-menu/list-category/{branch_id}/")
    suspend fun getCategory(@Path("branch_id") id: Int): Response<List<Category>>

    @GET("/api-menu/populars/{branch_id}/")
    suspend fun getPopulars(@Path("branch_id") id: Int): Response<List<Menu>>

    @GET("/api-menu/menu-list/{category_slug}{branch_id}/")
    suspend fun getMenu(@Path("category_slug") category: String, @Path("branch_id") id: Int): Response<List<Menu>>

    @GET("/api-branches/list-branches/")
    suspend fun getAllBranches(): Response<List<Branche>>

}