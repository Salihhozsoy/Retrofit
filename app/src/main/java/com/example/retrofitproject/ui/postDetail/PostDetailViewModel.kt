package com.example.retrofitproject.ui.postDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.retrofitproject.network.RetrofitHelper
import com.example.retrofitproject.network.repository.PostRepositoryImpl
import com.example.retrofitproject.network.service.PostService
import com.example.retrofitproject.ui.PostDeleteState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostDetailViewModel:ViewModel() {

    private val _postDetailState:MutableStateFlow<PostDetailState> = MutableStateFlow(PostDetailState.Idle)
    val postDetailState:StateFlow<PostDetailState> =_postDetailState

    private val _postDeleteState: MutableSharedFlow<PostDeleteState> = MutableSharedFlow()
    val postDeleteState: SharedFlow<PostDeleteState> =_postDeleteState

    private val postRepository = PostRepositoryImpl()

    fun getPostsById(id:Int){
        viewModelScope.launch {
            kotlin.runCatching {
                val post =postRepository.getPostById(id)
                post?.let {
                    _postDetailState.value = PostDetailState.Success(post)
                }?: kotlin.run {
                    _postDetailState.value =PostDetailState.PostNotFound
                }
            }.onFailure {
                _postDetailState.value=PostDetailState.Error
            }
        }
    }

    fun delete(id:Int){
        viewModelScope.launch {
            kotlin.runCatching {
                _postDeleteState.emit(PostDeleteState.Loading)
              //  val service= RetrofitHelper.getRetrofit().create(PostService::class.java)
              //  service.delete(id)
                postRepository.delete(id)
                _postDeleteState.emit(PostDeleteState.Success)
            }.onFailure {
                _postDeleteState.emit(PostDeleteState.Error)
            }
        }
    }
}