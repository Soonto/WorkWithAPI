package com.example.fragment.fragment.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface InterfaceApiService {
    @GET("1")
    fun getCharacter(): Call<Character>
    companion object{
        private var retrofit : Retrofit? = null
        private val baseUrl = "https://rickandmortyapi.com/api/character/"

        fun getApiService(): InterfaceApiService{
            if(retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(InterfaceApiService::class.java)
        }

    }
}