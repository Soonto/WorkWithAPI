package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.fragment.databinding.ActivityMainBinding
import com.example.fragment.fragment.FragmentView
import java.util.*
import java.util.regex.Pattern

open class MyTextWatcher() : TextWatcher{
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }


    override fun afterTextChanged(s: Editable?) {

    }

}
lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.inputText.addTextChangedListener (object : MyTextWatcher(){
           override fun afterTextChanged(s: Editable?) {
               // Toast.makeText(this@MainActivity, Patterns.EMAIL_ADDRESS.matcher(s.toString()).toString(), Toast.LENGTH_SHORT).show()
               val a = s.toString()
               if(a.endsWith("@ma")){
                   with(binding.inputText) {
                       setText("${a}il.ru")
                       //this.text?.let { setSelection(it.length) }
                       binding.password.visibility = View.INVISIBLE
                       binding.progressBar.visibility = View.VISIBLE
                   }
               }

            }
        })
        val fragment = FragmentView.newInstance(0)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .commit()
    }

}