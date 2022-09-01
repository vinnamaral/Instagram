package com.vinicius.instagram.profile.data

import com.vinicius.instagram.common.base.Cache
import com.vinicius.instagram.common.base.RequestCallback
import com.vinicius.instagram.common.model.Database
import com.vinicius.instagram.common.model.Post
import com.vinicius.instagram.common.model.UserAuth

class ProfileLocalDataSource(
  private val profileCache: Cache<Pair<UserAuth, Boolean?>>,
  private val postsCache: Cache<List<Post>>,
) : ProfileDataSource {

  override fun fetchUserProfile(userUUID: String, callback: RequestCallback<Pair<UserAuth, Boolean?>>) {
    val userAuth = profileCache.get(userUUID)
    if (userAuth != null) {
      callback.onSuccess(userAuth)
    } else {
      callback.onFailure("Usuário não encontrado")
    }
    callback.onComplete()
  }

  override fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>) {
    val posts = postsCache.get(userUUID)
    if (posts != null) {
      callback.onSuccess(posts)
    } else {
      callback.onFailure("posts não existem")
    }
    callback.onComplete()
  }

  override fun fetchSession(): UserAuth {
    return Database.sessionAuth ?: throw RuntimeException("usuário não logado!!!")
  }

  override fun putUser(response: Pair<UserAuth, Boolean?>) {
    profileCache.put(response)
  }

  override fun putPosts(response: List<Post>?) {
    postsCache.put(response)
  }

}