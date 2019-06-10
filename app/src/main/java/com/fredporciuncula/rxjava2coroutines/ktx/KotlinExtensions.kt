package com.fredporciuncula.rxjava2coroutines.ktx

fun <T> unsyncLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)
