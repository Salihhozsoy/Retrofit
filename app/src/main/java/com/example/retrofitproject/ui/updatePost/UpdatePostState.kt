package com.example.retrofitproject.ui.updatePost

import com.example.retrofitproject.network.model.Post

sealed class UpdatePostState{
    object Idle:UpdatePostState()
    object Loading:UpdatePostState()
    class Success(val post: Post):UpdatePostState()
    object Error:UpdatePostState()
}