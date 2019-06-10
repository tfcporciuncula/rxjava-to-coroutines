package com.fredporciuncula.rxjava2coroutines.di

import android.content.Context
import androidx.room.Room
import com.fredporciuncula.rxjava2coroutines.db.EasyLibraryDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DaoModule::class])
object DatabaseModule {

  @JvmStatic @Provides @Singleton
  fun provideDatabase(context: Context): EasyLibraryDatabase {
    return Room.databaseBuilder(context, EasyLibraryDatabase::class.java, "easy-library").build()
  }
}

@Module
object DaoModule {

  @JvmStatic @Provides
  fun providePostDao(database: EasyLibraryDatabase) = database.postDao()
}
