package com.example.fragment.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fragment.model.Model
import com.example.fragment.model.api.Character
import com.example.fragment.myInterface.FragmentNavigator
import kotlin.random.Random

class ViewModel(
    val model: Model,
    savedStateHandle: SavedStateHandle
) : ViewModel(), FragmentNavigator {
    private val _character =    savedStateHandle.getLiveData<Character>(KEY)
    val character: LiveData<Character> = _character
    private val maxIdCharacter = 826
    override fun getNextPage() {
        model.getCharter(Random.nextInt(1, maxIdCharacter)) {
            _character.value = it
        }
    }
    override fun getPreviousPage() {
    }
    companion object{
        const val KEY = "key"
    }

}