package com.fredporciuncula.rxjava2coroutines.di

import com.fredporciuncula.rxjava2coroutines.api.PostApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {

  @JvmStatic @Provides @Singleton
  fun providePostApi(): PostApi = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
    .create(PostApi::class.java)
}
