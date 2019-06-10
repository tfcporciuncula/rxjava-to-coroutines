package com.fredporciuncula.rxjava2coroutines.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.fredporciuncula.rxjava2coroutines.models.LocalPost
import io.reactivex.Completable

@Dao
interface PostDao {

  @Query("SELECT * FROM posts")
  fun posts(): LiveData<List<LocalPost>>

  @Query("DELETE FROM posts")
  fun clear(): Completable

  @Insert fun insert(posts: List<LocalPost>): Completable

  @Transaction fun clearAndInsert(posts: List<LocalPost>) {
    clear().blockingAwait()
    insert(posts).blockingAwait()
  }
}
