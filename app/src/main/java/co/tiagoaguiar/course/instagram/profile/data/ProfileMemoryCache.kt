package com.vinicius.instagram.profile.data

import com.vinicius.instagram.common.base.Cache
import com.vinicius.instagram.common.model.User

object ProfileMemoryCache : Cache<Pair<User, Boolean?>> {

  private var user: Pair<User, Boolean?>? = null

  override fun isCached(): Boolean {
    return user != null
  }

  override fun get(key: String): Pair<User, Boolean?>? {
    if (user?.first?.uuid == key) {
      return user
    }
    return null
  }

  override fun put(data: Pair<User, Boolean?>?) {
    user = data
  }

}