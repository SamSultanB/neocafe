package com.neocafe.neocafe.models.api.retrofit

import com.neocafe.neocafe.models.api.connections.AuthApi
import com.neocafe.neocafe.utils.Constants
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
                .build()

        }

        val authApi by lazy {
            retrofit.create(AuthApi::class.java)
        }

    }

}