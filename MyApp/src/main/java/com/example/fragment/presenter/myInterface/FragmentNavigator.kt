package com.example.fragment.presenter.myInterface

import com.example.fragment.domain.entities.Character
import com.example.fragment.data.MyCallBack

interface FragmentNavigator {
    fun getNextPage()
    fun getPreviousPage()
    fun setRecycleViewMode()
    fun setCommonMode()
    fun getCharacterList(callBack: MyCallBack<List<Character>>)
}