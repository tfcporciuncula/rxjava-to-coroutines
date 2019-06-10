package com.fredporciuncula.rxjava2coroutines.api

import com.fredporciuncula.rxjava2coroutines.models.RemotePost
import io.reactivex.Single
import retrofit2.http.GET

interface PostApi {

  @GET("posts")
  fun fetchPosts(): Single<List<RemotePost>>
}
