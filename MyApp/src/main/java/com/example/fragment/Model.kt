package com.example.fragment

import android.util.Log
import com.example.fragment.fragment.api.InterfaceApiService
import com.example.fragment.myApp.MyApp

interface Model {
    fun getCharter()
}

class MyModel(
) : Model {
    private val apiService = InterfaceApiService.getApiService()
    override fun getCharter() {
        Log.d("1233456", apiService.getCharacter().toString())
    }

}
