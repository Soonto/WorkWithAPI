package com.example.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fragment.model.api.Character
import com.example.fragment.model.api.InterfaceApiService
import com.example.fragment.myInterface.CallBack
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
        var answer: Character? = Character(123, "t")

        apiService.getCharacter(id.toString()).enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.body() != null) {
                    answer = response.body()!!
                    Log.d("12345", response.body().toString())
                    isSuccess.done(answer!!)
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
            }

        })
        Log.d("12345", "1")
    }


}



