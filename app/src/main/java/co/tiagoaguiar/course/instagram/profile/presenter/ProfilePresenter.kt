package com.vinicius.instagram.profile.presenter

import com.vinicius.instagram.common.base.RequestCallback
import com.vinicius.instagram.common.model.Post
import com.vinicius.instagram.common.model.User
import com.vinicius.instagram.common.model.UserAuth
import com.vinicius.instagram.profile.Profile
import com.vinicius.instagram.profile.data.ProfileRepository
import java.util.*

class ProfilePresenter(
  private var view: Profile.View?,
  private val repository: ProfileRepository
) : Profile.Presenter {

  override fun clear() {
    repository.clearCache()
  }

  override fun fetchUserProfile(uuid: String?) {
    view?.showProgress(true)
    repository.fetchUserProfile(uuid, object : RequestCallback<Pair<User, Boolean?>> {
      override fun onSuccess(data: Pair<User, Boolean?>) {
        view?.displayUserProfile(data)
      }

      override fun onFailure(message: String) {
        view?.displayRequestFailure(message)
      }

      override fun onComplete() {
      }
    })
  }

  override fun fetchUserPosts(uuid: String?) {
    repository.fetchUserPosts(uuid, object : RequestCallback<List<Post>> {
      override fun onSuccess(data: List<Post>) {
        if (data.isEmpty()) {
          view?.displayEmptyPosts()
        } else {
          view?.displayFullPosts(data)
        }
      }

      override fun onFailure(message: String) {
        view?.displayRequestFailure(message)
      }

      override fun onComplete() {
        view?.showProgress(false)
      }
    })
  }

  override fun followUser(uuid: String?, follow: Boolean) {
    repository.followUser(uuid, follow, object : RequestCallback<Boolean> {
      override fun onSuccess(data: Boolean) {
        fetchUserProfile(uuid)

        if (data) {
          view?.followUpdated()
        }
      }

      override fun onFailure(message: String) { }

      override fun onComplete() { }
    })
  }

  override fun onDestroy() {
    view = null
  }

}