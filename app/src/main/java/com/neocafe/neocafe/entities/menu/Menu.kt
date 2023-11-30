package com.neocafe.neocafe.entities.menu

import java.io.Serializable

data class Menu(
    val id: Int,
    val name: String,
    val slug: String,
    val category: Int?,
    val description: String,
    val image: String?,
    val price: String,
    val available: Boolean,
    val popular: Boolean
): Serializable
