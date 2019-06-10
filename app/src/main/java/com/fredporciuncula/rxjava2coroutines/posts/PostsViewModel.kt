package com.fredporciuncula.rxjava2coroutines.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fredporciuncula.rxjava2coroutines.api.PostApi
import com.fredporciuncula.rxjava2coroutines.db.PostDao
import com.fredporciuncula.rxjava2coroutines.livedata.SafeMediatorLiveData
import com.fredporciuncula.rxjava2coroutines.models.toLocalPosts
import com.fredporciuncula.rxjava2coroutines.models.toPosts
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

class PostsViewModel @Inject constructor(
  private val postApi: PostApi,
  private val postDao: PostDao,
  val adapter: PostAdapter
) : ViewModel() {

  private val disposables = CompositeDisposable()

  private val state = SafeMediatorLiveData(initialValue = PostsViewState()).apply {
    addSource(postDao.posts()) { update(posts = it.toPosts()) }
  }

  fun state(): LiveData<PostsViewState> = state

  fun refreshPosts() {
    postApi
      .fetchPosts()
      .doOnSubscribe { state.update(isLoading = true) }
      .doOnSuccess {
        postDao.clearAndInsert(it.toLocalPosts())
      }
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeBy(
        onSuccess = {
          state.update(isLoading = false)
        },
        onError = {
          Timber.e(it)
          state.update(isLoading = false, error = PostsError())
        })
      .addTo(disposables)
  }

  override fun onCleared() = disposables.dispose()
}
