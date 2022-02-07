package com.example.fragment.presenter.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragment.databinding.RecycleViewFragmentBinding
import com.example.fragment.MyApp

class RecycleViewFragment : Fragment() {
    private var binding : RecycleViewFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = RecycleViewFragmentBinding.inflate(inflater, container, false)
        binding!!.recyclerView.adapter = CharacterAdapter((activity?.application as MyApp).viewModel)
        binding!!.recyclerView.layoutManager = LinearLayoutManager(activity)
        Log.d("1234ResFragmentMake", "Ok")
        return binding!!.root

    }
}