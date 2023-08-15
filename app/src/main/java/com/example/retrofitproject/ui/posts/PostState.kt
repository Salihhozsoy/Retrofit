package com.example.retrofitproject.ui.posts

import com.example.retrofitproject.network.model.Post

sealed class PostState{
    object Idle:PostState()
    object Empty:PostState()
    class Result(val post:List<Post>):PostState()
    object Error:PostState()


}
