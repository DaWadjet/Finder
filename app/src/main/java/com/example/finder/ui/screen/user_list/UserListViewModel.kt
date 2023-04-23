package com.example.finder.ui.screen.user_list

import androidx.lifecycle.ViewModel
import com.example.finder.ui.screen.user_detail.UserDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    repo: UserListRepository
) : ViewModel() {

}