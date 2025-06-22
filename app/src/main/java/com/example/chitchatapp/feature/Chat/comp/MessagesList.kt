package com.example.chitchatapp.feature.Chat.comp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chitchatapp.domain.ext.id
import com.example.chitchatapp.domain.models.Channel
import com.example.chitchatapp.feature.Chat.ChatViewModel

@Composable
fun MessagesList(data : ChatViewModel.Data) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(data.channel.messages){message->
            val isSelfSent = message.sender == data.user.id()
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = if(isSelfSent)
                    Alignment.CenterEnd
                else
                    Alignment.CenterStart
            ){

                MessageCard(message = message)
            }
        }
    }
}