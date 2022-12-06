package com.vinicius.instagram.profile.data

import com.vinicius.instagram.common.base.RequestCallback
import com.vinicius.instagram.common.model.Post
import com.vinicius.instagram.common.model.User
import com.vinicius.instagram.common.model.UserAuth
import java.lang.UnsupportedOperationException

interface ProfileDataSource {

  fun fetchUserProfile(userUUID: String, callback: RequestCallback<Pair<User, Boolean?>>)

  fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>)

  fun followUser(userUUID: String, isFollow: Boolean, callback: RequestCallback<Boolean>) { throw UnsupportedOperationException() }

  fun fetchSession() : String { throw UnsupportedOperationException() }

  fun putUser(response: Pair<User, Boolean?>?) { throw UnsupportedOperationException() }

  fun putPosts(response: List<Post>?) { throw UnsupportedOperationException() }

}