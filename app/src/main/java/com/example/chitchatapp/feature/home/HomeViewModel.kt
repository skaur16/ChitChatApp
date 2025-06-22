package com.example.chitchatapp.feature.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.chitchatapp.data.LocalRepo
import com.example.chitchatapp.data.remote.ChannelRepo
import com.example.chitchatapp.data.remote.UserRepo
import com.example.chitchatapp.domain.ext.id
import com.example.chitchatapp.domain.models.Channel
import com.streamliners.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val channelRepo: ChannelRepo,
    private val localRepo: LocalRepo,
    private val userRepo: UserRepo
) : BaseViewModel() {

    val channelState = mutableStateOf<List<Channel>>(emptyList())

    fun start(){
        viewModelScope.launch{

            val userId = localRepo.getLoggedInUser().id()
            val users = userRepo.getAllUsers()

            val channels = channelRepo.getAllChannelsOf(userId)
                .map{channel->
                    if(channel.type == Channel.ChannelType.OneToOne){
                        val otherUserId = channel.members.find{
                            it != userId
                        } ?:  error("otherUserId not found")

                        val otherUser = users.find{
                            it.id() == otherUserId
                        } ?: error("user with $otherUserId not found")

                        channel.copy(
                            name = otherUser.name,
                            imageUrl = "",
                            type = Channel.ChannelType.OneToOne,
                            description = otherUser.bio,
                            members = listOf(otherUserId, userId),
                            messages = emptyList()
                        )
                    }
                    else{
                        channel
                    }

                }
            channelState.value = channels


        }
    }
}