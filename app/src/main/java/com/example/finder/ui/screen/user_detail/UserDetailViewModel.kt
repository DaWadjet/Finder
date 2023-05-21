package com.example.finder.ui.screen.user_detail

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finder.model.User
import com.example.finder.model.UserDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val repo: UserDetailRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {


    val userFromParam: UserDto? = savedStateHandle.get<String>("user")?.let {
        Json.decodeFromString(UserDto.serializer(), it)
    }

    private val _isLoadingUser: MutableState<Boolean> = mutableStateOf(false)
    val isLoadingUser: State<Boolean> get() = _isLoadingUser

    private val _toggleLikeFlow = MutableStateFlow(0)

    val user: StateFlow<User?> = _toggleLikeFlow.flatMapLatest {
        repo.loadUser(
            onStart = { _isLoadingUser.value = true
            },
            onCompletion = { _isLoadingUser.value = false
            },
            onError = { Log.e("userDetails", it) },
            username = userFromParam!!.username
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(0), null)

    fun  toggleLike(likedUser: UserDto) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repo.updateLike(!likedUser.isLiked, likedUser)
            }
            _toggleLikeFlow.value++

        }
    }
}
