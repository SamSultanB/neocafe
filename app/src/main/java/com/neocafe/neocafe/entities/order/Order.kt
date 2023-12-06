package com.neocafe.neocafe.entities.order

import java.io.Serializable

data class Order(
    val items: List<OrderItem>
): Serializable
