package com.neocafe.neocafe.models.api.connections

import com.neocafe.neocafe.entities.order.requests.OrderItem
import com.neocafe.neocafe.entities.order.responses.OrderItemResponse
import com.neocafe.neocafe.entities.profile.responses.Profile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BasketApi {

    @POST("/api-order/create/order/")
    suspend fun order(@Body order: OrderItem): Response<OrderItemResponse>
    @GET("/api-customers/customer/profile/")
    suspend fun getProfile(): Response<Profile>

}