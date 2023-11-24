package com.neocafe.neocafe.utils

import java.io.Serializable

data class TestCategory(val name: String, val products: List<Menu>, val image: Int): Serializable
