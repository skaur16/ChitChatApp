package com.example.chitchatapp.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chitchatapp.R
import com.example.chitchatapp.ui.theme.Gradient

@Preview
@Composable
fun SplashScreen()
{
    Box(
        modifier = Modifier.fillMaxSize().background(Gradient),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier.size(150.dp),
            painter = painterResource(R.drawable.chit_chat),
            contentDescription = "chat",
            colorFilter = ColorFilter.tint(Color.White)
        )
    }

}