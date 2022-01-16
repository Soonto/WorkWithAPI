package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.fragment.databinding.ActivityMainBinding
import com.example.fragment.model.api.Character
import com.example.fragment.view.FragmentView
import com.example.fragment.myApp.MyApp
import com.example.fragment.view.FragmentMaker
import com.example.fragment.view.FragmentView.Companion.ARG_ID
import com.example.fragment.view_model.ViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModel
    private var currentFragment: FragmentView? = null
    private val defFragment = FragmentView.newInstance(FragmentMaker(1, imgUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = (application as MyApp).myViewModel

        buttonBinding()
        setListener()
        if (savedInstanceState == null) {
            currentFragment = defFragment
            setDefFragment()
        }
    }

    private fun buttonBinding() {
        binding.cancelButton.setOnClickListener {
            onBackPressed()
            if(supportFragmentManager.backStackEntryCount == 0)
                binding.cancelButton.visibility = View.INVISIBLE
        }
        binding.nextButton.setOnClickListener { viewModel.getNextPage() }
    }

    private fun setDefFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainer.id, defFragment)
            .commit()
    }

    private fun setListener() {
        viewModel.character.observe(this) {
            setFragment(it)
        }
    }

    private fun setFragment(it: Character) {
        val currentFragment = FragmentView.newInstance(FragmentMaker(it.id, it.name, it.location["name"]!!, it.image))
        if (supportFragmentManager.fragments.last().arguments?.get(ARG_ID) != currentFragment.arguments?.get(ARG_ID)) {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentContainer.id, currentFragment)
                .addToBackStack(null)
                .commit()
        }
        binding.cancelButton.visibility = View.VISIBLE

    }
}