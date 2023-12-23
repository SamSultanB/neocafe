package com.neocafe.neocafe.entities.order.requests

import com.neocafe.neocafe.entities.order.responses.OrderBranch
import java.io.Serializable

data class Order(
    val id: Int,
    val status: String,
    val user: Int? = null,
    val branch: OrderBranch,
    val items: List<OrderItem>,
    val created: String,
    val get_total_cost: String
): Serializable
