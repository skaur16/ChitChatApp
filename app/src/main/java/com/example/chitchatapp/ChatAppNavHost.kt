package com.example.chitchatapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chitchatapp.data.remote.UserRepo
import com.example.chitchatapp.feature.editProfile.EditProfileScreen
import com.example.chitchatapp.feature.home.HomeScreen
import com.example.chitchatapp.feature.login.LoginScreen
import com.example.chitchatapp.feature.login.LoginViewModel
import com.example.chitchatapp.feature.splash.SplashScreen
import com.example.chitchatapp.feature.splash.SplashViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.factory.KoinViewModelFactory

@Composable
fun ChatAppNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Screen.SPLASH.route)

    {

        composable(Screen.SPLASH.route) {
            SplashScreen(navController,koinViewModel() )
        }

        composable(Screen.LOGIN.route) {
            LoginScreen(navController, koinViewModel())
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
            EditProfileScreen(navController,koinViewModel(),email)
        }

        composable(
            Screen.HOME.route
        ) {
            HomeScreen()
        }
    }

}