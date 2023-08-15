package com.example.retrofitproject.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitproject.network.RetrofitHelper
import com.example.retrofitproject.network.repository.PostRepository
import com.example.retrofitproject.network.repository.PostRepositoryImpl
import com.example.retrofitproject.network.service.PostService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostsFragmentViewModel : ViewModel() {

    private val _postState: MutableStateFlow<PostState> = MutableStateFlow(PostState.Idle)
    val postState: StateFlow<PostState> = _postState
    private val postRepository = PostRepositoryImpl()


    fun getAllPosts() {
        viewModelScope.launch {
            kotlin.runCatching {
                val posts = postRepository.getPosts()
                _postState.value = PostState.Result(posts)
            }.onFailure {
                _postState.value = PostState.Error
            }
        }
    }
}