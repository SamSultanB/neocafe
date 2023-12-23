package com.neocafe.neocafe.entities.branches

import java.io.Serializable

data class Branche(
    val id: Int,
    val name: String,
    val address: String,
    val map_link: String,
    val phone_number: String,
    val monday: String,
    val tuesday: String,
    val wednesday: String,
    val thursday: String,
    val friday: String,
    val saturday: String,
    val sunday: String,
    val image: String
): Serializable
