package com.example.retrofitproject.ui.addPost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitproject.network.RetrofitHelper
import com.example.retrofitproject.network.model.Post
import com.example.retrofitproject.network.service.PostService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddPostViewModel:ViewModel() {

    private val _createPostState:MutableStateFlow<CreatePostState> = MutableStateFlow(CreatePostState.Idle)
    val createPostState:StateFlow<CreatePostState> = _createPostState

    fun createPost(title:String,body:String){
        viewModelScope.launch {
            kotlin.runCatching {
                _createPostState.value =CreatePostState.Loading
                val service =RetrofitHelper.getRetrofit().create(PostService::class.java)
                val post = Post(body=body, title = title, userId = 1)
                val createdPost = service.create(post)
                _createPostState.value =CreatePostState.Success
                println(createdPost)
            }.onFailure {
               _createPostState.value =CreatePostState.Error
            }
        }
    }
}