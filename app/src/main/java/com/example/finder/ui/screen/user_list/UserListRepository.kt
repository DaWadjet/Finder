
package com.example.finder.ui.screen.user_list

import androidx.annotation.WorkerThread
import com.example.finder.model.UserDto
import com.example.finder.model.mapResultsToUserDtoList
import com.example.finder.network.RandomuserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

import javax.inject.Inject

class UserListRepository @Inject constructor(
    private val randomuserService: RandomuserApi
) {
    @WorkerThread
    fun loadUsers(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow {

        val response = randomuserService.findAll()
            val users = mapResultsToUserDtoList(response)
            emit(users)


    }.onStart { onStart() }
        .onCompletion { onCompletion() }
        .catch { e ->
            onError(e.message ?: "Unknown error occurred")
        }
        .flowOn(Dispatchers.IO)
}
