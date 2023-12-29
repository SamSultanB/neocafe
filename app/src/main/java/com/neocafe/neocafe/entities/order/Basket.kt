package com.neocafe.neocafe.entities.order

import com.neocafe.neocafe.entities.menu.responses.ExtraItem
import com.neocafe.neocafe.entities.menu.responses.Menu
import com.neocafe.neocafe.entities.order.requests.ExtraProductDetails
import com.neocafe.neocafe.entities.order.requests.MTO
import com.neocafe.neocafe.entities.order.requests.MenuDetails
import kotlin.math.roundToInt

object Basket {

    val order: MutableMap<String, Menu> = mutableMapOf()

    val orderForRequest: MutableMap<Int, MTO> = mutableMapOf()

    var totalPrice: Int = 0

    fun addMenu(menu: Menu){
        val menuInOrders = order.get(menu.name)
        val orderMenu = orderForRequest.get(menu.id)
        if(menuInOrders != null){
            menuInOrders.amount ++
            orderMenu!!.menu_quantity ++
            val decimal = menu.price.toDouble()
            totalPrice += decimal.roundToInt()
        }else{
            menu.amount ++
            order.put(menu.name, menu)
            val menuDetails = MenuDetails(menu.image, menu.name, menu.description, menu.price)

            orderForRequest.put(menu.id, MTO(menu.id,menuDetails, menu.amount, 1 ,ExtraProductDetails("", ""), menu.amount))
            val decimal = menu.price.toDouble()
            totalPrice += decimal.roundToInt()
        }
    }

    fun delete(menu: Menu){
        val menuInOrders = order.get(menu.name)
        val orderMenu = orderForRequest.get(menu.id)
        if(menuInOrders != null){
            if(menuInOrders.amount == 1){
                order.remove(menu.name)
                orderForRequest.remove(menu.id)
                totalPrice = 0
            }else{
                menuInOrders.amount --
                orderMenu!!.menu_quantity --
                val decimal = menu.price.toDouble()
                totalPrice -= decimal.roundToInt()
            }
        }
    }

    fun getAmount(menu: Menu): Int{
        val menuInOrders = order.get(menu.name)
        if (menuInOrders != null){
            return menuInOrders.amount
        }else{
            return 0
        }
    }

}