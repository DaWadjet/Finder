package com.example.finder.ui.screen.profile_edit

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finder.model.User
import com.example.finder.model.UserDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val repo: ProfileEditRepository
) : ViewModel() {
    val me = repo.loadMe( onStart = { _isLoadingMe.value = true },
        onCompletion = { _isLoadingMe.value = false },
        onError ={ Log.e("loadingMe", it) })

    private val _isLoadingMe: MutableState<Boolean> = mutableStateOf(false)

    val isLoadingMe: State<Boolean> get() = _isLoadingMe

    private val _saveComplete = MutableStateFlow(false)
    val saveComplete: StateFlow<Boolean> = _saveComplete

    fun onSaveCompleteHandled() {
        _saveComplete.value = false
    }
    fun saveMe(user: UserDto) {
        _isLoadingMe.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repo.saveMe(user)
            }
        }
        _saveComplete.value = true
    }

}