package com.neocafe.neocafe.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neocafe.neocafe.entities.categories.Category
import com.neocafe.neocafe.entities.menu.Menu
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.models.repositories.MenuRepository
import kotlinx.coroutines.launch

class MenuViewModel(private val repository: MenuRepository): ViewModel()  {

    val getCategoriesResponse: MutableLiveData<Resource<List<Category>>> = MutableLiveData()

    val getPopularsResponse: MutableLiveData<Resource<List<Menu>>> = MutableLiveData()

//    fun getCategories(){
//        viewModelScope.launch {
//            getCategoriesResponse.postValue(Resource.Loading())
//            val response = repository.getCategories()
//            if(response.isSuccessful){
//                response.body()?.let {
//                    getCategoriesResponse.postValue(Resource.Success(it))
//                }
//            }else{
//                getCategoriesResponse.postValue(Resource.Error(response.message()))
//            }
//        }
//    }

//    fun getPopulars(){
//        viewModelScope.launch {
//            getPopularsResponse.postValue(Resource.Loading())
//            val response = repository.getPopulars()
//            if(response.isSuccessful){
//                response.body()?.let {
//                    getPopularsResponse.postValue(Resource.Success(it))
//                }
//            }else(
//                getPopularsResponse.postValue(Resource.Error(response.message()))
//            )
//        }
//    }

}