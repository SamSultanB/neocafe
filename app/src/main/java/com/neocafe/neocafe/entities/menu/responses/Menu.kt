package com.neocafe.neocafe.entities.menu.responses

import java.io.Serializable
import java.time.temporal.TemporalAmount

data class Menu(
    val id: Int,
    val name: String,
    val slug: String,
    val category: Int?,
    val description: String,
    val image: String?,
    val price: String,
    val available: Boolean,
    val extra_product: List<String>,
    var amount: Int = 0
): Serializable
