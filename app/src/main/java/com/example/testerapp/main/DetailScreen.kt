package com.example.testerapp.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.testerapp.R
import com.example.testerapp.navigation.MainRouteScreen
import com.example.testerapp.navigation.screens.Item
import com.example.testerapp.viewModel.FavoritesViewModel


@Composable
fun DetailScreen(
    navController: NavController,
    title: String?,
    address: String?,
    image: Int?,
    price: String?,
    description: String?,
    favoritesViewModel: FavoritesViewModel
){

    var isFavorite by remember {
        mutableStateOf(favoritesViewModel.favoriteItems.any { it.title == title })
    }

    image?.let {
        Image(
            painter = painterResource(id = it),
            contentDescription = title,
            modifier = Modifier
                .fillMaxWidth()
                .size(250.dp),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
        ) {
            Image(
                modifier = Modifier
                    .clickable {
                        navController.navigate(MainRouteScreen.Home.route)
                    }
                    .padding(16.dp)
                    .size(70.dp),
                painter = painterResource(id = R.drawable.back_icon_button),
                contentDescription = "back icon")

            Image(
                modifier = Modifier
                    .padding(start = 190.dp, top = 30.dp)
                    .clickable {
                        isFavorite = !isFavorite
                        val item = Item(title ?: "", address ?: "", price ?: "", image ?: 0, description ?: "")
                        if (isFavorite) {
                            favoritesViewModel.addFavorite(item)
                        } else {
                            favoritesViewModel.removeFavorite(item)
                        }
                    }
                    .size(45.dp),
                painter = painterResource(
                    id = if(isFavorite)R.drawable.favorite_icon_filled
                    else R.drawable.favorite_unfilled),
                contentDescription = "favorite")

            Image(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .size(45.dp),
                painter = painterResource(id = R.drawable.share_icon),
                contentDescription = "share")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 250.dp, start = 15.dp, end = 15.dp, bottom = 130.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = title ?: "No Title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = address ?: "No Title",
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                modifier = Modifier
                    //.padding(start = 80.dp)
                    .size(70.dp),
                painter = painterResource(id = R.drawable.nearme_icon),
                contentDescription = "nearme")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier
            .padding(bottom = 40.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
            ){
            Text(
                text = description ?: "No Description",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp * 0.2f) // 20% of screen height
                .background(
                    color = Color(0xFF22A1F1),
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                )
                //.padding(top = 16.dp) // Optional padding
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .height(100.dp)
                ) {
                    Column (){
                        Text(
                            text = "TOTAL PRICE",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        //Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "PHP 100.00/hr",
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(70.dp))
                    Button(onClick = {
                    },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier.size(500.dp, 40.dp),
                        shape = RoundedCornerShape(20.dp)

                    ) {
                        Text(
                            text = "Book Slot",
                            color = Color(0xFF22A1F1),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                            )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    DetailScreen(
        navController = rememberNavController(),
        title = "Anson's Parking",
        address = "Makati City, Philippines",
        image = R.drawable.parking,
        price = "Php 100.00",
        description = "Anson's is a well-known appliance and electronics retail chain in the Philippines, " +
                "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                "Anson's provides convenient access to its extensive product lineup.\n" +
                "\nAnson's is a well-known appliance and electronics retail chain in the Philippines, " +
                "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                "Anson's provides convenient access to its extensive product lineup.\n"
                + "\nAnson's is a well-known appliance and electronics retail chain in the Philippines, " +
                "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
                "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
                "Anson's provides convenient access to its extensive product lineup.\n",
        favoritesViewModel = FavoritesViewModel()
//                + "\nAnson's is a well-known appliance and electronics retail chain in the Philippines, " +
//                "offering a wide range of products including kitchen appliances, entertainment systems, and household essentials. " +
//                "With branches located in key cities such as Makati, Quezon City and Muntinlupa. " +
//                "Anson's provides convenient access to its extensive product lineup.\n"
    )
}

