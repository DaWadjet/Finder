package com.example.finder.persistence

import com.example.finder.model.User

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * from users WHERE isMe = 0")
    fun getAllUsers(): List<User>
    @Query("SELECT * from users WHERE isMe = 1")
    fun getMe(): User?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(user: User)
    @Query("SELECT * from users WHERE username = :username")
    suspend fun getUser(username: String): User?
    @Update
    suspend fun update(user: User)
    @Delete
    suspend fun delete(user: User)

}