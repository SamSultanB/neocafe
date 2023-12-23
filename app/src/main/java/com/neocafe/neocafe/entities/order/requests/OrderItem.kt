package com.neocafe.neocafe.entities.order.requests

import com.neocafe.neocafe.entities.order.responses.OrderExtraItem
import com.neocafe.neocafe.entities.order.responses.OrderMenu
import java.io.Serializable

data class OrderItem(
    val order: Int,
    val menu: OrderMenu,
    val menu_quantity: Int,
    val extra_product: OrderExtraItem,
    val extra_product_quantity: Int,
    val bonuses_used: String,
    val cashback: String
): Serializable
