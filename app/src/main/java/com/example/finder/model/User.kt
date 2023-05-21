package com.example.finder.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val username: String,
    @ColumnInfo(name = "isMe") val isMe: Boolean = false,
    @ColumnInfo(name = "firstName") val firstName: String,
    @ColumnInfo(name = "lastName") val lastName: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "pronouns") val pronouns: String,
    @ColumnInfo(name = "phoneNumber") val phoneNumber: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "isLiked") val isLiked: Boolean = false



) {
    companion object {
        fun createDefaultUser(): User {
            return User(
                isMe = true,
                firstName = "",
                lastName = "",
                age = 0,
                isLiked = false,
                city = "",
                pronouns = "he/him",
                username = "",
                phoneNumber = "",
                imageUrl = "https://cdn-icons-png.flaticon.com/512/1674/1674295.png"
            )
        }
    }
}


fun User.toDto(): UserDto {
    return UserDto(
        username = this.username,
        firstName = this.firstName,
        lastName = this.lastName,
        city = this.city,
        age = this.age,
        pronouns = this.pronouns,
        phoneNumber = this.phoneNumber,
        imageUrl = this.imageUrl,
        isLiked = this.isLiked,
    )
}




