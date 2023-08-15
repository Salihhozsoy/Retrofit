package com.example.retrofitproject.network.service

import com.example.retrofitproject.network.model.Post
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {
    @GET("posts")
    suspend fun getPosts():List<Post>

    @GET("posts/{id}")
    suspend fun getPostsById(@Path("id") id:Int):Post?

    @DELETE("posts/{id}")
    suspend fun delete(@Path("id")id:Int)

    @POST("posts")
    suspend fun create(@Body post: Post):Post

    @PUT("posts/{id}")
    suspend fun update(@Body post:Post, @Path("id") id: Int):Post
}