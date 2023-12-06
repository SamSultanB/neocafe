package com.neocafe.neocafe.entities.order

import java.io.Serializable

data class OrderItem(
    val menu: MenuItem,
    val extra_product: OrderExtraItem,
    val quantity: Int
): Serializable
