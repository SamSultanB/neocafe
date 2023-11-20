package com.neocafe.neocafe.entities.registration

import com.google.gson.annotations.SerializedName
import java.util.Date

data class RegistrationForm(
    val name: String,
    val phone_number: String,
    val date_of_birth: String? = null
)
