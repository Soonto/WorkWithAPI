package com.example.fragment.myApp

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.example.fragment.model.MyRepository
import com.example.fragment.view_model.MainActivityViewModel


class MyApp : Application() {
    val myRepository = MyRepository()
    val viewModel = MainActivityViewModel(myRepository, SavedStateHandle())
}