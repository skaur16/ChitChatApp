package com.example.chitchatapp.data

import android.util.Log
import com.example.chitchatapp.helper.DataStoreUtil

class LocalRepo(
    private val dataStoreUtil : DataStoreUtil
) {

    suspend fun onLoggedIn(){
            dataStoreUtil.setData("isLoggedIn", true)
            Log.e("Inside OnLoggedIn", "${isLoggedIn()}")
    }

    suspend fun isLoggedIn(): Boolean {
        return dataStoreUtil.getData<Boolean>("isLoggedIn") ?: false
    }

}