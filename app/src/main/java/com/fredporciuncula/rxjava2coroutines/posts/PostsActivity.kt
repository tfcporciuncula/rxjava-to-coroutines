package com.fredporciuncula.rxjava2coroutines.posts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.fredporciuncula.rxjava2coroutines.R
import com.fredporciuncula.rxjava2coroutines.di.injector
import com.fredporciuncula.rxjava2coroutines.ktx.viewModels
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_posts.*

class PostsActivity : AppCompatActivity() {

  private val viewModel by viewModels { injector.postsViewModel }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_posts)

    refreshIfInitialLaunch(savedInstanceState)
    setupUi()
    observeViewState()
  }

  private fun refreshIfInitialLaunch(savedInstanceState: Bundle?) {
    if (savedInstanceState == null) viewModel.refreshPosts()
  }

  private fun setupUi() {
    swipeRefreshLayout.setOnRefreshListener { viewModel.refreshPosts() }
    recyclerView.adapter = viewModel.adapter
  }

  private fun observeViewState() {
    viewModel.state().observe(this) {
      viewModel.adapter.submitList(it.posts)

      swipeRefreshLayout.isRefreshing = it.isLoading

      it.error?.let { error ->
        error.doIfNotHandled { showError() }
      }
    }
  }

  private fun showError() = Snackbar.make(rootView, R.string.error_message, Snackbar.LENGTH_LONG).show()
}
