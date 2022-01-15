package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.example.fragment.databinding.ActivityMainBinding
import com.example.fragment.model.api.Character
import com.example.fragment.view.FragmentView
import com.example.fragment.myApp.MyApp
import com.example.fragment.view_model.ViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModel
    var fragmentCounter = 1
    private val defFragment = FragmentView.newInstance {
        bundleOf(
            Pair(FragmentView.ARG_NAME, "NaN"),
            Pair(FragmentView.ARG_LOCATION, "Nan"),
            Pair(FragmentView.ARG_IMGURL, "https://rickandmortyapi.com/api/character/avatar/2.jpeg")
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("123", fragmentCounter.toString())
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = (application as MyApp).myViewModel

        with(binding) {
            cancelButton.setOnClickListener {
                onBackPressed()
                fragmentCounter--
                if (fragmentCounter < 2)
                    cancelButton.visibility = View.INVISIBLE
            }
            nextButton.setOnClickListener { viewModel.getNextPage() }
        }
        setListener()
        if (savedInstanceState == null) {
            setDefFragment()
            Log.d("123def", fragmentCounter.toString())

        } else {
            fragmentCounter = savedInstanceState.getInt(ARG_FRAGMENT_COUNTER)
            Log.d("123out", fragmentCounter.toString())
        }


    }
    private fun setDefFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainer.id, defFragment)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("123in", fragmentCounter.toString())
        outState.putInt(ARG_FRAGMENT_COUNTER, fragmentCounter)
    }

    private fun setListener() {
        viewModel.character.observe(this) {
            setFragment(it)
        }
    }
    private fun setFragment(it : Character){
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainer.id, FragmentView.newInstance {
                bundleOf(
                    Pair(FragmentView.ARG_NAME, it.name),
                    Pair(FragmentView.ARG_LOCATION, it.location.getValue("name")),
                    Pair(FragmentView.ARG_IMGURL, it.image)
                )
            })
            .addToBackStack(null)
            .commit()
        fragmentCounter++
        binding.cancelButton.visibility = View.VISIBLE

    }
    companion object {
        const val ARG_FRAGMENT_COUNTER = "fragment_counter"
    }
}