package com.vinicius.instagram.home

import com.vinicius.instagram.common.base.BasePresenter
import com.vinicius.instagram.common.base.BaseView
import com.vinicius.instagram.common.model.Post

interface Home {

  interface Presenter : BasePresenter {
    fun fetchFeed()
    fun clear()
  }

  interface View : BaseView<Presenter> {
    fun showProgress(enabled: Boolean)
    fun displayRequestFailure(message: String)
    fun displayEmptyPosts()
    fun displayFullPosts(posts: List<Post>)
  }

}