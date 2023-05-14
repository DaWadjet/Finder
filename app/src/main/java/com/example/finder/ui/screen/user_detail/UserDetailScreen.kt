package com.example.finder.ui.screen.user_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.finder.model.User
import com.example.finder.ui.screen.profile_edit.ProfileEditViewModel

@Composable
fun UserDetailScreen(
    navController: NavController,
    userDetailViewModel: UserDetailViewModel,
) {

    val user = remember {
        val args = requireNotNull(navController.currentBackStackEntry)
            .arguments?.getParcelable<User>("user")
        requireNotNull(args)
    }
}