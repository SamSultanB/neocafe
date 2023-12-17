package com.neocafe.neocafe.entities.order.requests

import java.io.Serializable

data class Order(
    val status: String? = null,
    val user: Int? = null,
    val menu: List<MenuItem>,
    val extra_products: List<OrderExtraItem>,
    val items: List<OrderItem>,
    val bonuses_writen_off: String,
    val cashback: String,
    val created: String,
    val updated: String,
    val branch: Int,
    val get_total_cost: String
): Serializable
