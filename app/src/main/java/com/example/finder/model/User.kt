package com.example.finder.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val username: String,
    @ColumnInfo(name = "firstName") val firstName: String,
    @ColumnInfo(name = "lastName") val lastName: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "pronouns") val pronouns: String,
    @ColumnInfo(name = "phoneNumber") val phoneNumber: String
    )
