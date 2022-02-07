package com.example.fragment.presenter.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.fragment.databinding.FragmentViewBinding
import com.example.fragment.core.CallBack
import com.squareup.picasso.Picasso


class FragmentView : Fragment() {
    lateinit var binding : FragmentViewBinding
    val a = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        if(savedInstanceState != null)
            Log.d("1234","onCreate not null "+ arguments?.get(ARG_ID).toString())
        super.onCreate(savedInstanceState)
        Log.d("1234","onCreate "+ arguments?.get(ARG_ID).toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("1234", "onDestroy " + arguments?.get(ARG_ID).toString())
        Unit
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("1234", "onAttach " + arguments?.get(ARG_ID).toString())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.d("1234","onCreateView "+ arguments?.get(ARG_ID).toString())
        val picasso = Picasso.get()
        binding = FragmentViewBinding.inflate(inflater, container, false)
        binding.Name.text = this.arguments?.getString(ARG_NAME)
        binding.location.text = this.arguments?.getString(ARG_LOCATION)
        picasso.load(this.arguments?.getString(ARG_IMGURL)).into(binding.imagePlace)
        return binding.root
    }




    companion object{
        fun newInstance(callBack: CallBack): FragmentView {
            val fragment = FragmentView()
            fragment.arguments = callBack.getAnswer()
            return fragment
        }
        const val ARG_NAME = "arg_name"
        const val ARG_LOCATION = "arg_location"
        const val ARG_IMGURL = "arg_img"
        const val ARG_ID = "id"

    }


}

fun FragmentView.funn(){
    a
}