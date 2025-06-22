package com.example.chitchatapp.feature.Chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.chitchatapp.domain.models.Message
import com.example.chitchatapp.feature.Chat.comp.MessagesList
import com.streamliners.base.taskState.taskStateOf
import com.streamliners.compose.comp.textInput.TextInputLayout
import com.streamliners.compose.comp.textInput.state.TextInputState
import com.streamliners.compose.comp.textInput.state.ifValidInput
import com.streamliners.compose.comp.textInput.state.update

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    channelId: String,
    navController: NavHostController,
    viewModel: ChatViewModel
) {

    LaunchedEffect(key1 = Unit){
        viewModel.start(channelId)
    }

    val messageInput = remember {
        mutableStateOf(
                TextInputState("Message")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Chat")}
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it)
        ) {
            Column (
                modifier = Modifier.weight(1f)
            ){

                if(viewModel.channel.value != null){
                    MessagesList(viewModel.channel.value!!)
                }

            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                value = viewModel.messageInput.value,
                onValueChange = {
                    viewModel.messageInput.value = it
                },
                placeholder = {Text(text = "Message")},
                trailingIcon = {
                    IconButton(onClick = {
                       viewModel.sendMessage(viewModel.messageInput.value){

                           viewModel.messageInput.value = ""
                       }
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "SendMsg" )
                    }
                }
            )

          /*  TextInputLayout(
                state = messageInput,
                trailingIconButton = {
                    IconButton(onClick = {
                        messageInput.ifValidInput{message->
                            viewModel.sendMessage(message)
                        }
                    }){
                        Icon(imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "SendMsg"
                            )
                    }
                }
            )*/

        }
    }
}