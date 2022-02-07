package com.example.fragment.presenter.viewModel

import androidx.lifecycle.*
import com.example.fragment.domain.entities.Character
import com.example.fragment.data.MyCallBack
import com.example.fragment.domain.Repository
import com.example.fragment.presenter.myInterface.FragmentNavigator
import kotlinx.coroutines.launch
import kotlin.random.Random

enum class Mode{
    RECYCLE_VIEW,
    COMMON
}

class MainActivityViewModel(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel(), FragmentNavigator {
    private var _character =    savedStateHandle.getLiveData<Character>(KEY_CHARACTER)
    private val _mode =  savedStateHandle.getLiveData<Mode>(KEY_MODE)

    val character: LiveData<Character> = _character
    val mode : LiveData<Mode> = _mode

    private val maxIdCharacter = 826
    var characterList = listOf<Character>()
    val observer  = Observer<Character> {
        _character.value = it
    }


    override fun getNextPage() {
        repository.getCharter(Random.nextInt(1, maxIdCharacter)) {
            _character.value = it
        }
    }

    override fun getCharacterList(callBack: MyCallBack<List<Character>>) {
        viewModelScope.launch{
            repository.getChartersList(0, 100) {
                callBack.done(it)
                characterList = it
            }
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

    override fun onCleared() {
        super.onCleared()
        repository.character.removeObserver(observer)
    }
}