package com.example.finder.persistence



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finder.model.User


    @Database(entities = [User::class], version = 2, exportSchema = false)
    abstract class UserDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao
    }
