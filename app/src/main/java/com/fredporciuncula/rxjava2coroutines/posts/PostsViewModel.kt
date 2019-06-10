package com.fredporciuncula.rxjava2coroutines.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredporciuncula.rxjava2coroutines.api.PostApi
import com.fredporciuncula.rxjava2coroutines.db.PostDao
import com.fredporciuncula.rxjava2coroutines.livedata.SafeMediatorLiveData
import com.fredporciuncula.rxjava2coroutines.models.toLocalPosts
import com.fredporciuncula.rxjava2coroutines.models.toPosts
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class PostsViewModel @Inject constructor(
  private val postApi: PostApi,
  private val postDao: PostDao,
  val adapter: PostAdapter
) : ViewModel() {

  private val state = SafeMediatorLiveData(initialValue = PostsViewState()).apply {
    addSource(postDao.posts()) { update(posts = it.toPosts()) }
  }

  fun state(): LiveData<PostsViewState> = state

  fun refreshPosts() {
    viewModelScope.launch {
      runCatching {
        state.update(isLoading = true)
        postDao.clearAndInsert(postApi.fetchPosts().toLocalPosts())
        state.update(isLoading = false)
      }.onFailure(::handleFailure)
    }
  }

  private fun handleFailure(throwable: Throwable) {
    if (throwable is CancellationException) return

    Timber.e(throwable)
    state.update(isLoading = false, error = PostsError())
  }
}
