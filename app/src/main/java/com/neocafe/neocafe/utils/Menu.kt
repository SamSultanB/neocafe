package com.neocafe.neocafe.utils

import java.io.Serializable

data class Menu(val name: String, val category: String, val image: Int, val description: String, val price: Int, var amount: Int = 0): Serializable
