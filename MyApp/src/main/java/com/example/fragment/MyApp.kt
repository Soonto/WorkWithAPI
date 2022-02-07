package com.example.fragment

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.example.fragment.data.MyRepository
import com.example.fragment.presenter.viewModel.MainActivityViewModel


class MyApp : Application() {
    val myRepository = MyRepository()
    val viewModel = MainActivityViewModel(myRepository, SavedStateHandle())
}