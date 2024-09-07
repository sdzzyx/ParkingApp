package com.example.testerapp.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testerapp.navigation.BottomNavigationBar
import com.example.testerapp.navigation.graphs.MainNavGraph
import com.example.testerapp.utils.bottomNavigationItemsList
import com.example.testerapp.viewModel.FavoritesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    rootNavHostController: NavHostController,
    homeNavController: NavHostController = rememberNavController(),
    favoritesViewModel: FavoritesViewModel

){

    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentRoute by remember(navBackStackEntry) {
        derivedStateOf {
            navBackStackEntry?.destination?.route
        }
    }
//    val topBarTitle by remember(currentRoute) {
//        derivedStateOf {
//            if (currentRoute != null) {
//                bottomNavigationItemsList[bottomNavigationItemsList.indexOfFirst {
//                    it.route == currentRoute
//                }].title
//            } else {
//                bottomNavigationItemsList[0].title
//            }
//        }
//    }
    Scaffold(
//        topBar = {
//            TopAppBar(title = {
//                Text(text = topBarTitle)
//            })
//        },
        bottomBar = {
            BottomNavigationBar(items = bottomNavigationItemsList, currentRoute = currentRoute ){ currentNavigationItem->
                homeNavController.navigate(currentNavigationItem.route){
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    homeNavController.graph.startDestinationRoute?.let { startDestinationRoute ->
                        // Pop up to the start destination, clearing the back stack
                        popUpTo(startDestinationRoute) {
                            // Save the state of popped destinations
                            saveState = true
                        }
                    }

                    // Configure navigation to avoid multiple instances of the same destination
                    launchSingleTop = true

                    // Restore state when re-selecting a previously selected item
                    restoreState = true
                }
            }
        }
    ) { innerPadding->
        MainNavGraph(rootNavHostController, homeNavController, favoritesViewModel, innerPadding)
    }
}