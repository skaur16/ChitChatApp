package com.example.chitchatapp.feature.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.chitchatapp.Screen
import com.example.chitchatapp.ui.theme.Primary
import com.example.chitchatapp.ui.theme.Secondary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController
                )
{
    Scaffold (
            topBar = {
                    TopAppBar(
                        title = {
                            Text("Welcome to ChitChat")
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Secondary,
                            titleContentColor = Color.White
                        )
                    )
            }
    ){
        val context = LocalContext.current
        Box (
            modifier = Modifier.fillMaxSize().padding(it),
            contentAlignment = Alignment.Center

        ){
            SignInWithGoogleButton(
                onSuccess = {user->
                    val email = user.email ?: error("Email not found!")
                    Toast.makeText(context, "Signed In as $email", Toast.LENGTH_SHORT).show()
                    navController.navigate(Screen.EDITPROFILE(email).route)
                },
                onError = {exception->
                    Toast.makeText(context, "Error caused :${exception?.message}", Toast.LENGTH_SHORT).show()
                })
        }
    }
    
}