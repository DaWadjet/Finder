package com.example.finder.ui.screen.user_detail

import androidx.annotation.WorkerThread
import com.example.finder.model.UserDto
import com.example.finder.model.toUser
import com.example.finder.persistence.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class UserDetailRepository @Inject constructor(
    private val userDao: UserDao
) {
        suspend fun updateLike(isLiked: Boolean, user: UserDto) {
            userDao.create(user.toUser(isLiked = isLiked))
        }

    @WorkerThread
    fun loadUser(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit,
        username: String
    ) = flow {
        val user =  userDao.getUser(username)
        emit(user)
    }.onStart { onStart() }
        .onCompletion { onCompletion() }
        .catch { e ->
            onError(e.message ?: "Unknown error occurred")
        }
        .flowOn(Dispatchers.IO)
}