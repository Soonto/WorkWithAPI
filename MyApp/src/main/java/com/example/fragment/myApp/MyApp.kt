package com.example.fragment.myApp

import android.app.Application
import com.example.fragment.Model
import com.example.fragment.MyModel
import com.example.fragment.ViewModel
import com.example.fragment.fragment.api.InterfaceApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp : Application() {
    val myModel = MyModel()
    val myViewModel = ViewModel(myModel)

    override fun onCreate() {
        super.onCreate()

    }



}