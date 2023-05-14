package com.example.finder.ui.navigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finder.ui.screen.profile_edit.ProfileEditScreen
import com.example.finder.ui.screen.user_detail.UserDetailScreen
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
        composable("user/{username}",
            arguments = listOf(
                navArgument("username"){type = NavType.StringType}
            )
        ) {
            val username = it.arguments?.getString("username")
            username?.let {
                UserDetailScreen()
            }
        }
    }
}