package com.example.chitchatapp.data

import android.util.Log
import com.example.chitchatapp.domain.models.User
import com.example.chitchatapp.helper.DataStoreUtil

class LocalRepo(
    private val dataStoreUtil : DataStoreUtil
) {

    suspend fun onLoggedIn(user : User){
            dataStoreUtil.setData("user",user)
    }

    suspend fun getLoggedInUser(): User {
        return getLoggedInUserNullable() ?: error("User not found")
    }
    suspend fun getLoggedInUserNullable(): User? {
        return dataStoreUtil.getData<User>("user")
    }

    suspend fun isLoggedIn() = getLoggedInUserNullable() != null

}