package com.vinicius.instagram.register.presentation

import android.net.Uri
import com.vinicius.instagram.R
import com.vinicius.instagram.register.RegisterNameAndPassword
import com.vinicius.instagram.register.RegisterPhoto
import com.vinicius.instagram.register.data.RegisterCallback
import com.vinicius.instagram.register.data.RegisterRepository

class RegisterPhotoPresenter(
  private var view: RegisterPhoto.View?,
  private val repository: RegisterRepository
) : RegisterPhoto.Presenter {

  override fun updateUser(photoUri: Uri) {
    view?.showProgress(true)

    repository.updateUser(photoUri, object : RegisterCallback {
      override fun onSuccess() {
        view?.onUpdateSuccess()
      }

      override fun onFailure(message: String) {
        view?.onUpdateFailure(message)
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