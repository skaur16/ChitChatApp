package com.example.chitchatapp.feature.editProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chitchatapp.data.UserRepo
import com.example.chitchatapp.domain.models.User
import kotlinx.coroutines.launch

class EditProfileViewModel : ViewModel() {


    fun saveUser(user : User, onSuccess: () -> Unit){
        viewModelScope.launch {
            UserRepo().saveUser(user)
            onSuccess()
        }
    }
}