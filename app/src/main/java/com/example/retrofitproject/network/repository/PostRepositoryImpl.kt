package com.example.retrofitproject.network.repository

import com.example.retrofitproject.network.RetrofitHelper
import com.example.retrofitproject.network.model.Post
import com.example.retrofitproject.network.service.PostService

class PostRepositoryImpl :PostRepository {

    private val service = RetrofitHelper.getRetrofit().create(PostService::class.java)

    override suspend fun getPosts(): List<Post> {

       return  service.getPosts()
    }

    override suspend fun getPostById(id: Int): Post? {
       return service.getPostsById(id)
    }

    override suspend fun delete(id: Int): Boolean {
        return kotlin.runCatching {
            service.delete(id)
        }.isSuccess
    }

    override suspend fun update(post: Post, id: Int): Boolean {
        return kotlin.runCatching {
            service.update(post,post.id)
        }.isSuccess
    }

}