package com.example.chitchatapp.feature.Chat

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.chitchatapp.data.LocalRepo
import com.example.chitchatapp.data.remote.ChannelRepo
import com.example.chitchatapp.domain.ext.id
import com.example.chitchatapp.domain.models.Channel
import com.example.chitchatapp.domain.models.Message
import com.example.chitchatapp.domain.models.User
import com.google.firebase.Timestamp
import com.streamliners.base.BaseViewModel
import com.streamliners.base.ext.execute
import com.streamliners.base.taskState.taskStateOf
import com.streamliners.base.taskState.update
import kotlinx.coroutines.launch

class ChatViewModel(
    private val channelRepo : ChannelRepo,
    private val localRepo : LocalRepo
) : BaseViewModel() {

    val channel = mutableStateOf<Channel?>(null)
    val messageInput = mutableStateOf("")
    lateinit var user : User

    fun start(
        channelId : String
    ){
        viewModelScope.launch {
            user = localRepo.getLoggedInUser()
            launch{
                channelRepo.subscribeToChannel(channelId).collect{
                    channel.value = channelRepo.getChannel(channelId)
                }
            }

        }

     }

    fun sendMessage(
        messageStr : String,
        onSuccess : () -> Unit
                    ) {

        val message = Message(
            time = Timestamp.now(),
            message = messageStr,
            mediaUrl = null,
            sender = user.id()

        )
        viewModelScope.launch{
            channel.value?.let {
                channelRepo.sendMessages(it.id(), message)
                onSuccess()
            }
        }
    }
}