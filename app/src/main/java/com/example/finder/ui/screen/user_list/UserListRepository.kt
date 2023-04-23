package com.example.finder.ui.screen.user_list

import com.example.finder.network.RandomuserService
import com.example.finder.persistence.UserDao
import javax.inject.Inject

class UserListRepository @Inject constructor(
    private val randomuserService: RandomuserService
) {

}