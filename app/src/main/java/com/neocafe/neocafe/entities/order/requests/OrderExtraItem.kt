package com.neocafe.neocafe.entities.order.requests

import java.io.Serializable

data class OrderExtraItem(
    val name: String,
    val price: String,
): Serializable
