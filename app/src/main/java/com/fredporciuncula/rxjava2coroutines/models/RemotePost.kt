package com.fredporciuncula.rxjava2coroutines.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemotePost(
  val userId: Long,
  val id: Long,
  val title: String,
  val body: String
)
