package com.neocafe.neocafe.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neocafe.neocafe.entities.branches.Branche
import com.neocafe.neocafe.models.api.retrofit.Resource
import com.neocafe.neocafe.models.repositories.BranchesRepository
import kotlinx.coroutines.launch

class BranchesViewModel(private val repository: BranchesRepository): ViewModel() {

    val getAllBranchesResponse: MutableLiveData<Resource<List<Branche>>> = MutableLiveData()
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

}