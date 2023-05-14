package com.example.finder.ui.screen.profile_edit

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.finder.model.User
import com.example.finder.model.toDto
import java.lang.Exception

@Composable
fun ProfileEditScreen(
    navController: NavController,
    profileEditViewModel: ProfileEditViewModel,
) {
    val me by profileEditViewModel.me.collectAsState(User.createDefaultUser())
    val isLoadingMe by profileEditViewModel.isLoadingMe

    val user = remember { mutableStateOf(User.createDefaultUser().toDto()) }

    LaunchedEffect(me) {
        if (me != null) {
           user.value = me!!.toDto()
        }
    }

    LaunchedEffect(profileEditViewModel) {
        profileEditViewModel.saveComplete.collect { saveComplete ->
            if (saveComplete) {
                navController.navigateUp()
                profileEditViewModel.onSaveCompleteHandled()
            }
        }
    }
    Crossfade(targetState = isLoadingMe) { isLoading ->
        if (isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        } else {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = rememberImagePainter(
                data = user.value.imageUrl,
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colors.secondary,
                    shape = CircleShape
                )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                TextFieldWithLabel(
                    label = "First Name",
                    value = user.value.firstName,
                    onValueChange = { user.value = user.value.copy(firstName = it) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldWithLabel(
                    label = "Age",
                    value = user.value.age.toString(),
                    onValueChange = {
                        try {
                            if (it.isNotEmpty()) {
                                user.value = user.value.copy(age = it.toInt())
                            } else {
                                user.value = user.value.copy(age = 0)
                            }
                        } catch (e: Exception) {

                        }

                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                TextFieldWithLabel(
                    label = "Last Name",
                    value = user.value.lastName,
                    onValueChange = { user.value = user.value.copy(lastName = it) },
                    modifier = Modifier.fillMaxWidth()
                )


                Spacer(modifier = Modifier.height(16.dp))

                TextFieldWithLabel(
                    label = "Pronouns",
                    value = user.value.pronouns,
                    onValueChange = { user.value = user.value.copy(pronouns = it) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextFieldWithLabel(
            label = "City",
            value = user.value.city,
            onValueChange = { user.value = user.value.copy(city = it) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextFieldWithLabel(
            label = "Phone Number",
            value = user.value.phoneNumber,
            onValueChange = { user.value = user.value.copy(phoneNumber = it) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                    profileEditViewModel.saveMe(user.value)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Save")
        }
    }
}}}

@Composable
fun TextFieldWithLabel(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp)),
            textStyle = MaterialTheme.typography.body1,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            singleLine = true
        )
    }
}


