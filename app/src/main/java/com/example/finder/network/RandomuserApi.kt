package com.example.finder.network

import com.example.finder.model.RandomuserResults
import com.example.finder.model.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RandomuserApi {
    @POST("/")
    suspend fun create(@Body user: UserDto): Response<Unit>

    @GET("/")
    suspend fun findAll(@Query("seed") seed: String? = "foobar",@Query("results") limit: Int? = 100): Response<RandomuserResults>

    @PUT("/")
    suspend fun update(@Body character: UserDto): Response<Unit>

    @DELETE("/{username}")
    suspend fun delete(@Path("username") id: Int): Response<Unit>

}