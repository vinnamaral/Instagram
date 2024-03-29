package com.vinicius.instagram.search.data

import com.vinicius.instagram.common.base.RequestCallback
import com.vinicius.instagram.common.model.User
import com.vinicius.instagram.common.model.UserAuth

class SearchRepository(private val dataSource: SearchDataSource) {

  fun fetchUsers(name: String, callback: RequestCallback<List<User>>) {
    dataSource.fetchUsers(name, object : RequestCallback<List<User>> {
      override fun onSuccess(data: List<User>) {
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