package com.example.finder.persistence

import com.example.finder.model.User

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * from users WHERE isMe = 0")
    fun getAllUsers(): Flow<List<User>>
    @Query("SELECT * from users WHERE isMe = 1")
    fun getMe(): Flow<User?>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(character: User)
    @Update
    suspend fun update(character: User)
    @Delete
    suspend fun delete(character: User)

}