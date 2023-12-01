package com.neocafe.neocafe.di

import android.content.Context
import com.neocafe.neocafe.models.api.connections.AuthApi
import com.neocafe.neocafe.models.api.connections.MenuApi
import com.neocafe.neocafe.models.api.retrofit.SessionManager
import com.neocafe.neocafe.models.api.retrofit.TokenInterceptor
import com.neocafe.neocafe.models.repositories.AuthRepository
import com.neocafe.neocafe.models.repositories.MenuRepository
import com.neocafe.neocafe.utils.Constants
import com.neocafe.neocafe.viewModels.AuthViewModel
import com.neocafe.neocafe.viewModels.MenuViewModel
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
    factory { MenuRepository(get()) }
    factory { AuthRepository(get())}
}

val viewModule = module {
    viewModel { MenuViewModel(get()) }
    viewModel { AuthViewModel(get(), get()) }
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

fun getInterceptor(sessionManager: SessionManager): TokenInterceptor{
    return TokenInterceptor(sessionManager)
}

fun getSessionManager(context: Context): SessionManager{
    return SessionManager(context)
}