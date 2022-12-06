package com.vinicius.instagram.search.data

import com.vinicius.instagram.common.base.RequestCallback
import com.vinicius.instagram.common.model.User
import com.vinicius.instagram.common.model.UserAuth

interface SearchDataSource {
  fun fetchUsers(name: String, callback: RequestCallback<List<User>>)
}