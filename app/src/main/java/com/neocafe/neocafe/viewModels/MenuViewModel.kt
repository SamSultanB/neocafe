package com.neocafe.neocafe.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neocafe.neocafe.entities.branches.Branche
import com.neocafe.neocafe.entities.categories.Category
import com.neocafe.neocafe.entities.menu.responses.Menu
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.models.repositories.MenuRepository
import com.neocafe.neocafe.utils.Constants
import kotlinx.coroutines.launch

class MenuViewModel(private val repository: MenuRepository): ViewModel()  {

    val getCategoriesResponse: MutableLiveData<Resource<List<Category>>> = MutableLiveData()

    val getPopularsResponse: MutableLiveData<Resource<List<Menu>>> = MutableLiveData()

    val getMenuResponse: MutableLiveData<Resource<List<Menu>>> = MutableLiveData()

    val getAllBranchesResponse: MutableLiveData<Resource<List<Branche>>> = MutableLiveData()

    val getAllMenuResponse: MutableLiveData<Resource<List<Menu>>> = MutableLiveData()

    fun getCategories(){
        viewModelScope.launch {
            getCategoriesResponse.postValue(Resource.Loading())
            val response = repository.getCategories()
            if(response.isSuccessful){
                response.body()?.let {
                    getCategoriesResponse.postValue(Resource.Success(it))
                }
            }else{
//                getCategoriesResponse.postValue(Resource.Error(response.message()))
                val error = response.errorBody()
                getCategoriesResponse.postValue(Resource.Error(error!!.string()))
            }
        }
    }

    fun getPopulars(id: Int){
        viewModelScope.launch {
            getPopularsResponse.postValue(Resource.Loading())
            val response = repository.getPopulars()
            if(response.isSuccessful){
                response.body()?.let {
                    getPopularsResponse.postValue(Resource.Success(it))
                }
            }else {
//                getPopularsResponse.postValue(Resource.Error(response.message()))
                val error = response.errorBody()
                getPopularsResponse.postValue(Resource.Error(error!!.string()))
            }
        }
    }

    fun getMenu(slug: String, id: Int){
        viewModelScope.launch {
            getPopularsResponse.postValue(Resource.Loading())
            val response = repository.getMenu(slug)
            if(response.isSuccessful){
                response.body()?.let {
                    getMenuResponse.postValue(Resource.Success(it))
                }
            }else {
                getMenuResponse.postValue(Resource.Error(response.message()))
            }
        }
    }

    fun getAllBranches(){
        viewModelScope.launch {
            getAllBranchesResponse.postValue(Resource.Loading())
            val response = repository.getAllBranches()
            if(response.isSuccessful){
                response.body()?.let {
                    getAllBranchesResponse.postValue(Resource.Success(it))
                }
            }else{
                getAllBranchesResponse.postValue(Resource.Error(response.message()))
            }
        }
    }

    fun getAllMenu(id: Int){
        viewModelScope.launch {
            getAllMenuResponse.postValue(Resource.Loading())
            val response = repository.getAllMenu()
            if(response.isSuccessful){
                response.body()?.let {
                    getAllMenuResponse.postValue(Resource.Success(it))
                }
            }else{
//                getAllMenuResponse.postValue(Resource.Error(response.message()))
                val error = response.errorBody()
                getAllMenuResponse.postValue(Resource.Error(error!!.string()))
            }
        }
    }


}