package com.example.fragment.view_model

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fragment.Model
import com.example.fragment.MyModel
import com.example.fragment.model.api.Character
import com.example.fragment.myInterface.FragmentNavigator

class ViewModel(
    val model : Model
) : ViewModel(), FragmentNavigator {
    private val _character = MutableLiveData<com.example.fragment.model.api.Character>()
    val character : LiveData<com.example.fragment.model.api.Character> = _character
    var idCounter = 1
    override fun getNextPage() {
        model.getCharter(idCounter){
            _character.value = it
            idCounter++
        }
    }

    override fun getPreviousPage() {
    }

}