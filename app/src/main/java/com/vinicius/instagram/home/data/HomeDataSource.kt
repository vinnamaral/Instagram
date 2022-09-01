package com.vinicius.instagram.home.data

import com.vinicius.instagram.common.base.RequestCallback
import com.vinicius.instagram.common.model.Post
import com.vinicius.instagram.common.model.UserAuth
import java.lang.UnsupportedOperationException

interface HomeDataSource {

  fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>)

  fun fetchSession() : UserAuth { throw UnsupportedOperationException() }

  fun putFeed(response: List<Post>?) { throw UnsupportedOperationException() }

}