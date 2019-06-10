package com.fredporciuncula.rxjava2coroutines.api

import com.fredporciuncula.rxjava2coroutines.models.RemotePost
import retrofit2.http.GET

interface PostApi {

  @GET("posts")
  suspend fun fetchPosts(): List<RemotePost>
}
