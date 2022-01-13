package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import com.example.fragment.databinding.ActivityMainBinding
import com.example.fragment.fragment.FragmentView
import com.example.fragment.myApp.MyApp


class MainActivity : AppCompatActivity(){
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = (application as MyApp).myViewModel
        with(binding){
            cancelButton.setOnClickListener{viewModel.getPreviousPage()}
            nextButton.setOnClickListener{viewModel.getNextPage()}
        }
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainer.id, FragmentView.newInstance{
                bundleOf(Pair(FragmentView.ARG_NAME, "FirstName"), Pair(FragmentView.ARG_LOCATION, "FirstLocation"))
            })
            .addToBackStack(null)
            .commit()

    }

}