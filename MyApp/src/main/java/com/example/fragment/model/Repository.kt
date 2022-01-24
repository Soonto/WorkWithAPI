package com.example.fragment.model

import android.util.Log
import com.example.fragment.model.api.ApiCallback
import com.example.fragment.model.api.InterfaceApiService
import retrofit2.Callback
import retrofit2.Response

interface Repository {
    fun getCharter(id: Int, myCallBack: MyCallBack<Character>)
    fun getChartersList(begin: Int = 0, end: Int = 100, myCallBack: MyCallBack<List<Character>>)
}

fun interface MyCallBack<T> {
    fun done(character: T)
}


class MyRepository(
) : Repository {
    private val apiService = InterfaceApiService.getApiService()
    override fun getCharter(id: Int, myCallBack: MyCallBack<Character>) {
        apiService.getCharacter(id.toString()).enqueue(ApiCallback(myCallBack))
    }
    override fun getChartersList(begin: Int, end: Int, myCallBack: MyCallBack<List<Character>>) {
        val characterList = mutableListOf<Character>()
        for (index in begin..end) {
            Log.d("1234", index.toString() )
            apiService.getCharacter(index.toString()).enqueue(ApiCallback {
                characterList.add(it)
                if(characterList.size == end - begin){
                    Log.d("1234", "characterList.size ${characterList.size}" )
                    myCallBack.done(characterList)
                }
            })


        }
        //Log.d("11234", "characterList.size ${characterList.size}" )

    }
}




