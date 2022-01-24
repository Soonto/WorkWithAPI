package com.example.fragment.view

import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.fragment.myInterface.CallBack

class FragmentFactory(
    private val id: Int = 0,
    private val name: String = "",
    private val location: String ="",
    private val imgUrl: String = ""
) : CallBack {

    override fun getAnswer(): Bundle {
        return bundleOf(
            Pair(FragmentView.ARG_ID, id),
            Pair(FragmentView.ARG_NAME, name),
            Pair(FragmentView.ARG_LOCATION, location),
            Pair(FragmentView.ARG_IMGURL, imgUrl))
    }
}


