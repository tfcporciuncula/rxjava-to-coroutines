package com.fredporciuncula.rxjava2coroutines

import android.app.Application
import com.fredporciuncula.rxjava2coroutines.di.ApplicationComponent
import com.fredporciuncula.rxjava2coroutines.di.DaggerApplicationComponent
import com.fredporciuncula.rxjava2coroutines.di.DaggerComponentProvider
import com.fredporciuncula.rxjava2coroutines.ktx.unsyncLazy
import timber.log.Timber

class PostsApplication : Application(), DaggerComponentProvider {

  override val component: ApplicationComponent by unsyncLazy {
    DaggerApplicationComponent.factory().create(applicationContext)
  }

  override fun onCreate() {
    super.onCreate()
    setupTimber()
  }

  private fun setupTimber() {
    if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
  }
}
