package com.neocafe.neocafe.entities.order

import androidx.lifecycle.MutableLiveData
import com.neocafe.neocafe.entities.menu.responses.Menu
import com.neocafe.neocafe.entities.order.requests.ExtraProductDetails
import com.neocafe.neocafe.entities.order.requests.MTO
import com.neocafe.neocafe.entities.order.requests.MenuDetails
import kotlin.math.roundToInt

object Basket {

    val order: MutableMap<String, Menu> = mutableMapOf()

    val orderForRequest: MutableMap<Int, MTO> = mutableMapOf()

    var totalPrice: Int = 0
    val totalPriceInBasket: MutableLiveData<Int> = MutableLiveData()

    fun addMenu(menu: Menu){
        val menuInOrders = order.get(menu.name)
        val orderMenu = orderForRequest.get(menu.id)
        if(menuInOrders != null){
            if(menuInOrders.extraProduct != null){
                orderMenu!!.extra_product_quantity ++
                val decimal = menuInOrders.extraProduct?.price!!.toDouble()
                totalPrice += decimal.roundToInt()
            }
            menuInOrders.amount ++
            orderMenu!!.menu_quantity ++
            val decimal = menu.price.toDouble()
            totalPrice += decimal.roundToInt()
            totalPriceInBasket.postValue(totalPrice)
        }else{
            menu.amount ++
            order.put(menu.name, menu)
            val menuDetails = MenuDetails(menu.image, menu.name, menu.description, menu.price)
            orderForRequest.put(menu.id, MTO(menu.id, menuDetails, menu.amount, null ,ExtraProductDetails("", "0"), 0))

            val decimal = menu.price.toDouble()
            totalPrice += decimal.roundToInt()
            totalPriceInBasket.postValue(totalPrice)
        }
    }

    fun addMenuWithExtra(menu: Menu){
        order.put(menu.name, menu)
        val menuDetails = MenuDetails(menu.image, menu.name, menu.description, menu.price)
        orderForRequest.put(menu.id, MTO(menu.id, menuDetails, menu.amount, menu.extraProduct?.id ,ExtraProductDetails(menu.extraProduct?.name, menu.extraProduct?.price), menu.amount))
        val decimal = menu.price.toDouble()
        val extraPrice = menu.extraProduct?.price?.toDouble()
        totalPrice += extraPrice?.roundToInt()!!*menu.amount
        totalPrice += decimal.roundToInt()*menu.amount
        totalPriceInBasket.postValue(totalPrice)
    }

    fun delete(menu: Menu){
        val menuInOrders = order.get(menu.name)
        val orderMenu = orderForRequest.get(menu.id)
        if(menuInOrders != null){
            if(menuInOrders.amount == 1){
                order.remove(menu.name)
                orderForRequest.remove(menu.id)
                val decimal = menu.price.toDouble()
                totalPrice -= decimal.roundToInt()
                totalPriceInBasket.postValue(totalPrice)
            }else{
                if(menuInOrders.extraProduct != null){
                    val decimal = menuInOrders.extraProduct?.price!!.toDouble()
                    orderMenu!!.extra_product_quantity --
                    totalPrice -= decimal.roundToInt()
                }
                menuInOrders.amount --
                orderMenu!!.menu_quantity --
                orderMenu.extra_product_quantity --
                val decimal = menu.price.toDouble()
                totalPrice -= decimal.roundToInt()
                totalPriceInBasket.postValue(totalPrice)

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