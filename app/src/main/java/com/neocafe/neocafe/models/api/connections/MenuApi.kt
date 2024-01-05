package com.neocafe.neocafe.models.api.connections

import com.neocafe.neocafe.entities.branches.Branche
import com.neocafe.neocafe.entities.categories.Category
import com.neocafe.neocafe.entities.menu.responses.Menu
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MenuApi {

    @GET("/api-menu/list-category/")
    suspend fun getCategory(): Response<List<Category>>

    @GET("/api-menu/populars/")
    suspend fun getPopulars(): Response<List<Menu>>

    @GET("/api-menu/menu-list/{category_slug}/")
    suspend fun getMenu(@Path("category_slug") category: String): Response<List<Menu>>

    @GET("/api-branches/list-branches/")
    suspend fun getAllBranches(): Response<List<Branche>>

    @GET("/api-menu/list-menu/")
    suspend fun getAllMenu(): Response<List<Menu>>

}