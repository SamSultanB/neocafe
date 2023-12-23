package com.neocafe.neocafe.models.repositories

import com.neocafe.neocafe.models.api.connections.MenuApi

class MenuRepository(private val menuApi: MenuApi) {

    suspend fun getCategories(id: Int) = menuApi.getCategory(id)

    suspend fun getPopulars(id: Int) = menuApi.getPopulars(id)

    suspend fun getMenu(slug: String, id: Int) = menuApi.getMenu(slug, id)

    suspend fun getAllBranches() = menuApi.getAllBranches()

}