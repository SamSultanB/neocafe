package com.neocafe.neocafe.di

import com.neocafe.neocafe.models.api.connections.AuthApi
import com.neocafe.neocafe.models.api.retrofit.RetrofitInstance
import com.neocafe.neocafe.models.repositories.AuthRepository
import com.neocafe.neocafe.viewModels.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val retrofitModule = module {
    single { getRetrofit() }
    single { getAuthApi(get()) }
    factory { AuthRepository(get())}
}

val viewModule = module {
    viewModel { AuthViewModel(get()) }
}

fun getRetrofit(): Retrofit{
    return RetrofitInstance.retrofit
}

fun getAuthApi(retrofit: Retrofit): AuthApi{
    return retrofit.create(AuthApi::class.java)
}