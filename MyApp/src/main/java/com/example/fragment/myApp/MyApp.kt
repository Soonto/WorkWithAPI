package com.example.fragment.myApp

import android.app.Application
import com.example.fragment.MyModel
import com.example.fragment.view_model.ViewModel

class MyApp : Application() {
    private val myModel = MyModel()
    val myViewModel = ViewModel(myModel)


    override fun onCreate() {
        super.onCreate()

    }



}