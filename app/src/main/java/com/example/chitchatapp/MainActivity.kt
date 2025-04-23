package com.example.chitchatapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.chitchatapp.ui.theme.ChitChatAppTheme
import com.streamliners.base.BaseActivity

class MainActivity : BaseActivity() {

    override var buildType : String = BuildConfig.BUILD_TYPE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChitChatAppTheme {
                ChatAppNavHost()

            }
        }
    }
}

