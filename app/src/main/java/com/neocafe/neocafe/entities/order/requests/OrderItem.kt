package com.neocafe.neocafe.entities.order.requests

import java.io.Serializable

data class OrderItem(
    val menu_quantity: Int,
    val extra_product_quantity: Int
): Serializable
