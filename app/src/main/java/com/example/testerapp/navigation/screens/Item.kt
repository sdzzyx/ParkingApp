package com.example.testerapp.navigation.screens

import androidx.annotation.DrawableRes

data class Item(
    val title: String,
    val address: String,
    val price: String,
    @DrawableRes val image: Int,
    val description: String
)
