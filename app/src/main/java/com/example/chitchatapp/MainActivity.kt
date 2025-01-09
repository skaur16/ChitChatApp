package com.example.chitchatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chitchatapp.feature.splash.SplashScreen
import com.example.chitchatapp.feature.login.LoginScreen
import com.example.chitchatapp.ui.theme.ChitChatAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChitChatAppTheme {
                ChatAppNavHost()
            }
        }
    }
}

