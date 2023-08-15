package com.example.retrofitproject.ui

sealed class PostDeleteState{
    object Idle:PostDeleteState()
    object Loading:PostDeleteState()
    object Success:PostDeleteState()
    object Error:PostDeleteState()
}
