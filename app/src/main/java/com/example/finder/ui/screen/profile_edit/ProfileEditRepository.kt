package com.example.finder.ui.screen.profile_edit

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.finder.model.User
import com.example.finder.model.UserDto
import com.example.finder.model.toUser
import com.example.finder.persistence.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ProfileEditRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun saveMe(user: UserDto) {
            userDao.create(user.toUser(isMe = true))
    }

    @WorkerThread
    fun loadMe(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        val me =  userDao.getMe()
        emit(me)
    }.onStart { onStart() }
        .onCompletion { onCompletion() }
        .catch { e ->
            onError(e.message ?: "Unknown error occurred")
        }
        .flowOn(Dispatchers.IO)
}