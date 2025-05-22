package com.example.chitchatapp.feature.newChat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun NewChatScreen(
    viewModel: NewChatViewModel,
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.start()
    }

}