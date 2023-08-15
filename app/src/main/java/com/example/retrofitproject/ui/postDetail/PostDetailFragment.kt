package com.example.retrofitproject.ui.postDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.retrofitproject.R
import com.example.retrofitproject.databinding.FragmentPostDetailBinding
import com.example.retrofitproject.ui.PostDeleteState
import com.example.retrofitproject.ui.posts.PostsFragmentDirections
import kotlinx.coroutines.launch

class PostDetailFragment : Fragment(R.layout.fragment_post_detail) {

    lateinit var binding: FragmentPostDetailBinding
    private val args: PostDetailFragmentArgs by navArgs()
    private val viewModel: PostDetailViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostDetailBinding.bind(view)

        viewModel.getPostsById(args.modelId)
        observePost()
        observeDelete()

        binding.ivDelete.setOnClickListener{
            viewModel.delete(args.modelId)
        }
        binding.btnOpenUpdatePage.setOnClickListener{
            val action =PostDetailFragmentDirections.actionPostDetailFragmentToUpdatePostFragment(args.modelId)
            findNavController().navigate(action)
        }
    }

    private fun observeDelete() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.postDeleteState.collect {
                    when (it) {
                        PostDeleteState.Idle -> {}
                        PostDeleteState.Loading -> {}
                        PostDeleteState.Success -> {
                            findNavController().navigate(R.id.action_postDetailFragment_to_postFragment)
                        }
                        PostDeleteState.Error -> {}
                    }
                }
            }
        }
    }

    private fun observePost() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.postDetailState.collect {
                    when (it) {
                        PostDetailState.Idle -> {}
                        PostDetailState.PostNotFound -> {}
                        is PostDetailState.Success -> {
                            binding.tvBody.text = it.post.body
                            binding.tvTitle.text = it.post.title
                        }
                        PostDetailState.Error -> {}
                    }
                }
            }
        }
    }
}
