package com.example.finder.di

import com.example.finder.network.RandomuserApi
import com.example.finder.persistence.UserDao
import com.example.finder.ui.screen.profile_edit.ProfileEditRepository
import com.example.finder.ui.screen.user_detail.UserDetailRepository
import com.example.finder.ui.screen.user_list.UserListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideProfileEditRepository(
       userDao: UserDao
    ): ProfileEditRepository {
        return ProfileEditRepository(userDao)
    }

    @Provides
    @ViewModelScoped
    fun provideUserListRepository(
        randomuserService: RandomuserApi
    ): UserListRepository {
        return UserListRepository(randomuserService)
    }

    @Provides
    @ViewModelScoped
    fun provideUserDetailRepository(
        userDao: UserDao
    ): UserDetailRepository {
        return UserDetailRepository(userDao)
    }
}
