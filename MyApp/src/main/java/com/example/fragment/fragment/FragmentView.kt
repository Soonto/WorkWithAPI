package com.example.fragment.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fragment.R

import com.example.fragment.databinding.FragmentViewBinding
import com.example.fragment.myInterface.CallBack


class FragmentView : Fragment() {
    lateinit var binding : FragmentViewBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentViewBinding.inflate(inflater, container, false)
        binding.Name.text = this.arguments?.getString(ARG_NAME)
        binding.location.text = this.arguments?.getString(ARG_LOCATION)
        return binding.root
    }
    fun nextPage(){
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer,FragmentView.newInstance{
                bundleOf(Pair(ARG_NAME, "Morty"), Pair(ARG_LOCATION, "SPB"))
            })
            .commit()
    }

    companion object{
        fun newInstance(callBack: CallBack): FragmentView {
            val fragment = FragmentView()
            fragment.arguments = callBack.getAnswer()
            return fragment
        }
        const val ARG_NAME = "arg_name"
        const val ARG_LOCATION = "arg_location"

    }


}