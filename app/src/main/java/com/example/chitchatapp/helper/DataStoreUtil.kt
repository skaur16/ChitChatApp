package com.example.chitchatapp.helper

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import java.io.File

class DataStoreUtil(
    val dataStore : DataStore<Preferences>
) {

    companion object{
        fun create(context : Context) : DataStoreUtil {
            val dataStore = PreferenceDataStoreFactory.create {
                File(context.filesDir, "datastore/chitChatApp.preferences_pb")
            }
            return DataStoreUtil(
                dataStore
            )
        }
    }


    internal suspend inline fun <reified T> getData(key: String) : T?{
        val str = getSerializedData(key)
            ?: return null
        return Gson().fromJson(str, T::class.java)
    }

    internal suspend inline fun <reified T> setData(key : String, value : T){
        setSerializedData(key, Gson().toJson(value))
    }


    internal suspend fun getSerializedData(key: String): String? {
        return dataStore.data.first()[stringPreferencesKey(key)]
    }

    internal suspend fun setSerializedData(key: String, value : String) {
        dataStore.edit {
            it[stringPreferencesKey(key)] = value
        }

    }





}


