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
import com.example.chitchatapp.feature.editProfile.comp.RadioButton
import com.streamliners.compose.comp.textInput.TextInputLayout
import com.streamliners.compose.comp.textInput.config.InputConfig
import com.streamliners.compose.comp.textInput.config.text
import com.streamliners.compose.comp.textInput.state.TextInputState
import com.streamliners.compose.comp.textInput.state.allHaveValidInputs


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

//        var name by remember { mutableStateOf("") }
//        var nameError by remember { mutableStateOf(false) }
//        var bio by remember { mutableStateOf("") }
//        var bioError by remember { mutableStateOf(false) }
        val gender = remember { mutableStateOf<String?>(null) }

        val nameInput = remember {
            mutableStateOf(
                TextInputState(
                    label = "Name",
                    inputConfig = InputConfig.text {
                        minLength = 5
                        maxLength = 30
                    }
                )
            )
        }

        val bioInput = remember {
            mutableStateOf(
                TextInputState(
                    label = "Bio",
                    inputConfig = InputConfig.text {
                        minLength = 10
                        maxLength = 50
                    }
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            TextInputLayout(state = nameInput)
            TextInputLayout(state = bioInput)


            //radiogroup not working from droidlibs

            RadioButton()


            val scope = rememberCoroutineScope()

            Button(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    if (TextInputState.allHaveValidInputs(
                            nameInput, bioInput
                        )
                    ) {

                        scope.launch {
                            snackbarHostState.showSnackbar("Your name is ${nameInput.value.value} " +
                                    "and bio is ${bioInput.value.value}")
                        }

                    }
                }
            ) {
                Text(text = "Save")
            }

        }

    }
}