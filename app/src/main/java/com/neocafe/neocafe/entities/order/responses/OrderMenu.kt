package com.neocafe.neocafe.entities.order.responses

import java.io.Serializable

data class OrderMenu(
    val image: String,
    val name: String,
    val price: String
): Serializable
