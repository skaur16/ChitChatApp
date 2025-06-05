package com.example.chitchatapp.feature.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.chitchatapp.data.LocalRepo
import com.example.chitchatapp.data.remote.ChannelRepo
import com.example.chitchatapp.domain.ext.id
import com.example.chitchatapp.domain.models.Channel
import com.streamliners.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val channelRepo: ChannelRepo,
    private val localRepo: LocalRepo
) : BaseViewModel() {

    val channels = mutableStateOf<List<Channel>>(emptyList())

    fun start(){
        viewModelScope.launch{
            val userId = localRepo.getLoggedInUser().id()
            channels.value = channelRepo.getAllChannelsOf(userId)
        }
    }
}