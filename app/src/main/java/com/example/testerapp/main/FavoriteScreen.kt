package com.example.testerapp.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.testerapp.R
import com.example.testerapp.navigation.screens.Item
import com.example.testerapp.viewModel.FavoritesViewModel

@Composable
fun FavoriteScreen(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel,
//    image: Int,
    title: String?,
//    price: String?
){

    val favoriteItems = favoritesViewModel.favoriteItems

    var isFavorite by remember {
        mutableStateOf(favoritesViewModel.favoriteItems.any { it.title == title })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        //.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Text(
                text = "Favorites Parking",
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF3098DA),
                fontSize = 30.sp
            )

    }

    Box(
        modifier = Modifier.fillMaxSize()){
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.90f)
                .align(Alignment.BottomCenter)
        ){
            items(favoriteItems){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFECE9EC))
                        .fillMaxWidth()

                ) {
                        Image(
                            modifier = Modifier
                                .size(100.dp)
                                .padding(16.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .weight(1.5f),
                            painter = painterResource(id = it.image),
                            contentDescription = it.title,
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier
                                .weight(2f)
                        ) {
                            Text(
                                text = it.title,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = it.price,
                                fontSize = 10.sp,
                                color = Color.Gray)
                        }

//                    Column(modifier = Modifier){
//                        Text(text = "Favorite")
//                    }

                    Image(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(45.dp)
                            .clickable {
                                isFavorite = !isFavorite
                                //permanent clicked and unclicked behavior
                                //need to fixed this
                                // (if heart is being clicked
                                // it must have a toast message and
                                // will update to the detail screen
                                // if it is favorite or not)
                            },
                        painter = painterResource(
                            id = if(isFavorite) R.drawable.favorite_icon_filled
                        else R.drawable.favorite_unfilled),
                        contentDescription = "favorite")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFavoriteScreen() {
    val navController = rememberNavController()

    // Mocking a FavoritesViewModel with sample data
    val favoritesViewModel = object : FavoritesViewModel() {
        init {
            addFavorite(Item("Anson's Parking", image = R.drawable.parking, price = "Php 100.00", description = "", address = ""))
            addFavorite(Item("Glorietta Parking", image = R.drawable.parking2, price = "Php 100.00", description = "", address = ""))
            addFavorite(Item("Ayala Mall Parking", image = R.drawable.parking3, price = "Php 100.00", description = "", address = ""))
        }
    }

    FavoriteScreen(navController = navController, favoritesViewModel = favoritesViewModel, title = String())
}