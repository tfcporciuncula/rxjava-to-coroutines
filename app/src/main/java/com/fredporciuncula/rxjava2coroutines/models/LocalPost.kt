package com.fredporciuncula.rxjava2coroutines.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class LocalPost(

  @PrimaryKey
  val id: Long,

  @ColumnInfo(name = "user_id")
  val userId: Long,

  @ColumnInfo(name = "title")
  val title: String,

  @ColumnInfo(name = "body")
  val body: String
)
