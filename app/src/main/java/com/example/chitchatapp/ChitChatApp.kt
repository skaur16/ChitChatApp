package com.example.chitchatapp

import android.app.Application
import com.example.chitchatapp.helper.appModule
import com.example.chitchatapp.helper.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ChitChatApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@ChitChatApp)
            modules(appModule, viewModelModule)
        }
    }
}