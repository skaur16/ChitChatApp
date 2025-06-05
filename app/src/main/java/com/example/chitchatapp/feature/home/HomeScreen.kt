package com.example.chitchatapp.feature.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.chitchatapp.Screen
import com.example.chitchatapp.domain.ext.id
import com.example.chitchatapp.feature.Chat.ChatScreen
import com.example.chitchatapp.feature.home.comp.ChannelCard
import com.example.chitchatapp.ui.comp.UserCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController,
               viewModel: HomeViewModel
               ) {

    LaunchedEffect(key1 = Unit) {
        viewModel.start()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Home")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate(Screen.NewChat.route)} ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add" )
            }
        }
    )
    {
        LazyColumn (
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            if(viewModel.channels.value.isEmpty()){
                item {
                    Box (
                        modifier = Modifier.fillMaxSize(),
                        Alignment.Center
                    ){
                        Text(text = "No Chats Found!")
                    }
                }
            }
            else{
                items(viewModel.channels.value){channel->
                    ChannelCard(channel,
                        onClick = {
                            navController.navigate(
                                Screen.Chat(channel.id()).route
                            )
                        } )
                }
            }
        }





    }

}