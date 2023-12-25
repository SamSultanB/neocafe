package com.neocafe.neocafe.entities.branches

import java.io.Serializable

data class Branche(
    val id: Int,
    val name: String,
    val address: String,
    val map_link: String,
    val phone_number: String,
    
    val monday: String,
    val monday_start_time: String,
    val monday_end_time: String,

    val tuesday: String,
    val tuesday_start_time: String,
    val tuesday_end_time: String,

    val wednesday: String,
    val wednesday_start_time: String,
    val wednesday_end_time: String,

    val thursday: String,
    val thursday_start_time: String,
    val thursday_end_time: String,

    val friday: String,
    val friday_start_time: String,
    val friday_end_time: String,

    val saturday: String,
    val saturday_start_time: String,
    val saturday_end_time: String,

    val sunday: String,
    val sunday_start_time: String,
    val sunday_end_time: String,

    val image: String
): Serializable
