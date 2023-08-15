package com.example.retrofitproject.ui.addPost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.retrofitproject.R
import com.example.retrofitproject.databinding.FragmentAddPostBinding
import com.example.retrofitproject.ui.Extension.showToast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AddPostFragment : Fragment(R.layout.fragment_add_post) {

    lateinit var binding: FragmentAddPostBinding
    private val viewModel: AddPostViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddPostBinding.bind(view)

        observeCreatePostSave()
        binding.includeAddOrUpdated.btnSave.setOnClickListener {
            viewModel.createPost(binding.includeAddOrUpdated.etTitle.text.toString().trim(),binding.includeAddOrUpdated.etBody.text.toString().trim()
            )
        }
    }

    private fun observeCreatePostSave() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.createPostState.collect {
                    when (it) {
                        CreatePostState.Idle -> {}
                        CreatePostState.Loading -> {
                            binding.includeAddOrUpdated.progressBar.isVisible = true
                        }

                        CreatePostState.Success -> {
                            binding.includeAddOrUpdated.progressBar.isVisible = false
                            requireActivity().showToast("Post Eklendi")
                        }

                        CreatePostState.Error -> {
                            binding.includeAddOrUpdated.progressBar.isVisible = false
                        }
                    }
                }
            }
        }
    }
}

