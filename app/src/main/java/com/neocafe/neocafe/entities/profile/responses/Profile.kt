package com.neocafe.neocafe.entities.profile.responses

import com.neocafe.neocafe.entities.order.requests.Order
import com.neocafe.neocafe.entities.order.responses.OrderResponse
import java.io.Serial
import java.io.Serializable

data class Profile(
    val first_name: String,
    val phone_number: String,
    val date_of_birth: String,
    val bonuses: String,
    val active_orders: List<Order>,
    val completed_orders: List<Order>
): Serializable
