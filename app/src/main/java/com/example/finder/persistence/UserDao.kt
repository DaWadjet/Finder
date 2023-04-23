package com.example.finder.persistence

import com.example.finder.model.User

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * from users")
    fun getAllCities(): Flow<List<User>>

}