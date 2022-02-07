package com.example.fragment.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fragment.data.api.ApiCallback
import com.example.fragment.data.api.InterfaceApiService
import com.example.fragment.domain.Repository
import com.example.fragment.domain.entities.Character


fun interface MyCallBack<T> {
    fun done(character: T)
}
class MyRepository(
) : Repository {

    private val apiService = InterfaceApiService.Factory.getApiService()
    override val character: LiveData<Character>
        get() = TODO("Not yet implemented")

    override fun getCharter(id: Int, myCallBack: MyCallBack<Character>) {
        apiService.getCharacter(id.toString()).enqueue(ApiCallback(myCallBack))
    }
    override suspend fun getChartersList(begin: Int, end: Int, myCallBack: MyCallBack<List<Character>>) {
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


class TestRepository : Repository {

    private val _character = MutableLiveData<Character>()
    override val character : LiveData<Character> = _character

    override fun getCharter(id: Int, myCallBack: MyCallBack<Character>) {
        TODO("Not yet implemented")
    }

    override suspend fun getChartersList(
        begin: Int,
        end: Int,
        myCallBack: MyCallBack<List<Character>>
    ) {
        TODO("Not yet implemented")
    }

}

