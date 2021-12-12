package com.example.fragment.fragment

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragment.R
import com.example.fragment.databinding.FragmentViewBinding
import java.lang.Thread.sleep

interface Base{
    fun doAny()
}

class FragmentView : Fragment() {
    private lateinit var binding: FragmentViewBinding
    @SuppressLint("StringFormatInvalid")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewBinding.inflate(inflater, container, false)
        binding.viewNumOfFragment.text = getString(R.string.NumberOfFragment) + getFragmentCounter().toString()
        binding.nextButton.setOnClickListener{
            binding.nextButton.visibility = INVISIBLE
            binding.progressBar.visibility = VISIBLE
            createNewFragment()}
        binding.cancelButton.setOnClickListener{requireActivity().onBackPressed()
        }
        return binding.root
    }

    private fun createNewFragment() {
        val a = Runnable {
            sleep(1000)
            val fragment = FragmentView.newInstance(getFragmentCounter()+1)
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }
        Thread(a).start()


    }

    private fun getFragmentCounter(): Int {
        return requireArguments().getInt(ARG_COUNTER)
    }

    companion object{
        fun newInstance(number : Int): FragmentView {
            val args = Bundle()
            val fragment = FragmentView()
            args.putInt(ARG_COUNTER, number)
            fragment.arguments = args
            return fragment
        }
        const val ARG_COUNTER = "ARG_COUNTER"
    }
}