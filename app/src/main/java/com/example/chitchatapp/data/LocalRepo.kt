package com.example.chitchatapp.data

import android.util.Log
import com.example.chitchatapp.domain.models.User
import com.example.chitchatapp.helper.DataStoreUtil

class LocalRepo(
    private val dataStoreUtil : DataStoreUtil
) {


    suspend fun onLoggedIn(user : User){
            dataStoreUtil.setData("user",user)
        Log.e("OnLoggedIn", user.toString())
    }

    suspend fun getLoggedInUser(): User {
        return getLoggedInUserNullable() ?: error("User not found")
    }
    private suspend fun getLoggedInUserNullable(): User? {
        val a =  dataStoreUtil.getData<User>("user")
        Log.e("getLoggedInUser", a.toString())
        return a

    }

    suspend fun isLoggedIn() = getLoggedInUserNullable() != null

}