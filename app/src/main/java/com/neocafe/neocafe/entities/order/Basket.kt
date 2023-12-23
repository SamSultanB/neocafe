package com.neocafe.neocafe.entities.order

import com.neocafe.neocafe.entities.menu.responses.Menu
import kotlin.math.roundToInt

object Basket {

    val order: MutableMap<String, Menu> = mutableMapOf()

    var totalPrice: Int = 0

    fun addMenu(menu: Menu){
        val menuInOrders = order.get(menu.name)
        if(menuInOrders != null){
            menuInOrders.amount ++
            val decimal = menu.price.toDouble()
            totalPrice += decimal.roundToInt()
        }else{
            menu.amount ++
            order.put(menu.name, menu)
            val decimal = menu.price.toDouble()
            totalPrice += decimal.roundToInt()
        }
    }

    fun delete(menu: Menu){
        val menuInOrders = order.get(menu.name)
        if(menuInOrders != null){
            if(menuInOrders.amount == 1){
                order.remove(menu.name)
                totalPrice = 0
            }else{
                menuInOrders.amount --
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