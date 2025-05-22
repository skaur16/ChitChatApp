package com.example.chitchatapp.feature.newChat

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.chitchatapp.data.remote.UserRepo
import com.example.chitchatapp.domain.models.User
import com.streamliners.base.BaseViewModel
import kotlinx.coroutines.launch

class NewChatViewModel(
    val userRepo: UserRepo
) : BaseViewModel(){

    var users = mutableStateOf<List<User>>(emptyList())

    fun start(){
        viewModelScope.launch{
            users.value = userRepo.getAllUsers()

        }
    }
}