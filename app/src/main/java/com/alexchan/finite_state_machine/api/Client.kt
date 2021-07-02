package com.alexchan.finite_state_machine.api

import com.alexchan.finite_state_machine.model.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Client {
    @GET("photos/{id}")
    suspend fun fetchPhoto(
        @Path("id") id: String
    ): Response<Photo>

    @GET("collections/{collectionId}/photos")
    suspend fun fetchPhotos(
        @Path("collectionId") collectionId: Int,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<Photo>>

    @GET("photos")
    suspend fun fetchPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<List<Photo>>
}
