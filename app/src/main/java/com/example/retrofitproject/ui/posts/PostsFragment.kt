package com.example.retrofitproject.ui.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.retrofitproject.R
import com.example.retrofitproject.databinding.FragmentPostsBinding
import kotlinx.coroutines.launch

class PostsFragment : Fragment(R.layout.fragment_posts) {


    private lateinit var binding: FragmentPostsBinding
    private val viewModel: PostsFragmentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostsBinding.bind(view)

        listeners()
        viewModel.getAllPosts()
        observePostState()
    }
    private fun observePostState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.postState.collect {
                    when (it) {
                        PostState.Idle -> {}
                        PostState.Empty -> {}
                        is PostState.Result -> {
                            binding.rvPosts.adapter = PostsAdapter(requireContext(), it.post) {
                                val action = PostsFragmentDirections.actionPostFragmentToPostDetailFragment(it.id, it.title)
                                findNavController().navigate(action)
                            }
                        }
                        PostState.Error -> {}
                    }
                }
            }
        }
    }

    private fun listeners() {
        binding.btnPostDetail.setOnClickListener {
        //    val action = PostsFragmentDirections.actionPostFragmentToPostDetailFragment(123)
        //    findNavController().navigate(action)
        }
        binding.btnAddPost.setOnClickListener {
            findNavController().navigate(R.id.action_postFragment_to_addPostFragment)
        }
        binding.btnDrawer.setOnClickListener {
            findNavController().navigate(R.id.action_postFragment_to_drawerActivity)
        }
    }
}