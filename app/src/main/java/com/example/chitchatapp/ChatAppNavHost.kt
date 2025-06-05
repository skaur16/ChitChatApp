package com.example.chitchatapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chitchatapp.feature.Chat.ChatScreen
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
            HomeScreen(navController, koinViewModel())
        }

        composable(
            Screen.NewChat.route
        ){
            NewChatScreen(koinViewModel(), navController)
        }

        composable(
            Screen.Chat.format(),
            arguments =  listOf(
                navArgument("channelId"){
                    type = NavType.StringType
                }
            )
        ) {

            val channelId = it.arguments?.getString("channelId")?: error("ChannelId not passed !")
            ChatScreen(
                channelId = channelId,
                navController = navController
            )

        }

    }

}