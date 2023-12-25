package com.neocafe.neocafe.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neocafe.neocafe.entities.order.requests.OrderItem
import com.neocafe.neocafe.entities.order.responses.OrderItemResponse
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.models.repositories.BasketRepository
import kotlinx.coroutines.launch

class BasketViewModel(private val repository: BasketRepository): ViewModel() {

    val orderResponse: MutableLiveData<Resource<OrderItemResponse>> = MutableLiveData()

    fun order(order: OrderItem){
        viewModelScope.launch {
            orderResponse.postValue(Resource.Loading())
            val response = repository.order(order)
            if(response.isSuccessful){
                response.body()?.let {
                    orderResponse.postValue(Resource.Success(it))
                }
            }else{
//                registrationResponse.postValue(Resource.Error(response.message()))
                val error = response.errorBody()
                orderResponse.postValue(Resource.Error(error!!.string()))

            }
        }
    }

}