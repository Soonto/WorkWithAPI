package com.example.fragment.domain

import androidx.lifecycle.LiveData
import com.example.fragment.domain.entities.Character
import com.example.fragment.data.MyCallBack

interface Repository {
    val character : LiveData<Character>
    fun getCharter(id: Int, myCallBack: MyCallBack<Character>)
    suspend fun getChartersList(begin: Int = 0, end: Int = 100, myCallBack: MyCallBack<List<Character>>)
}
