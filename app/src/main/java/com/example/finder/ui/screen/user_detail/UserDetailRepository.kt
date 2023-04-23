package com.example.finder.ui.screen.user_detail

import com.example.finder.persistence.UserDao
import javax.inject.Inject

class UserDetailRepository @Inject constructor(
    private val userDao: UserDao
) {

}