package com.neocafe.neocafe.models.repositories

import com.neocafe.neocafe.entities.order.requests.OrderItem
import com.neocafe.neocafe.models.api.connections.BasketApi

class BasketRepository(private val basketApi: BasketApi) {

    suspend fun order(orderItem: OrderItem) = basketApi.order(orderItem)

    suspend fun profile() = basketApi.getProfile()

}