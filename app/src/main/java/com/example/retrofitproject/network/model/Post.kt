package com.example.retrofitproject.network.model

data class Post(
    val body: String,
    val id: Int =0,
    val title: String,
    val userId: Int
)
