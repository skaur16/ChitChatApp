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
import com.example.chitchatapp.domain.models.Gender
import com.example.chitchatapp.feature.editProfile.comp.RadioButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    email: String
) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile") },
                navigationIcon = {}
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.fillMaxSize()

            )
        }

    ) { paddingValues ->

        var name by remember { mutableStateOf("") }
        var nameError by remember { mutableStateOf(false) }
        var bio by remember { mutableStateOf("") }
        var bioError by remember { mutableStateOf(false) }
        val gender = remember { mutableStateOf<String?>(null) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxSize(),
                value = name,
                onValueChange = { name = it
                                nameError = it.isBlank()
                                },
                label = { Text(text = "Name") },
                isError = nameError,
                supportingText = if(nameError) {
                    { Text(text = "Required!") }
                } else null
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxSize(),
                value = email,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Email") }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxSize(),
                value = bio,
                onValueChange = { bio = it
                    bioError = it.isBlank()},
                label = { Text(text = "Bio") },
                isError = bioError,
                supportingText = if(bioError) {
                    { Text(text = "Required!") }
                } else null
            )

          //radiogroup not working from droidlibs

            RadioButton()


            val scope = rememberCoroutineScope()

            Button(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    if(name.isBlank() && bio.isBlank()){
                        nameError = true
                        bioError = true
                        scope.launch {
                            snackbarHostState.showSnackbar("Please input your Name and Bio")
                        }
                        return@Button
                    }
                    if (name.isBlank()) {
                        nameError = true
                        scope.launch {
                            snackbarHostState.showSnackbar("Please input your Name")
                        }
                        return@Button
                    }

                    if (bio.isBlank()) {
                        bioError = true
                        scope.launch {
                            snackbarHostState.showSnackbar("Please input your Bio")
                        }
                        return@Button
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("Your name is $name and bio is $bio")
                        }
                    }

                }
            ) {
                    Text(text = "Save")
                }
                }
        }

    }