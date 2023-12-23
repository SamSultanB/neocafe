package com.neocafe.neocafe.entities.order.requests

import java.io.Serializable

data class ExtraItem(
    val type_extra_product: String,
    val name: String,
    val price: String,
): Serializable
