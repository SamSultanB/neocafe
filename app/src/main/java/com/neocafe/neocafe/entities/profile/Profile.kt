package com.neocafe.neocafe.entities.profile

import com.neocafe.neocafe.entities.order.requests.Order

data class Profile(
    val full_name: String,
    val phone_number: String,
    val date_of_birth: String,
    val bonuses: String,
    val orders: List<Order>
)
