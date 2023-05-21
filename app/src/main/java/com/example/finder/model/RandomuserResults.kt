package com.example.finder.model

import kotlinx.serialization.Serializable

@Serializable
data class RandomuserResults (
    val results: List<Result>,
    val info: Info
)


@Serializable
data class Info (
    val seed: String,
    val results: Long,
    val page: Long,
    val version: String
)


@Serializable
data class Result (
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val dob: Dob,
    val registered: Dob,
    val phone: String,
    val cell: String,
    val id: ID,
    val picture: Picture,
    val nat: String
)


@Serializable data class Dob (
    val date: String,
    val age: Long
)


@Serializable
data class ID (
    val name: String,
)


@Serializable
data class Location (
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val coordinates: Coordinates,
    val timezone: Timezone
)


@Serializable
data class Coordinates (
    val latitude: String,
    val longitude: String
)


@Serializable
data class Street (
    val number: Long,
    val name: String
)


@Serializable
data class Timezone (
    val offset: String,
    val description: String
)


@Serializable
data class Login (
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)


@Serializable
data class Name (
    val title: String,
    val first: String,
    val last: String
)


@Serializable
data class Picture (
    val large: String,
    val medium: String,
    val thumbnail: String
)

fun mapResultsToUserDtoList(results: RandomuserResults): List<UserDto> {
    return results.results.map { result ->
        UserDto(
            username = result.login.username,
            firstName = result.name.first,
            lastName = result.name.last,
            city = result.location.city,
            age = result.dob.age.toInt(),
            pronouns = getPronouns(result.gender),
            phoneNumber = result.phone,
             imageUrl = result.picture.large,
            isLiked = false,
        )
    }
}

private fun getPronouns(gender: String): String {
    return when (gender.lowercase()) {
        "female" -> "she/her"
        "male" -> "he/him"
        "non-binary" -> "they/them"
        else -> "they/them"
    }
}


