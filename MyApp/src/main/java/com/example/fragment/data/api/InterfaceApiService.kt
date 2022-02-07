package com.example.fragment.data.api

import com.example.fragment.domain.entities.Character
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface InterfaceApiService {

    @GET("{id}")
    fun getCharacter(@Path("id") employeeId: String): Call<Character>
    object Factory{
        private var retrofit : Retrofit? = null
        private const val baseUrl = "https://rickandmortyapi.com/api/character/"

        val okHttpClient = OkHttpClient().newBuilder().addInterceptor(MyInterceptor()).build()

        fun getApiService(): InterfaceApiService {
            if(retrofit == null) {
                retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(InterfaceApiService::class.java)
        }

    }
}

