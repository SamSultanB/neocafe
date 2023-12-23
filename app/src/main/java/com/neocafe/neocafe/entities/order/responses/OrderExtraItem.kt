package com.neocafe.neocafe.entities.order.responses

import java.io.Serializable

data class OrderExtraItem(
    val name: String,
    val price: String
): Serializable
