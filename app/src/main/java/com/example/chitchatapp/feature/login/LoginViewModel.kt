package com.example.chitchatapp.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.chitchatapp.Screen
import com.example.chitchatapp.data.LocalRepo
import com.example.chitchatapp.data.remote.UserRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val userRepo: UserRepo,
    private val localRepo : LocalRepo
): ViewModel() {

    fun onLoggedIn(
        email : String,
        navController: NavController
    ){
        viewModelScope.launch {
            val user = userRepo.getUserWithEmail(email)
            if(user != null){
                //save data in local and navigate
                localRepo.onLoggedIn()
                navController.navigate(Screen.HOME.route)
            }
            else{
                //navigate to editProfile Screen
                navController.navigate(Screen.EDITPROFILE(email).route)
            }
        }
    }
}