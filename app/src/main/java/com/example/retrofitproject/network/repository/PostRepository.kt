package com.example.retrofitproject.network.repository

import com.example.retrofitproject.network.model.Post

interface PostRepository {

    suspend fun getPosts(): List<Post>

    suspend fun getPostById(id: Int): Post?

    suspend fun delete(id: Int): Boolean

    suspend fun update(post: Post,id: Int): Boolean
}