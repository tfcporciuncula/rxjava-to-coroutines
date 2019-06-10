package com.fredporciuncula.rxjava2coroutines.livedata

import androidx.lifecycle.MediatorLiveData

/**
 * A safer [MediatorLiveData] that has an initial value and doesn't accept nor expose nullables.
 */
class SafeMediatorLiveData<T : Any>(initialValue: T) : MediatorLiveData<T>() {

  init {
    value = initialValue
  }

  override fun getValue(): T = super.getValue()!!
}
