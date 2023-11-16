package com.neocafe.neocafe.models.api.retrofit

import com.neocafe.neocafe.models.api.api.AuthApi
import com.neocafe.neocafe.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{

//        private val tokenInterceptor = TokenInterceptor(SessionManager())
//        private val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(tokenInterceptor)
//            .build()

        private val retrofit by lazy {

            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
                .build()

        }

        val apiAuth by lazy {
            retrofit.create(AuthApi::class.java)
        }

    }

}