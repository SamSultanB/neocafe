package com.neocafe.neocafe.entities.menu.responses

import java.io.Serializable

data class ExtraItem(
    val id: Int? = null,
    val type_extra_product: String,
    val name: String,
    val price: String,
): Serializable
