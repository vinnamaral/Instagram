package com.vinicius.instagram.search

import com.vinicius.instagram.common.base.BasePresenter
import com.vinicius.instagram.common.base.BaseView
import com.vinicius.instagram.common.model.User
import com.vinicius.instagram.common.model.UserAuth

interface Search {

  interface Presenter : BasePresenter {
    fun fetchUsers(name: String)
  }

  interface View : BaseView<Presenter> {
    fun showProgress(enabled: Boolean)
    fun displayFullUsers(users: List<User>)
    fun displayEmptyUsers()
  }

}