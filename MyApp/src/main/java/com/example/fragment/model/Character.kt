package com.example.fragment.model

data class Character(
    val id : Int,
    val name : String,
    val image : String,
    val location : Map<String, String>
)