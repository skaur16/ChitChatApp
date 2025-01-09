package com.example.chitchatapp.feature.editProfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    email : String
) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text="Profile")},
                navigationIcon = {}
                )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.fillMaxSize()

            )
        }

    ) {paddingValues->

        var name by remember{ mutableStateOf("") }
        var bio by remember{ mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxSize(),
                value = name,
                onValueChange = {name = it},
                label = { Text(text = "Name")}
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxSize(),
                value = email,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Email")}
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxSize(),
                value = bio,
                onValueChange = {bio = it},
                label = { Text(text = "Bio")}
            )

            val scope = rememberCoroutineScope()

            Button(
                modifier = Modifier.padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    if(name.isNotBlank() && bio.isNotBlank()){

                        scope.launch {
                         snackbarHostState.showSnackbar("Your name is $name and bio is $bio")
                        }

                    }
                    else{
                        scope.launch{
                            snackbarHostState.showSnackbar("Please input your Name and Bio!")
                        }
                    }
                }
            ) {
                Text(text = "Save")
            }
        }
    }

}