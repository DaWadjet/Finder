package com.example.finder.ui.navigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finder.model.UserDto
import com.example.finder.ui.screen.profile_edit.ProfileEditScreen
import com.example.finder.ui.screen.user_detail.UserDetailRepository
import com.example.finder.ui.screen.user_detail.UserDetailScreen
import com.example.finder.ui.screen.user_detail.UserDetailViewModel
import com.example.finder.ui.screen.user_list.UserListScreen


@Composable
fun FinderAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "user-list"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("user-list") {UserListScreen(navController = navController, userListViewModel = hiltViewModel())}
        composable("profile-edit") {ProfileEditScreen(navController = navController, profileEditViewModel = hiltViewModel())}
        composable("user/{user}",
            arguments = listOf(
                navArgument("user"){type = NavType.StringType}
            )
        ) {
            UserDetailScreen(navController = navController, hiltViewModel())
        }
    }
}
