package com.example.fragment.domain.entities



data class Character (
    val id : Int,
    val name : String,
    val image : String,
    val location : Map<String, String>
)