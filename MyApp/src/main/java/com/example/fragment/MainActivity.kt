package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.example.fragment.databinding.ActivityMainBinding
import com.example.fragment.view.FragmentView
import com.example.fragment.myApp.MyApp
import com.example.fragment.view_model.ViewModel


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: ViewModel
    private val currentFragment = FragmentView.newInstance {
        bundleOf(
            Pair(FragmentView.ARG_NAME, "FirstName"),
            Pair(FragmentView.ARG_LOCATION, "FirstLocation")
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = (application as MyApp).myViewModel

        with(binding) {
            cancelButton.setOnClickListener { viewModel.getPreviousPage() }
            nextButton.setOnClickListener { viewModel.getNextPage() }
        }
        startFragment()
        setListener()
    }
    private fun startFragment(){
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainer.id, currentFragment)
            .addToBackStack(null)
            .commit()

    }
    private fun setListener() {
        viewModel.character.observe(this) {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentContainer.id, FragmentView.newInstance {
                    bundleOf(
                        Pair(FragmentView.ARG_NAME, it.name),
                        Pair(FragmentView.ARG_LOCATION, "qweqrw")
                    )
                })
                .addToBackStack(null)
                .commit()
        }
    }

}