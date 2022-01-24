package com.example.fragment

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.View
import com.example.fragment.databinding.ActivityMainBinding
import com.example.fragment.model.Character
import com.example.fragment.view.FragmentView
import com.example.fragment.myApp.MyApp
import com.example.fragment.view.FragmentFactory
import com.example.fragment.view.FragmentView.Companion.ARG_ID
import com.example.fragment.view.RecycleViewFragment
import com.example.fragment.view_model.MainActivityViewModel
import com.example.fragment.view_model.Mode


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var viewModel : MainActivityViewModel? = null
    private var currentFragment: FragmentView? = null
    private val defFragment = FragmentView.newInstance(
        FragmentFactory(
            id = 1,
            imgUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg"
        )
    )
    var mode = Mode.COMMON
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (application as MyApp).viewModel
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonBinding()
        setListeners()
        if (savedInstanceState == null) {
            mode = Mode.COMMON
            currentFragment = defFragment
            setDefFragment()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun buttonBinding() {
        binding.cancelButton.setOnClickListener {
            onBackPressed()
            if (supportFragmentManager.backStackEntryCount == 0)
                binding.cancelButton.visibility = View.INVISIBLE
        }
        binding.nextButton.setOnClickListener { viewModel?.getNextPage() }
        val gestureDetector = GestureDetector(this, GestureListener(viewModel!!))
        binding.fragmentContainer.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }

    private fun setDefFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainer.id, defFragment)
            .commit()
    }

    private fun setListeners() {
        if(mode == Mode.COMMON){
        viewModel?.character?.observe(this) {
            setFragment(it)
        }}
        else{
            setRecycleView()
        }

        viewModel?.mode?.observe(this){
            mode = it
            setRecycleView()
        }
    }

    private fun setFragment(it: Character) {
        val currentFragment =
            FragmentView.newInstance(FragmentFactory(it.id, it.name, it.location["name"]!!, it.image))
        if (supportFragmentManager.fragments.last().arguments?.get(ARG_ID) != currentFragment.arguments?.get(ARG_ID)) {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentContainer.id, currentFragment)
                .addToBackStack(null)
                .commit()
        }
        binding.cancelButton.visibility = View.VISIBLE

    }
    private fun setRecycleView(){
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainer.id, RecycleViewFragment())
            .commit()
    }

}