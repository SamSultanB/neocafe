package com.neocafe.neocafe.models.entities

import java.io.Serializable

data class TokenRefresh(
    val refresh: String,
    val access: String
): Serializable
