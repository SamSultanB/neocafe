package com.neocafe.neocafe.entities.order.requests

import java.io.Serializable

data class OrderItem(
    val order_type: String,
    val status: String,
    val branch: Int? = null,
    val user: Int? = null,
    val bonuses_used: String,
    val total_price: String,
    val items: List<MTO>
): Serializable
