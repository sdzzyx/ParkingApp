package com.example.testerapp.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testerapp.main.DetailScreen
import com.example.testerapp.main.FavoriteScreen
import com.example.testerapp.main.HomeScreen
import com.example.testerapp.main.ProfileScreen
import com.example.testerapp.navigation.Graph
import com.example.testerapp.navigation.MainRouteScreen
import com.example.testerapp.viewModel.FavoritesViewModel

@Composable
fun MainNavGraph(
    rootNavController: NavController,
    homeNavController: NavHostController,
    favoritesViewModel: FavoritesViewModel,
    innerPadding: PaddingValues){

    NavHost(
        navController = homeNavController,
        route = Graph.MainScreenGraph,
        startDestination = MainRouteScreen.Home.route
        ){
        composable(route = MainRouteScreen.Home.route){
            HomeScreen(navController = homeNavController, favoritesViewModel = favoritesViewModel)
        }
        composable(route = MainRouteScreen.Favorites.route){
            FavoriteScreen(navController = homeNavController, favoritesViewModel = favoritesViewModel, title = String())
        }
        composable(route = MainRouteScreen.Profile.route){
            ProfileScreen()
        }
        composable(route = MainRouteScreen.DetailScreen.route,
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("address") { type = NavType.StringType},
                navArgument("image") { type = NavType.IntType },
                navArgument("price") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
            ){backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            val address = backStackEntry.arguments?.getString("address")
            val image = backStackEntry.arguments?.getInt("image")
            val price = backStackEntry.arguments?.getString("price")
            val description = backStackEntry.arguments?.getString("description")

            DetailScreen(homeNavController, title, address, image, price, description, favoritesViewModel)
        }
    }
}

