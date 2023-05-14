package com.example.finder.ui.screen.user_list


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.finder.model.UserDto
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    navController: NavController,
    userListViewModel: UserListViewModel,
) {

    val userList by userListViewModel.userList.collectAsState(initial = emptyList())
    val isLoading by userListViewModel.isLoading

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Users list") },
            )
        },
        content = {
            if (isLoading) {
                // Show a progress indicator
                Text(
                "loading",
                    modifier=Modifier.fillMaxSize()

                )
            } else {
                LazyColumn {
                    items(userList) { user ->
                        UserItem(user = user)
                    }
                }
            }
        }
    )
}

@Composable
fun UserItem(user: UserDto) {
    Column {
        Text(text = "${user.firstName} ${user.lastName}")
        Text(text = user.username)
        Text(text = "${user.age} years old")
    }
}

