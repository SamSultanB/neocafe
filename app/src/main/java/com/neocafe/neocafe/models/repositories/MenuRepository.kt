package com.neocafe.neocafe.models.repositories

import com.neocafe.neocafe.models.api.connections.MenuApi

class MenuRepository(private val menuApi: MenuApi) {

    suspend fun getCategories() = menuApi.getCategory()

    suspend fun getPopulars() = menuApi.getPopulars()

}