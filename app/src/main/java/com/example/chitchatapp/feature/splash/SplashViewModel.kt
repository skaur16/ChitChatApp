package com.example.chitchatapp.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.chitchatapp.Screen
import com.example.chitchatapp.data.LocalRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val localRepo: LocalRepo
) : ViewModel(){

        fun checkLoginStatus(
            navController: NavController
        ){
            viewModelScope.launch {
                if(localRepo.isLoggedIn()){
                    navController.navigate(Screen.HOME.route)
                }
                else{
                    navController.navigate(Screen.LOGIN.route)
                }
            }
        }

}