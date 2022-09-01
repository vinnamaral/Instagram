package com.vinicius.instagram.search.data

import android.os.Handler
import android.os.Looper
import com.vinicius.instagram.common.base.RequestCallback
import com.vinicius.instagram.common.model.Database
import com.vinicius.instagram.common.model.UserAuth

class SearchFakeRemoteDataSource : SearchDataSource {

  override fun fetchUsers(name: String, callback: RequestCallback<List<UserAuth>>) {
    Handler(Looper.getMainLooper()).postDelayed({
      val users = Database.usersAuth.filter {
          it.name.lowercase().startsWith(name.lowercase()) && it.uuid != Database.sessionAuth!!.uuid
      }

      callback.onSuccess(users.toList())

      callback.onComplete()

    }, 2000)
  }

}