package com.neocafe.neocafe.models.api.connections

import com.neocafe.neocafe.entities.categories.Category
import com.neocafe.neocafe.entities.menu.Menu
import retrofit2.Response
import retrofit2.http.GET

interface MenuApi {

    @GET("/api-menu/list-category/")
    suspend fun getCategory(): Response<List<Category>>

    @GET("/api-menu/populars/")
    suspend fun getPopulars(): Response<List<Menu>>

}