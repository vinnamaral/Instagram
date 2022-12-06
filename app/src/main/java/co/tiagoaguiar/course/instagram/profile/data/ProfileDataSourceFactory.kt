package com.vinicius.instagram.profile.data

import com.vinicius.instagram.common.base.Cache
import com.vinicius.instagram.common.model.Post
import com.vinicius.instagram.common.model.User
import com.vinicius.instagram.common.model.UserAuth

class ProfileDataSourceFactory(
  private val profileCache: Cache<Pair<User, Boolean?>>,
  private val postsCache: Cache<List<Post>>,
) {

  fun createLocalDataSource(): ProfileDataSource {
    return ProfileLocalDataSource(profileCache, postsCache)
  }

  fun createRemoteDataSource(): ProfileDataSource {
    return FireProfileDataSource()
  }

  fun createFromUser(uuid: String?): ProfileDataSource {
    if (uuid != null)
      return createRemoteDataSource()

    if (profileCache.isCached()) {
      return ProfileLocalDataSource(profileCache, postsCache)
    }
    return createRemoteDataSource()
  }

  fun createFromPosts(uuid: String?): ProfileDataSource {
    if (uuid != null)
      return createRemoteDataSource()

    if (postsCache.isCached()) {
      return ProfileLocalDataSource(profileCache, postsCache)
    }
    return createRemoteDataSource()
  }

}