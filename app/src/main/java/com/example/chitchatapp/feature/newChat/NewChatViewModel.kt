package com.example.chitchatapp.feature.newChat

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.chitchatapp.data.LocalRepo
import com.example.chitchatapp.data.remote.UserRepo
import com.example.chitchatapp.domain.models.User
import com.streamliners.base.BaseViewModel
import kotlinx.coroutines.launch

class NewChatViewModel(
    private val userRepo: UserRepo,
    private val localRepo : LocalRepo
) : BaseViewModel(){

    var users = mutableStateOf<List<User>>(emptyList())

    fun start(){
        viewModelScope.launch{
            val currentUser = localRepo.getLoggedInUser()
            users.value = userRepo.getAllUsers()
                .filter {
                    it.id != currentUser.id
                }


        }
    }
}