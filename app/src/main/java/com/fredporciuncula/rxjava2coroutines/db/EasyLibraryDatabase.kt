package com.fredporciuncula.rxjava2coroutines.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fredporciuncula.rxjava2coroutines.models.LocalPost

@Database(entities = [LocalPost::class], version = 1, exportSchema = false)
abstract class EasyLibraryDatabase : RoomDatabase() {

  abstract fun postDao(): PostDao
}
