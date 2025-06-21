package com.example.chitchatapp.feature.editProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chitchatapp.data.LocalRepo
import com.example.chitchatapp.data.remote.UserRepo
import com.example.chitchatapp.domain.models.User
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(
    private val userRepo: UserRepo,
    private val localRepo: LocalRepo
): ViewModel() {


    fun saveUser(user : User,
                 onSuccess: () -> Unit,
                 onError : (String) -> Unit
                 ){

        val exceptionHandler = CoroutineExceptionHandler{ _, error ->

            onError(error.message ?: "Unknown error")
        }
        viewModelScope.launch (exceptionHandler){
            userRepo.saveUser(user)
            //TODO : onLoggedIn should be executed when saveUser is successful and with its data, it should save the user in local
            localRepo.onLoggedIn(user)
            onSuccess()
        }
    }
}