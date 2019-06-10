package com.fredporciuncula.rxjava2coroutines.posts

import com.fredporciuncula.rxjava2coroutines.livedata.Event
import com.fredporciuncula.rxjava2coroutines.livedata.SafeMediatorLiveData
import com.fredporciuncula.rxjava2coroutines.models.Post

data class PostsViewState(
  val posts: List<Post> = emptyList(),
  val isLoading: Boolean = false,
  val error: PostsError? = null
)

class PostsError : Event()

fun SafeMediatorLiveData<PostsViewState>.update(
  posts: List<Post> = value.posts,
  isLoading: Boolean = value.isLoading,
  error: PostsError? = value.error
) {
  value = value.copy(posts = posts, isLoading = isLoading, error = error)
}
