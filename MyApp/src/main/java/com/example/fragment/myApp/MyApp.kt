package com.example.fragment.myApp

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.example.fragment.model.MyModel
import com.example.fragment.view_model.ViewModel

class MyApp : Application() {
    private val myModel = MyModel()
    val myViewModel = ViewModel(myModel, SavedStateHandle())


    override fun onCreate() {
        super.onCreate()

    }



}