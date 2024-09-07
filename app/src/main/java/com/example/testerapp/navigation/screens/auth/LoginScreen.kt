package com.example.testerapp.navigation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.testerapp.R
import com.example.testerapp.navigation.AuthRouteScreen
import com.example.testerapp.navigation.Graph

@Composable
fun LogInScreen(navController: NavHostController) {

    var textInput by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var errorMessage by remember {
        mutableStateOf("")
    }
    var passwordVisibility by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(100.dp),
                //.align(Alignment.CenterHorizontally)
                //.padding(140.dp),
            painter = painterResource(id = R.drawable.logo_blue),
            contentDescription = "logoBlue")

        Spacer(modifier = Modifier.height(20.dp))


        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFF8F4F8))
                .padding(16.dp),

            value = textInput,
            onValueChange = {
//                if (it.all { char -> char.isLetter() || char.isWhitespace() }) {
//                    textInput = it
//                }
                textInput = it
            },
            keyboardOptions = KeyboardOptions(
//                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Email),

            cursorBrush = SolidColor(MaterialTheme.colorScheme.secondary),
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(
                color = Color(0xFF028CE2),
                fontSize = 20.sp
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
//                    androidx.compose.material3.Icon(
//                        imageVector = Icons.Default.Search,
//                        contentDescription = null)

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(modifier = Modifier.weight(1f)) {
                        if (textInput.isEmpty()) {
                            Text(
                                text = "Email:",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF028CE2))
                        } else {
                            innerTextField()
                        }
                    }

                    if(textInput.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(8.dp))

                        androidx.compose.material3.Icon(
                            modifier = Modifier.clickable {
                                textInput = ""
                            },
                            imageVector = Icons.Default.Clear,
                            contentDescription = null
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(10.dp))

        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFF8F4F8))
                .padding(16.dp),

            value = password,
            onValueChange = {
                if (it.all { char -> char.isLetterOrDigit() || char.isWhitespace() }) {
                    password = it
                }
            },

            visualTransformation = if(passwordVisibility)VisualTransformation.None else PasswordVisualTransformation(),

            keyboardOptions = KeyboardOptions(
                //imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password),

            cursorBrush = SolidColor(MaterialTheme.colorScheme.secondary),
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(
                color = Color(0xFF028CE2),
                fontSize = 20.sp
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
//                    androidx.compose.material3.Icon(
//                        imageVector = Icons.Default.Search,
//                        contentDescription = null)

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(modifier = Modifier.weight(1f)) {
                        if (password.isEmpty()) {
                            Text(
                                text = "Password:",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF028CE2))
                        } else {
                            innerTextField()
                        }
                    }

                    if(password.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(8.dp))

                        androidx.compose.material3.Icon(
                            modifier = Modifier.clickable {
                            passwordVisibility = !passwordVisibility
                            //password = ""
                            },
                            painter = painterResource(id = R.drawable.eye),
                            //imageVector = Icons.Default.Clear,
                            contentDescription = null,
                            tint = if(passwordVisibility) Color(0xFF657075) else Color(0xFFA6ABAD)
                        )
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        Column {
            Button(
                onClick = {
                    if (textInput == "juan.delacruz@gmail.com" && password == "juan2468"){

                        navController.navigate(Graph.MainScreenGraph){
                            popUpTo(AuthRouteScreen.Login.route){
                                inclusive = true
                            }
                        }
                        //will navigate to the main screen/ home screen
                        //navController.navigate(Screen.MainScreen.route)

                    } else{
                        errorMessage = "Error: Incorrect username or password"
                    }
                    //navController.navigate(Screen.Profile.route)
                },
                enabled = textInput.isNotEmpty() && password.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (textInput.isNotEmpty()) Color(0xFF028CE2) else Color(0xFFF0EBF1)
                ),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF028CE2)
                //),
                modifier = Modifier
                    .width(355.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(10.dp),

                ) {
                Text(
                    text = "Login",
                    Modifier.padding(vertical = 8.dp),
                    fontSize = 20.sp
                )

            }
            if (errorMessage.isNotEmpty()){
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = errorMessage,
                    color = Color(0xFFEC0A0A),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Composable
@Preview
fun LoginScreenPreview(){
    val navController = rememberNavController()
    LogInScreen(navController = navController)
}