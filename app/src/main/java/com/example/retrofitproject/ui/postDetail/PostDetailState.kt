package com.example.retrofitproject.ui.postDetail

import com.example.retrofitproject.network.model.Post

sealed class PostDetailState{
    object  Idle:PostDetailState()
    object PostNotFound:PostDetailState()
    class Success(val post: Post):PostDetailState()
    object Error:PostDetailState()
}
