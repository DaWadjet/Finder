package com.example.finder.ui.screen.user_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.finder.ui.screen.user_detail.UserDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * UI state for the weather screen
 */
sealed interface UserListState {
    data class Success(val text: String) : UserListState
    object Error : UserListState
    object Loading : UserListState
}


@HiltViewModel
class UserListViewModel @Inject constructor(
    repo: UserListRepository
) : ViewModel() {
    var state: UserListState by mutableStateOf(UserListState.Success(text="bonjour"))
        private set
}