package com.neocafe.neocafe.entities.order

import java.io.Serializable

data class OrderExtraItem(
    val name: String,
    val price: String,
): Serializable
