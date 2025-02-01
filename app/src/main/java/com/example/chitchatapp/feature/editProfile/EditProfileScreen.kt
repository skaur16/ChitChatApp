package com.example.chitchatapp.feature.editProfile


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.chitchatapp.domain.models.Gender
import com.example.chitchatapp.domain.models.User
import com.streamliners.compose.comp.select.RadioGroup
import com.streamliners.compose.comp.textInput.TextInputLayout
import com.streamliners.compose.comp.textInput.config.InputConfig
import com.streamliners.compose.comp.textInput.config.text
import com.streamliners.compose.comp.textInput.state.TextInputState
import com.streamliners.compose.comp.textInput.state.allHaveValidInputs



import kotlinx.coroutines.launch
import org.checkerframework.checker.units.qual.Length

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel,
    email: String
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val gender = remember { mutableStateOf<Gender?>(null) }
    var genderError by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(key1 = gender.value) {
       if(gender.value != null){
           genderError = false
       }
        Toast.makeText(context, genderError.toString(), Toast.LENGTH_SHORT ).show()
   }




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

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Email") }
            )

            TextInputLayout(state = bioInput)

            RadioGroup(
                title = "Gender",
                state = gender,
                options = Gender.entries.toList(),
                labelExtractor = { it.name}
            )
            Log.e("Gender", genderError.toString())
            if(genderError){
                Text(text="Required")
            }

            val scope = rememberCoroutineScope()

            Button(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = {

                    Log.e("TAG1", "ENTRY")
                    if (TextInputState.allHaveValidInputs(
                            nameInput, bioInput
                        )
                    ) {
                        gender.value?.let {

                            val user = User(
                                name = nameInput.value.value,
                                email = email,
                                bio = bioInput.value.value,
                                gender = it

                            )
                            Log.e("TAG2", "BEFORE CALL")


                            viewModel.saveUser(user) {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Registration Successful")
                                }
                            }
                            Log.e("TAG3", "SAVE BUTTON")


                        }
                    }
                    if(gender.value == null){
                            genderError = true
                }

                }
            ) {
                Text(text = "Save")
            }

        }

    }
}