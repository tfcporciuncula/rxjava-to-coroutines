package com.fredporciuncula.rxjava2coroutines.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.fredporciuncula.rxjava2coroutines.models.LocalPost

@Dao
interface PostDao {

  @Query("SELECT * FROM posts")
  fun posts(): LiveData<List<LocalPost>>

  @Query("DELETE FROM posts")
  suspend fun clear()

  @Insert suspend fun insert(posts: List<LocalPost>)

  @Transaction suspend fun clearAndInsert(posts: List<LocalPost>) {
    clear()
    insert(posts)
  }
}
