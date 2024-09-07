package com.example.testerapp.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.testerapp.MainActivity
import com.example.testerapp.R
import com.example.testerapp.navigation.MainRouteScreen
import com.example.testerapp.navigation.screens.Item
import com.example.testerapp.viewModel.FavoritesViewModel

@Composable
fun HomeScreen(navController: NavController, favoritesViewModel: FavoritesViewModel){
    var searchInput by remember{
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.2f) // 80% of screen height
            .background(
                color = Color(0xFF22A1F1),
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
            )
            .padding(top = 16.dp) // Optional padding
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 10.dp),
                text = "Location",
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                modifier = Modifier
                    .padding(start = 10.dp),
                text = "Taguig City",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))

            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFF8F4F8))
                    .padding(16.dp),

                value = searchInput,
                onValueChange = {
                    searchInput = it
                },
                cursorBrush = SolidColor(MaterialTheme.colorScheme.secondary),
                maxLines = 1,
                singleLine = true,
                textStyle = TextStyle(
                    color = Color(0xFF028CE2),
                    fontSize = 20.sp
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        if (searchInput.isEmpty()) {
                            Image(painter = painterResource(
                                id = R.drawable.pin),
                                contentDescription = "pin",
                                modifier = Modifier
                                    .padding(end = 10.dp)
                                    .size(20.dp))
                            } else {
                                innerTextField()
                            }
                        if(searchInput.isEmpty()) {
                            Text(
                                text = "Search",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF91969B)
                                )
                        }
                    }
                }
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, top = 180.dp, bottom = 20.dp, end = 10.dp)

//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 15.dp, start = 22.dp),
            text = "Popular",
        color = Color(0xFF028CE2),
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
        LazyRow(
            modifier = Modifier,
                //.fillMaxSize()
                //.padding(top = 2.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(MainActivity.items){_, item ->
                Spacer(modifier = Modifier.width(10.dp))
                RowItem(item = item, navController = navController)
                Spacer(modifier = Modifier.width(10.dp))

            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            modifier = Modifier
                .padding(start = 22.dp),
            text = "Nearby Parking",
            color = Color(0xFF028CE2),
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
        LazyRow(
            modifier = Modifier,
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(MainActivity.items){ _, item ->
                Spacer(modifier = Modifier.width(10.dp))
                NearbyItem(item = item, navController = navController)
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@Composable
fun RowItem(item: Item, navController: NavController){
    Column(
        modifier = Modifier
            .clickable {
                navController.navigate(MainRouteScreen.DetailScreen.createRoute(item))
            },
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .width(140.dp)
                .clip(RoundedCornerShape(10.dp)),
            painter = painterResource(id = item.image),
            contentDescription = (item.title),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.title,
            fontWeight = FontWeight.SemiBold)
        //Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = item.price,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color(0xFF7E8388)
        )
    }

}

@Composable
fun NearbyItem(item: Item, navController: NavController){
    Column(
        modifier = Modifier
            .clickable {
                navController.navigate(MainRouteScreen.DetailScreen.createRoute(item))
            },
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier
//                .clickable {
//                    navController.navigate(MainRouteScreen.DetailScreen.createRoute(item))
//                }
                .fillMaxWidth()
                .height(130.dp)
                .width(140.dp)
                .clip(RoundedCornerShape(10.dp)),
            painter = painterResource(id = item.image),
            contentDescription = (item.title),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.title,
            fontWeight = FontWeight.SemiBold)

        Text(
            text = item.price,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color(0xFF7E8388)
        )
    }
}


@Composable
@Preview
fun HomeScreenPreview(){
    val navController = rememberNavController()
    val favoritesViewModel = FavoritesViewModel()
    HomeScreen(navController, favoritesViewModel)
}


