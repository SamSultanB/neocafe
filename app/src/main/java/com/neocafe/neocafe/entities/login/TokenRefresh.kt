package com.neocafe.neocafe.entities.login

import java.io.Serializable

data class TokenRefresh(
    val refresh: String,
    val access: String
): Serializable
