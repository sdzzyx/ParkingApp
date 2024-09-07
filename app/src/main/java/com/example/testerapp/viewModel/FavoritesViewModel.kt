package com.example.testerapp.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.testerapp.navigation.screens.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//viewmodel to handle Items/Favorite
open class FavoritesViewModel: ViewModel() {
    private val _favoriteItems = mutableStateListOf<Item>()
    val favoriteItems: List<Item> get() = _favoriteItems

    fun addFavorite(item: Item){
        if(!_favoriteItems.contains(item)){
            _favoriteItems.add(item)
        }
    }

    fun removeFavorite(item: Item){
        _favoriteItems.remove(item)
    }

    fun isFavorite(item: Item): Boolean {
        return _favoriteItems.contains(item)
    }
}