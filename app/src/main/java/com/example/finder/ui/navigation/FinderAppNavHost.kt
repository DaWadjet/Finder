package com.example.finder.ui.navigation
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase


@Composable
fun FinderAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "user-list"
) {

    val analytics = remember { Firebase.analytics}
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

            navController.addOnDestinationChangedListener { _, destination, _ ->
                var params = Bundle()
                params.putString(FirebaseAnalytics.Param.SCREEN_NAME, destination.label as String?)
                params.putString(FirebaseAnalytics.Param.SCREEN_CLASS, destination.label as String?)
                analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, params)
        }
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
