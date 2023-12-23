package com.neocafe.neocafe.entities.profile.requests

import java.io.Serializable

data class ProfileRequest(
    val first_name: String,
    val phone_number: String,
    val date_of_birth: String
): Serializable
