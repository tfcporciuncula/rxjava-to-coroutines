package com.fredporciuncula.rxjava2coroutines.di

import android.content.Context
import com.fredporciuncula.rxjava2coroutines.posts.PostsViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, DatabaseModule::class])
interface ApplicationComponent {

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance applicationContext: Context): ApplicationComponent
  }

  val postsViewModel: PostsViewModel
}
