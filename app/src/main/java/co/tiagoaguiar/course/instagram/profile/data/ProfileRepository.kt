package com.vinicius.instagram.profile.data

import com.vinicius.instagram.common.base.RequestCallback
import com.vinicius.instagram.common.model.Post
import com.vinicius.instagram.common.model.User
import com.vinicius.instagram.common.model.UserAuth

class ProfileRepository(private val dataSourceFactory: ProfileDataSourceFactory) {

  fun clearCache() {
    val localDataSource = dataSourceFactory.createLocalDataSource()
    localDataSource.putPosts(null)
    localDataSource.putUser(null)
  }

  fun fetchUserProfile(uuid: String?, callback: RequestCallback<Pair<User, Boolean?>>) {
    val localDataSource = dataSourceFactory.createLocalDataSource()
    val userId = uuid ?: localDataSource.fetchSession()
    val dataSource = dataSourceFactory.createFromUser(uuid)

   dataSource.fetchUserProfile(userId, object : RequestCallback<Pair<User, Boolean?>> {
      override fun onSuccess(data: Pair<User, Boolean?>) {
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
    val userId = uuid ?: localDataSource.fetchSession()
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
    val userId = uuid ?: localDataSource.fetchSession()
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