package com.example.fragment.myInterface

import com.example.fragment.model.Character
import com.example.fragment.model.MyCallBack

interface FragmentNavigator {
    fun getNextPage()
    fun getPreviousPage()
    fun setRecycleViewMode()
    fun setCommonMode()
    fun getCharacterList(callBack: MyCallBack<List<Character>>)
}