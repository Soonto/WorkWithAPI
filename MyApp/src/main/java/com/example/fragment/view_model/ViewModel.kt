package com.example.fragment.view_model

import androidx.lifecycle.*
import com.example.fragment.model.Repository
import com.example.fragment.model.Character
import com.example.fragment.model.MyCallBack
import com.example.fragment.myInterface.FragmentNavigator
import java.security.CryptoPrimitive
import kotlin.random.Random

enum class Mode{
    RECYCLE_VIEW,
    COMMON
}
class MyListener<T> : Observer<T>{
    override fun onChanged(t: T?) {
        TODO("Not yet implemented")
    }

}
class MainActivityViewModel(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel(), FragmentNavigator {
    private val _character =    savedStateHandle.getLiveData<Character>(KEY_CHARACTER)
    private val _mode =  savedStateHandle.getLiveData<Mode>(KEY_MODE)

    val character: LiveData<Character> = _character
    val mode : LiveData<Mode> = _mode

    private val maxIdCharacter = 826
    var characterList = listOf<Character>()
    override fun getNextPage() {
        repository.getCharter(Random.nextInt(1, maxIdCharacter)) {
            _character.value = it
        }
    }

    override fun getCharacterList(callBack: MyCallBack<List<Character>>) {
        repository.getChartersList(0, 100){
            callBack.done(it)
            characterList = it
        }
    }
    override fun getPreviousPage() {
    }

    override fun setRecycleViewMode() {
        _mode.value = Mode.RECYCLE_VIEW
    }

    override fun setCommonMode() {
        _mode.value = Mode.COMMON
    }



    companion object{
        const val KEY_CHARACTER = "key_character"
        const val  KEY_MODE = "key_mode"
    }

}