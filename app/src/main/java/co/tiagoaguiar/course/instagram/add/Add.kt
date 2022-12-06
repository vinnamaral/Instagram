package com.vinicius.instagram.add

import android.net.Uri
import com.vinicius.instagram.common.base.BasePresenter
import com.vinicius.instagram.common.base.BaseView

interface Add {

  interface Presenter : BasePresenter {
    fun createPost(uri: Uri, caption: String)
  }

  interface View : BaseView<Presenter> {
    fun showProgress(enabled: Boolean)
    fun displayRequestSuccess()
    fun displayRequestFailure(message: String)
  }

}