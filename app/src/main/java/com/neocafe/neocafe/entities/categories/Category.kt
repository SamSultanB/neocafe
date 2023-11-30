package com.neocafe.neocafe.entities.categories

import java.io.Serializable

data class Category(
    val name: String,
    val slug: String,
    val image: String
): Serializable
