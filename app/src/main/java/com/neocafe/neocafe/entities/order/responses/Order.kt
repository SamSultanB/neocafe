package com.neocafe.neocafe.entities.order.responses

import com.neocafe.neocafe.entities.order.requests.MTO
import java.io.Serializable

data class Order(
    val branch: OrderBranch,
    val created: String,
    val items: List<MTO>
): Serializable
