package com.example.retrofitproject.ui.updatePost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitproject.network.RetrofitHelper
import com.example.retrofitproject.network.model.Post
import com.example.retrofitproject.network.repository.PostRepositoryImpl
import com.example.retrofitproject.network.service.PostService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UpdatePostViewModel:ViewModel() {

    private val _updatePostState: MutableStateFlow<UpdatePostState> = MutableStateFlow(UpdatePostState.Idle)
    val updatePostState: StateFlow<UpdatePostState> = _updatePostState

    private val _getPostState: MutableStateFlow<GetPostState> = MutableStateFlow(GetPostState.Idle)
    val getPostState: StateFlow<GetPostState> = _getPostState

    private val postRepository = PostRepositoryImpl()

    fun getPost(id:Int){
        viewModelScope.launch{
            kotlin.runCatching {
                _getPostState.value =GetPostState.Loading
                val post= postRepository.getPostById(id)
                post?.let {
                    _getPostState.value =GetPostState.Success(post)
                }?: kotlin.run {
                    println("notfound")
                }

            }.onFailure {
                _getPostState.value =GetPostState.Error
            }
        }
    }

    fun updatePost(body:String,title:String,id:Int){
        viewModelScope.launch {
            kotlin.runCatching {
                _updatePostState.value =UpdatePostState.Loading
                val service = RetrofitHelper.getRetrofit().create(PostService::class.java)
                val post =Post(body=body,title=title, userId = 1)
                val updatedPost =service.update(post=post,id=id)

             //  val updatedPost=postRepository.update(post,id)
                _updatePostState.value =UpdatePostState.Success(updatedPost)

            }.onFailure {
                _updatePostState.value=UpdatePostState.Error
            }
        }
    }

}