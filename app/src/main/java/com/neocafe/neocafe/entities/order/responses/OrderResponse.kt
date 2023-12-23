package com.neocafe.neocafe.entities.order.responses

import com.neocafe.neocafe.entities.order.requests.MenuItem
import com.neocafe.neocafe.entities.order.requests.ExtraItem
import com.neocafe.neocafe.entities.order.requests.OrderItem

data class OrderResponse(
    val id: Int,
    val status: String? = null,
    val user: Int? = null,
    val menu: List<MenuItem>,
    val extra_products: List<ExtraItem>,
    val items: List<OrderItem>,
    val bonuses_writen_off: String,
    val cashback: String,
    val created: String,
    val updated: String,
    val branch: Int,
    val get_total_cost: String
)
