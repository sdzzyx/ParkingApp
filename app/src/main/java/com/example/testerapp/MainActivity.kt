package com.example.testerapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testerapp.navigation.graphs.RootNavGraph
import com.example.testerapp.navigation.screens.Item
import com.example.testerapp.ui.theme.TesterAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    companion object{
        val items: List<Item> = listOf(
            Item(
                title = "Anson Parking",
                address = "Makati City, Philipines",
                image = R.drawable.parking,
                price = "Php 100.00",
                description = "Anson's is a well-known appliance and electronics retail chain in the Philippines, " +
                        "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                        "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                        "Anson's provides convenient access to its extensive product lineup.\n" +
                        "\nAnson's is a well-known appliance and electronics retail chain in the Philippines, " +
                        "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                        "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                        "Anson's provides convenient access to its extensive product lineup." + "\nAnson's is a well-known appliance and electronics retail chain in the Philippines, " +
                        "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                        "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                        "Anson's provides convenient access to its extensive product lineup.\n" + "\nAnson's is a well-known appliance and electronics retail chain in the Philippines, " +
                        "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                        "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                        "Anson's provides convenient access to its extensive product lineup.\n"
            ),

            Item(
                title = "Glorietta Parking",
                address = "Makati City, Philipines",
                image = R.drawable.parking2,
                price = "Php 100.00",
                description = "Glorietta is a well-known appliance and electronics retail chain in the Philippines, " +
                        "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                        "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                        "Anson's provides convenient access to its extensive product lineup.\n" +
                        "\nAnson's is a well-known appliance and electronics retail chain in the Philippines, " +
                        "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                        "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                        "Anson's provides convenient access to its extensive product lineup." + "\nAnson's is a well-known appliance and electronics retail chain in the Philippines, " +
                        "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                        "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                        "Anson's provides convenient access to its extensive product lineup.\n" + "\nAnson's is a well-known appliance and electronics retail chain in the Philippines, " +
                        "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                        "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                        "Anson's provides convenient access to its extensive product lineup.\n"
            ),

            Item(
                title = "Ayala Parking",
                address = "Makati City, Philipines",
                image = R.drawable.parking3,
                price = "Php 100.00",
                description = "Ayala is a well-known appliance and electronics retail chain in the Philippines, " +
                        "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                        "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                        "Anson's provides convenient access to its extensive product lineup.\n" +
                        "\nAnson's is a well-known appliance and electronics retail chain in the Philippines, " +
                        "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                        "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                        "Anson's provides convenient access to its extensive product lineup." + "\nAnson's is a well-known appliance and electronics retail chain in the Philippines, " +
                        "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                        "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                        "Anson's provides convenient access to its extensive product lineup.\n" + "\nAnson's is a well-known appliance and electronics retail chain in the Philippines, " +
                        "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                        "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                        "Anson's provides convenient access to its extensive product lineup.\n"
            ),

            Item(
                title = "McKinley Parking",
                address = "Taguig City, Philipines",
                image = R.drawable.parking4,
                price = "Php 100.00",
                description = "McKinley is is a well-known appliance and electronics retail chain in the Philippines, " +
                        "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                        "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                        "Anson's provides convenient access to its extensive product lineup.\n" +
                        "\nAnson's is a well-known appliance and electronics retail chain in the Philippines, " +
                        "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                        "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                        "Anson's provides convenient access to its extensive product lineup." + "\nAnson's is a well-known appliance and electronics retail chain in the Philippines, " +
                        "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                        "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                        "Anson's provides convenient access to its extensive product lineup.\n" + "\nAnson's is a well-known appliance and electronics retail chain in the Philippines, " +
                        "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                        "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                        "Anson's provides convenient access to its extensive product lineup.\n"
            )
        )
    }


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        //3 seconds duration on splashscreen
        lifecycleScope.launch {
            delay(3000)
        }
        setContent {
            TesterAppTheme {
                RootNavGraph()
            }
        }
    }
}

