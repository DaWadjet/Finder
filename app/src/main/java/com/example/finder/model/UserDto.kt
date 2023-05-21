package com.example.finder.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
 val username: String,
 val firstName: String,
 val lastName: String,
 val city: String,
 val age: Int,
 val pronouns: String,
 val phoneNumber: String,
 val imageUrl: String,
 val isLiked: Boolean
)


fun UserDto.toUser(isMe: Boolean = false, isLiked: Boolean = false): User {
 return User(
  isMe = isMe,
  isLiked = isLiked,
  username = this.username,
  firstName = this.firstName,
  lastName = this.lastName,
  city = this.city,
  age = this.age,
  pronouns = this.pronouns,
  phoneNumber = this.phoneNumber,
  imageUrl = this.imageUrl
 )
}