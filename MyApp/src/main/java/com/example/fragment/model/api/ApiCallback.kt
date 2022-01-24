package com.example.fragment.model.api

import com.example.fragment.model.Character
import com.example.fragment.model.MyCallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class ApiCallback(
    private val mainCallback: MyCallBack<Character>
) : Callback<Character> {

    override fun onResponse(call: Call<Character>, response: Response<Character>) {
        if (response.body() != null) {
            mainCallback.done(response.body()!!)
        }
    }
    override fun onFailure(call: Call<Character>, t: Throwable) {
        //TODO exception processing
    }

}