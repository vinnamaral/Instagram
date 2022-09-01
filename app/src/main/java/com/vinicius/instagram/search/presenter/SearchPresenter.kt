package com.vinicius.instagram.search.presenter

import com.vinicius.instagram.common.base.RequestCallback
import com.vinicius.instagram.common.model.UserAuth
import com.vinicius.instagram.search.Search
import com.vinicius.instagram.search.data.SearchRepository

class SearchPresenter(
  private var view: Search.View?,
  private val repository: SearchRepository
) : Search.Presenter {


  override fun fetchUsers(name: String) {
    view?.showProgress(true)
    repository.fetchUsers(name, object : RequestCallback<List<UserAuth>> {
      override fun onSuccess(data: List<UserAuth>) {
        if (data.isEmpty()) {
          view?.displayEmptyUsers()
        } else {
          view?.displayFullUsers(data)
        }
      }

      override fun onFailure(message: String) {
        view?.displayEmptyUsers()
      }

      override fun onComplete() {
        view?.showProgress(false)
      }
    })
  }

  override fun onDestroy() {
    view = null
  }

}