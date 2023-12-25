package com.neocafe.neocafe.entities.order.responses

data class OrderItemResponse(
    val order: Int,
    val menu: OrderMenu,
    val menu_quantity: Int,
    val extra_product: OrderExtraItem,
    val extra_product_quantity: Int,
    val bonuses_used: String,
    val cashback: String
)
