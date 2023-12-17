package com.neocafe.neocafe.entities.order.requests

import java.io.Serializable

data class MenuItem(
    val name: String,
    val price: String
): Serializable
