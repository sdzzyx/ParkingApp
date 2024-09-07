package com.example.testerapp.navigation

import com.example.testerapp.navigation.screens.Item

object Graph {
    const val RootGraph = "rootGraph"
    const val AuthGraph = "authgraph"
    const val MainScreenGraph = "mainScreenGraph"

}

sealed class AuthRouteScreen(var route: String){
    object Login: AuthRouteScreen("login")
}

sealed class MainRouteScreen(var route: String){

    object Home: MainRouteScreen("home")

    object Favorites: MainRouteScreen("favorite_screen/{image}/{title}/{price}"){
        fun createRoute(item: Item) = "favorite_screen/${item.image}/${item.title}/${item.price}"
    }

    object Profile: MainRouteScreen("profile")

    object DetailScreen: MainRouteScreen("detail_screen/{title}/{address}/{image}/{price}/{description}"){
        fun createRoute(item: Item) = "detail_screen/${item.title}/${item.address}/${item.image}/${item.price}/${item.description}"
    }
}