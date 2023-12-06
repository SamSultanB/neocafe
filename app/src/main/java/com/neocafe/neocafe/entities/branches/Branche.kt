package com.neocafe.neocafe.entities.branches

import java.io.Serializable

data class Branche(
    val id: Int,
    val name: String,
    val address: String,
    val phone_number: String,
    val work_schedule: String,
    val image: String
): Serializable
