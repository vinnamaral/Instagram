package com.vinicius.instagram.profile.data

import com.vinicius.instagram.common.base.Cache
import com.vinicius.instagram.common.base.RequestCallback
import com.vinicius.instagram.common.model.Database
import com.vinicius.instagram.common.model.Post
import com.vinicius.instagram.common.model.User
import com.vinicius.instagram.common.model.UserAuth
import com.google.firebase.auth.FirebaseAuth

class ProfileLocalDataSource(
  private val profileCache: Cache<Pair<User, Boolean?>>,
  private val postsCache: Cache<List<Post>>,
) : ProfileDataSource {

  override fun fetchUserProfile(userUUID: String, callback: RequestCallback<Pair<User, Boolean?>>) {
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

  override fun fetchSession(): String {
    return FirebaseAuth.getInstance().uid ?: throw RuntimeException("usuário não logado!!!")
  }

  override fun putUser(response: Pair<User, Boolean?>?) {
    profileCache.put(response)
  }

  override fun putPosts(response: List<Post>?) {
    postsCache.put(response)
  }

}