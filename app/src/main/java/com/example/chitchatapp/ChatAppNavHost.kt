package com.example.chitchatapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chitchatapp.feature.editProfile.EditProfileScreen
import com.example.chitchatapp.feature.home.HomeScreen
import com.example.chitchatapp.feature.login.LoginScreen
import com.example.chitchatapp.feature.newChat.NewChatScreen
import com.example.chitchatapp.feature.splash.SplashScreen
import com.streamliners.pickers.date.showDatePickerDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainActivity.ChatAppNavHost() {

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
            EditProfileScreen(
                navController,
                koinViewModel(),
                email,
            showDatePicker = ::showDatePickerDialog
            )
        }

        composable(
            Screen.HOME.route
        ) {
            HomeScreen(navController)
        }

        composable(
            Screen.NewChat.route
        ){
            NewChatScreen(koinViewModel(), navController)
        }
    }

}