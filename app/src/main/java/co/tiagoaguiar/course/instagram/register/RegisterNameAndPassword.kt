package com.vinicius.instagram.register

import androidx.annotation.StringRes
import com.vinicius.instagram.common.base.BasePresenter
import com.vinicius.instagram.common.base.BaseView

interface RegisterNameAndPassword {

  interface Presenter : BasePresenter {
    fun create(email: String, name: String, password: String, confirm: String)
  }

  interface View: BaseView<Presenter> {
    fun showProgress(enabled: Boolean)

    fun displayNameFailure(@StringRes nameError: Int?)

    fun displayPasswordFailure(@StringRes passError: Int?)

    fun onCreateFailure(message: String)

    fun onCreateSuccess(name: String)
  }

}