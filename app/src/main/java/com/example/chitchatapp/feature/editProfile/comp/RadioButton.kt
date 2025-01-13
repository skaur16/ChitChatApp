package com.example.chitchatapp.feature.editProfile.comp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RadioButton() {
    val options = listOf("Male","Female")
    var selectedOption by remember {
        mutableStateOf(options[0])
    }

    Card (
        modifier = Modifier.fillMaxWidth()
                .padding(10.dp)
                .background(color = Color.LightGray)

    ){

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                Text(text = "Gender",
                    fontSize = 20.sp
                    )
            }
            options.forEach{option->
                Row {
                    RadioButton(
                        selected = selectedOption == option,
                        onClick = {
                            selectedOption = option
                        }
                    )
                    Text(text = option)
                }
            }
        }
    }

}