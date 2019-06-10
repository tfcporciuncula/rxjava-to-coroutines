package com.fredporciuncula.rxjava2coroutines.models

fun RemotePost.toLocalPost() = LocalPost(id, userId, title, body)

fun List<RemotePost>.toLocalPosts() = map { it.toLocalPost() }

fun LocalPost.toPost() = Post(id, title, body)

fun List<LocalPost>.toPosts() = map { it.toPost() }
