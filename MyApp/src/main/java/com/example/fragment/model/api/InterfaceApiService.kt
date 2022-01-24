package com.example.fragment.model.api

import com.example.fragment.model.Character
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface InterfaceApiService {

    @GET("{id}")
    fun getCharacter(@Path("id") employeeId: String): Call<Character>
    companion object{
        private var retrofit : Retrofit? = null
        private const val baseUrl = "https://rickandmortyapi.com/api/character/"

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