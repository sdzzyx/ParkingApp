package com.example.testerapp.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.testerapp.navigation.AuthRouteScreen
import com.example.testerapp.navigation.Graph
import com.example.testerapp.navigation.screens.auth.LogInScreen

fun NavGraphBuilder.authNavGraph(rootNavController: NavHostController){
    navigation(
        route = Graph.AuthGraph,
        startDestination = AuthRouteScreen.Login.route
    ){
        composable(route = AuthRouteScreen.Login.route){
            LogInScreen(navController = rootNavController)
        }
    }
}