package com.example.testerapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import com.example.testerapp.R
import com.example.testerapp.navigation.MainRouteScreen
import com.example.testerapp.navigation.NavigationItem


val bottomNavigationItemsList = listOf(
    NavigationItem(
        title = "Home",
        route = MainRouteScreen.Home.route,
        selectedIcon = R.drawable.tab_home_selected,
        unSelectedIcon = R.drawable.tab_home_gray,
    ),
    NavigationItem(
        title = "Favorites",
        route = MainRouteScreen.Favorites.route,
        selectedIcon = R.drawable.tab_favorite_selected,
        unSelectedIcon = R.drawable.tab_favorite_unselected,
        //badgeCount = 0
    ),
    NavigationItem(
        title = "Profile",
        route = MainRouteScreen.Profile.route,
        selectedIcon = R.drawable.tab_profile_selected,
        unSelectedIcon = R.drawable.tab_profile_unselected,
    )
)