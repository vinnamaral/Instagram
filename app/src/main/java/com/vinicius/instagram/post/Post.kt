package com.vinicius.instagram.post

import android.net.Uri
import com.vinicius.instagram.common.base.BasePresenter
import com.vinicius.instagram.common.base.BaseView

interface Post {

  interface Presenter: BasePresenter {
    fun selectUri(uri: Uri)
    fun getSelectedUri(): Uri?
    fun fetchPictures()
  }

  interface View : BaseView<Presenter> {
    fun showProgress(enabled: Boolean)
    fun displayFullPictures(posts: List<Uri>)
    fun displayEmptyPictures()
    fun displayRequestFailure(message: String)
  }

}