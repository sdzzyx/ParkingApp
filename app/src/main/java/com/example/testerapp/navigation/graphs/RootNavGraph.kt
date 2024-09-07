package com.example.testerapp.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testerapp.main.MainScreen
import com.example.testerapp.navigation.Graph
import com.example.testerapp.viewModel.FavoritesViewModel

@Composable
fun RootNavGraph(){
    //isAuth: Boolean, parameter in RootNavGraph to only show the homescreen
    val rootNavController = rememberNavController()
    val favoritesViewModel: FavoritesViewModel = viewModel()

    NavHost(
        navController = rootNavController,
        route = Graph.RootGraph,
        startDestination = Graph.AuthGraph //if (isAuth) Graph.MainScreenGraph else
    ) {
        authNavGraph(rootNavController = rootNavController)
        composable(route = Graph.MainScreenGraph){
            MainScreen(rootNavHostController = rootNavController, favoritesViewModel = favoritesViewModel)
        }
    }
}