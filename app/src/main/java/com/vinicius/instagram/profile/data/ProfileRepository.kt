package com.vinicius.instagram.profile.data

import com.vinicius.instagram.common.base.RequestCallback
import com.vinicius.instagram.common.model.Post
import com.vinicius.instagram.common.model.UserAuth

class ProfileRepository(private val dataSourceFactory: ProfileDataSourceFactory) {

  fun clearCache() {
    val localDataSource = dataSourceFactory.createLocalDataSource()
    localDataSource.putPosts(null)
  }

  fun fetchUserProfile(uuid: String?, callback: RequestCallback<Pair<UserAuth, Boolean?>>) {
    val localDataSource = dataSourceFactory.createLocalDataSource()
    val userId = uuid ?: localDataSource.fetchSession().uuid
    val dataSource = dataSourceFactory.createFromUser(uuid)

    dataSource.fetchUserProfile(userId, object : RequestCallback<Pair<UserAuth, Boolean?>> {
      override fun onSuccess(data: Pair<UserAuth, Boolean?>) {
        if (uuid == null) {
          localDataSource.putUser(data)
        }
        callback.onSuccess(data)
      }

      override fun onFailure(message: String) {
        callback.onFailure(message)
      }

      override fun onComplete() {
        callback.onComplete()
      }

    })
  }

  fun fetchUserPosts(uuid: String?, callback: RequestCallback<List<Post>>) {
    val localDataSource = dataSourceFactory.createLocalDataSource()
    val userId = uuid ?: localDataSource.fetchSession().uuid
    val dataSource = dataSourceFactory.createFromPosts(uuid)

    dataSource.fetchUserPosts(userId, object : RequestCallback<List<Post>> {
      override fun onSuccess(data: List<Post>) {
        if (uuid == null) {
          localDataSource.putPosts(data)
        }
        callback.onSuccess(data)
      }

      override fun onFailure(message: String) {
        callback.onFailure(message)
      }

      override fun onComplete() {
        callback.onComplete()
      }
    })
  }

  fun followUser(uuid: String?, follow: Boolean, callback: RequestCallback<Boolean>) {
    val localDataSource = dataSourceFactory.createLocalDataSource()
    val userId = uuid ?: localDataSource.fetchSession().uuid
    val dataSource = dataSourceFactory.createRemoteDataSource()

    dataSource.followUser(userId, follow, object : RequestCallback<Boolean> {
      override fun onSuccess(data: Boolean) {
        callback.onSuccess(data)
      }

      override fun onFailure(message: String) {
        callback.onFailure(message)
      }

      override fun onComplete() {
        callback.onComplete()
      }
    })
  }

}