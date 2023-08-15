package com.example.retrofitproject.ui.updatePost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.retrofitproject.R
import com.example.retrofitproject.databinding.FragmentUpdatePostBinding
import com.example.retrofitproject.ui.postDetail.PostDetailFragmentArgs
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UpdatePostFragment : Fragment(R.layout.fragment_update_post) {

    private lateinit var binding: FragmentUpdatePostBinding
    private val viewModel: UpdatePostViewModel by activityViewModels()
    private val args: UpdatePostFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdatePostBinding.bind(view)

        viewModel.getPost(args.updateId)
        observeGetPostState()
        observeUpdatePostState()
        binding.includeAddOrUptatePost.btnSave.text ="Update Post"
        binding.includeAddOrUptatePost.btnSave.setOnClickListener{
            viewModel.updatePost(binding.includeAddOrUptatePost.etBody.text.toString().trim(),binding.includeAddOrUptatePost.etTitle.text.toString().trim(),args.updateId)
        }
    }

    private fun observeGetPostState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.getPostState.collect{
                    when(it){
                        GetPostState.Idle->{}
                        GetPostState.Loading->{}
                        is GetPostState.Success->{
                            binding.includeAddOrUptatePost.etBody.setText(it.post.body)
                            binding.includeAddOrUptatePost.etTitle.setText(it.post.title)
                        }
                        GetPostState.Error->{}
                    }
                }
            }
        }
    }
   private fun observeUpdatePostState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.updatePostState.collect{
                    when(it){
                        UpdatePostState.Idle->{}
                        UpdatePostState.Loading->{}
                        is UpdatePostState.Success->{
                            findNavController().popBackStack()
                        }
                        UpdatePostState.Error->{}
                    }
                }
            }
        }
    }
}