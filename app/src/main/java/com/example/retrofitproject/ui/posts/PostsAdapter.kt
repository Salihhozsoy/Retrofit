package com.example.retrofitproject.ui.posts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitproject.databinding.PostListItemBinding
import com.example.retrofitproject.network.model.Post

class PostsAdapter(
    private val context: Context,
    private val posts: List<Post>,
    val onClick: (post: Post) -> Unit
) : RecyclerView.Adapter<PostsAdapter.CustomViewHolder>() {

    class CustomViewHolder(binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val postTitle = binding.tvPostTitle
        val postBody = binding.tvPostBody
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = PostListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val posts = posts[position]
        holder.postTitle.text = posts.title
        holder.postBody.text = posts.body

        holder.itemView.setOnClickListener {
            onClick(posts)
        }
    }
}