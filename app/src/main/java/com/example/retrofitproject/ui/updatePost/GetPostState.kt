package com.example.retrofitproject.ui.updatePost

import com.example.retrofitproject.network.model.Post

sealed class GetPostState{
    object Idle:GetPostState()
    object Loading:GetPostState()
    class Success(val post: Post):GetPostState()
    object Error:GetPostState()
}
