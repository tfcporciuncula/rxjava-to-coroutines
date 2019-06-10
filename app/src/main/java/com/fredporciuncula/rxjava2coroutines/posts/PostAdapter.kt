package com.fredporciuncula.rxjava2coroutines.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fredporciuncula.rxjava2coroutines.R
import com.fredporciuncula.rxjava2coroutines.models.Post
import kotlinx.android.synthetic.main.item_post.view.*
import javax.inject.Inject

class PostAdapter @Inject constructor() : ListAdapter<Post, PostAdapter.ViewHolder>(DiffCallback()) {

  class DiffCallback : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleTextView = itemView.titleTextView
    private val bodyTextView = itemView.bodyTextView

    fun bind(post: Post) {
      titleTextView.text = post.title
      bodyTextView.text = post.body
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))
}
