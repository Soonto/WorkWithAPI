package com.example.fragment.model

import com.example.fragment.model.api.Character
import com.example.fragment.model.api.InterfaceApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface Model {
    fun getCharter(id : Int ,isSuccess: IsSuccess)
}

fun interface IsSuccess {
    fun done(character: Character)
}

class MyModel(
) : Model {
    private val apiService = InterfaceApiService.getApiService()
    override fun getCharter(id : Int, isSuccess: IsSuccess) {
        var answer: Character?

        apiService.getCharacter(id.toString()).enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.body() != null) {
                    answer = response.body()!!
                    isSuccess.done(answer!!)
                }
            }
            override fun onFailure(call: Call<Character>, t: Throwable) {
            }

        })
    }
}



