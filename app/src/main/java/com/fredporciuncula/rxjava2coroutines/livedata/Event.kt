package com.fredporciuncula.rxjava2coroutines.livedata

import androidx.lifecycle.LiveData

/**
 * Wrapper for events emitted by a [LiveData].
 *
 * Inspired by https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 */
open class Event {

  private var hasNotBeenHandled = true

  fun doIfNotHandled(block: () -> Unit) {
    if (hasNotBeenHandled) {
      hasNotBeenHandled = false
      block()
    }
  }
}
