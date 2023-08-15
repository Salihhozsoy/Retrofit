package com.example.retrofitproject.ui.addPost

sealed class CreatePostState{
    object Idle:CreatePostState()
    object Loading:CreatePostState()
    object Success:CreatePostState()
    object Error:CreatePostState()
}
