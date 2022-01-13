package com.example.fragment

import android.app.Application
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.fragment.fragment.FragmentNavigator
import com.example.fragment.fragment.FragmentView

class ViewModel(
    model : Model
) : FragmentNavigator, Fragment() {
    override fun getNextPage() {


    }

    override fun getPreviousPage() {
    }

}