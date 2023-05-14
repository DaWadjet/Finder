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
            onStart = { _isLoading.value = true },
            onCompletion = { _isLoading.value = false },
            onError ={ Log.e("userList", it) }
        )

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading


}