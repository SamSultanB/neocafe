package com.neocafe.neocafe.di

import android.content.Context
import com.neocafe.neocafe.adapters.FilialsRvAdapter
import com.neocafe.neocafe.adapters.MenuRvAdapter
import com.neocafe.neocafe.models.api.connections.AuthApi
import com.neocafe.neocafe.models.api.connections.BasketApi
import com.neocafe.neocafe.models.api.connections.BranchesApi
import com.neocafe.neocafe.models.api.connections.MenuApi
import com.neocafe.neocafe.models.api.connections.ProfileApi
import com.neocafe.neocafe.models.api.retrofit.SessionManager
import com.neocafe.neocafe.models.api.retrofit.TokenInterceptor
import com.neocafe.neocafe.models.repositories.AuthRepository
import com.neocafe.neocafe.models.repositories.BasketRepository
import com.neocafe.neocafe.models.repositories.BranchesRepository
import com.neocafe.neocafe.models.repositories.MenuRepository
import com.neocafe.neocafe.models.repositories.ProfileRepository
import com.neocafe.neocafe.utils.Constants
import com.neocafe.neocafe.viewModels.AuthViewModel
import com.neocafe.neocafe.viewModels.BasketViewModel
import com.neocafe.neocafe.viewModels.BranchesViewModel
import com.neocafe.neocafe.viewModels.MenuViewModel
import com.neocafe.neocafe.viewModels.ProfileViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val retrofitModule = module {
    single { getSessionManager(context = androidContext()) }
    single { getInterceptor(get()) }
    single { getRetrofit(get()) }
    single { getOkHttpClient(get()) }
    single { getAuthApi(get()) }
    single { getMenuApi(get()) }
    single { getBasketApi(get()) }
    single { getProfileApi(get()) }
    single{ getBranchesApi(get()) }
    factory { MenuRvAdapter() }
    single { FilialsRvAdapter() }
    single { BasketRepository(get()) }
    single { BranchesRepository(get()) }
    single { ProfileRepository(get()) }
    factory { MenuRepository(get()) }
    factory { AuthRepository(get())}
}

val viewModule = module {
    viewModel { MenuViewModel(get()) }
    viewModel { AuthViewModel(get(), get()) }
    viewModel { BranchesViewModel(get())}
    viewModel { ProfileViewModel(get()) }
    viewModel { BasketViewModel( get()) }
}

fun getRetrofit(okHttpClient: OkHttpClient): Retrofit{
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun getOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient{
    return OkHttpClient.Builder()
        .addInterceptor(tokenInterceptor)
        .build()
}

fun getAuthApi(retrofit: Retrofit): AuthApi{
    return retrofit.create(AuthApi::class.java)
}

fun getMenuApi(retrofit: Retrofit): MenuApi{
    return retrofit.create(MenuApi::class.java)
}

fun getBranchesApi(retrofit: Retrofit): BranchesApi{
    return retrofit.create(BranchesApi::class.java)
}

fun getProfileApi(retrofit: Retrofit): ProfileApi{
    return retrofit.create(ProfileApi::class.java)
}

fun getBasketApi(retrofit: Retrofit): BasketApi{
    return retrofit.create(BasketApi::class.java)
}

fun getInterceptor(sessionManager: SessionManager): TokenInterceptor{
    return TokenInterceptor(sessionManager)
}

fun getSessionManager(context: Context): SessionManager{
    return SessionManager(context)
}