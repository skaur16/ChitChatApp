package com.example.chitchatapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chitchatapp.feature.editProfile.EditProfileScreen
import com.example.chitchatapp.feature.login.LoginScreen
import com.example.chitchatapp.feature.splash.SplashScreen

@Composable
fun ChatAppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screen.LOGIN.route){
        composable(Screen.SPLASH.route) {
            SplashScreen()
        }
        composable(Screen.LOGIN.route) {
            LoginScreen(navController)
        }
        composable(
            Screen.EDITPROFILE.format(),
            arguments =  listOf(
                navArgument("email"){
                    type = NavType.StringType
                }
            )
            ) {
            val email = it.arguments?.getString("email")?: error("Email not passed !")
            EditProfileScreen(viewModel(),email)
        }
    }

}