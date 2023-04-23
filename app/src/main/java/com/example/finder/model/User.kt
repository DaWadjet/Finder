package com.example.finder.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "users")
data class User(
    val username: String,
    @ColumnInfo(name = "cityName") val firstName: String,
    @ColumnInfo(name = "cityName") val lastName: String,
    @ColumnInfo(name = "cityName") val city: String,
    @ColumnInfo(name = "cityName") val age: Int,
    @ColumnInfo(name = "cityName") val pronouns: String,
    @ColumnInfo(name = "cityName") val phoneNumber: String
    )
