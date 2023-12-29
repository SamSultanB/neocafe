package com.neocafe.neocafe.entities.order.requests

import java.io.Serializable

data class MenuDetails(
    val image: String? = "",
    val name: String,
    val description: String,
    val price: String
): Serializable
