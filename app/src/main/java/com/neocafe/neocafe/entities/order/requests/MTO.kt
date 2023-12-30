package com.neocafe.neocafe.entities.order.requests

import java.io.Serializable

data class MTO(
    val menu: Int,
    val menu_detail: MenuDetails,
    var menu_quantity: Int,
    val extra_product: Int? = null,
    val extra_product_detail: ExtraProductDetails? = null,
    var extra_product_quantity: Int
): Serializable
