package com.example.chitchatapp.feature.editProfile


import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.chitchatapp.Screen
import com.example.chitchatapp.domain.models.Gender
import com.example.chitchatapp.domain.models.User
import com.example.chitchatapp.feature.editProfile.comp.AddImageButton
import com.example.chitchatapp.feature.editProfile.comp.ProfileImage
import com.example.chitchatapp.ui.theme.Secondary
import com.streamliners.compose.comp.select.RadioGroup
import com.streamliners.compose.comp.textInput.TextInputLayout
import com.streamliners.compose.comp.textInput.config.InputConfig
import com.streamliners.compose.comp.textInput.config.text
import com.streamliners.compose.comp.textInput.dialog.TextInputDialogState
import com.streamliners.compose.comp.textInput.state.TextInputState
import com.streamliners.compose.comp.textInput.state.allHaveValidInputs
import com.streamliners.pickers.date.DatePickerDialog
import com.streamliners.pickers.date.ShowDatePicker
import com.streamliners.pickers.media.MediaPickerDialog
import com.streamliners.pickers.media.MediaPickerDialogState
import com.streamliners.pickers.media.MediaType
import com.streamliners.pickers.media.PickedMedia
import com.streamliners.pickers.media.rememberMediaPickerDialogState
import com.streamliners.utils.DateTimeUtils


import kotlinx.coroutines.launch
import org.checkerframework.checker.units.qual.Length

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavController,
    viewModel: EditProfileViewModel,
    email: String,
    showDatePicker: ShowDatePicker
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val mediaPickerDialogState = rememberMediaPickerDialogState()


    val gender = remember { mutableStateOf<Gender?>(null) }
    var genderError by remember { mutableStateOf(false) }
    var dob by remember { mutableStateOf<String?>(null) }
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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Secondary,
                    titleContentColor = Color.White
                )
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
        val image = remember{
            mutableStateOf<PickedMedia?>(null)
        }

        var imageUri = remember{ mutableStateOf<Uri?>(null) }
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            uri->
            imageUri.value = uri
        }



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

           /* image.value?.let{
                ProfileImage(it)
            }?: run{
                AddImageButton{

            }*/
            if (imageUri.value != null) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(context)
                            .data(imageUri.value)
                            .build()
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape)
                        .clickable { launcher.launch("image/*") }
                        .align(
                            alignment = Alignment.CenterHorizontally
                        )
                )
            } else {
                AddImageButton(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    onClick = {
                        launcher.launch("image/*")
                    }
                )
            }

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

            //DOB datepicker..
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .clickable {
                        showDatePicker(
                            DatePickerDialog.Params(
                                format = DateTimeUtils.Format.DATE_MONTH_YEAR_2,
                                prefill = dob,
                                onPicked = {date->
                                    dob = date
                                }
                            )
                        )
                    },
                value = dob?: "",
                onValueChange = {},
                enabled = false,
                label = { Text(text = "DOB") }
            )

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
                                gender = it,
                                dob = dob
                            )
                            Log.e("TAG2", "BEFORE CALL")


                            viewModel.saveUser(user) {
                                navController.navigate(Screen.HOME.route)
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
    MediaPickerDialog(state = mediaPickerDialogState,
        authority = "com.example.chitchat app.FileProvider")
}