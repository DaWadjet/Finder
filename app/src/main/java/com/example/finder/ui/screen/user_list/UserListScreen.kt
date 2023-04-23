package com.example.finder.ui.screen.user_list


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import java.util.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    navController: NavController,
    userListViewModel: UserListViewModel,
) {

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Users list") },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigateUp() },
                        content = {
                            Icon(Icons.Filled.AccountCircle, contentDescription = "edit profile")
                        }
                    )
                }
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(

                            data = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8dXNlcnxlbnwwfHwwfHw%3D&w=1000&q=80",

                            )
                        .crossfade(true)
                        .build(),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                            modifier = Modifier
                            .clickable(onClick = { navController.navigate("profile-edit") })
                        .padding(16.dp).
                            size(100.dp).clip(CircleShape)
                )
                when (userListViewModel.state) {
                    is UserListState.Loading -> Text(
                        text = "Loading",
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp)
                    )
                    is UserListState.Success -> Text(
                        text = (userListViewModel.state as UserListState.Success).text,
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp)
                    )

                    is UserListState.Error -> Text(
                            text = "error",
                            color = Color.Black,
                            modifier = Modifier.padding(16.dp)
                        )
                }


            }
        }
    )
}

