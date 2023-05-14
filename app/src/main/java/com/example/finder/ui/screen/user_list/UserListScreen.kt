package com.example.finder.ui.screen.user_list

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import kotlinx.coroutines.flow.collectLatest

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.finder.model.User
import com.example.finder.model.UserDto
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    navController: NavController,
    userListViewModel: UserListViewModel,
) {

    val userList by userListViewModel.userList.collectAsState(initial = emptyList())
    val isLoadingUsers by userListViewModel.isLoadingUsers
    val isLoadingMe by userListViewModel.isLoadingMe
    val isLoading = isLoadingMe || isLoadingUsers

    val me by userListViewModel.me.collectAsState(User.createDefaultUser())

    LaunchedEffect(me) {
        if (me == null) {
            navController.navigate("profile-edit")
        }
    }

    Log.d("me", me.toString())
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        "Users list",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .fillMaxWidth(),
                    )
                },
    navigationIcon = {
                    IconButton(
                        onClick = { navController.navigate("profile-edit") },
                        content = {
                            Icon(Icons.Filled.AccountCircle, contentDescription = "edit profile")
                        }
                    )
                }
            )
        },
        content = {
            Crossfade(targetState = isLoading) { isLoading ->
                if (isLoading) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn {
                        items(userList) { item ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp, horizontal = 16.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(all = 16.dp)
                                ) {
                                    val painter = rememberAsyncImagePainter(
                                        ImageRequest.Builder(LocalContext.current)
                                            .data(data = item.imageUrl)
                                            .apply {
                                                crossfade(true)
                                            }
                                            .build()
                                    )
                                    Image(
                                        painter = painter,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(70.dp)
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                    Column(
                                        modifier = Modifier.padding(start = 16.dp)
                                    ) {
                                        Text(
                                            text = "${item.firstName}, ${item.age}",
                                            style = MaterialTheme.typography.h6,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = item.city,
                                            style = MaterialTheme.typography.subtitle1
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

