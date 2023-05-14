package com.example.finder.ui.screen.user_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repo: UserListRepository
) : ViewModel() {

    val userList =
        repo.loadUsers(
            onStart = { _isLoadingUsers.value = true },
            onCompletion = { _isLoadingUsers.value = false },
            onError ={ Log.e("userList", it) }
        )

    val me = repo.loadMe( onStart = { _isLoadingMe.value = true },
        onCompletion = { _isLoadingMe.value = false },
        onError ={ Log.e("loadingMe", it) })

    private val _isLoadingUsers: MutableState<Boolean> = mutableStateOf(false)
    private val _isLoadingMe: MutableState<Boolean> = mutableStateOf(false)

    val isLoadingUsers: State<Boolean> get() = _isLoadingUsers
    val isLoadingMe: State<Boolean> get() = _isLoadingMe


}