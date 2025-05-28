package com.example.chitchatapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.example.chitchatapp.temp.Scripts
import com.example.chitchatapp.ui.theme.ChitChatAppTheme
import com.streamliners.base.BaseActivity
import kotlinx.coroutines.launch

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
      //  runScripts()
    }

    private fun runScripts(){
        lifecycleScope.launch{
            //Scripts.saveDummyChannels()
            //Scripts.ChannelQueryTest()
           // Scripts.saveDummyUsers()
        }
    }
}

