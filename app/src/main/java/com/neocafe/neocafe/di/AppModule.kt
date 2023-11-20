package com.neocafe.neocafe.di

import android.content.Context
import com.neocafe.neocafe.models.api.connections.AuthApi
import com.neocafe.neocafe.models.api.retrofit.SessionManager
import com.neocafe.neocafe.models.api.retrofit.TokenInterceptor
import com.neocafe.neocafe.models.repositories.AuthRepository
import com.neocafe.neocafe.utils.Constants
import com.neocafe.neocafe.viewModels.AuthViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single { getSessionManager(context = androidContext()) }
    single { getInterceptor(get()) }
    single { getRetrofit(get()) }
    single { getOkHttpClient(get()) }
    single { getAuthApi(get()) }
    factory { AuthRepository(get())}
}

val viewModule = module {
    viewModel { AuthViewModel(get()) }
}

fun getRetrofit(okHttpClient: OkHttpClient): Retrofit{
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
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

fun getInterceptor(sessionManager: SessionManager): TokenInterceptor{
    return TokenInterceptor(sessionManager)
}

fun getSessionManager(context: Context): SessionManager{
    return SessionManager(context)
}