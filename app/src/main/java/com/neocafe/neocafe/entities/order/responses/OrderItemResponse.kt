package com.neocafe.neocafe.entities.order.responses

import com.neocafe.neocafe.entities.order.requests.MTO

data class OrderItemResponse(
    val id: Int,
    val order_type: String,
    val status: String,
    val branch: Int,
    val user: Int? = null,
    val bonuses_used: Int,
    val created: String,
    val total_price: Int,
    val cashback: String,
    val items: List<MTO>
)
