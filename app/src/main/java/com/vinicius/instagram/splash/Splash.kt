package com.vinicius.instagram.splash

import com.vinicius.instagram.common.base.BasePresenter
import com.vinicius.instagram.common.base.BaseView

interface Splash {

  interface Presenter : BasePresenter {
    fun authenticated()
  }

  interface View : BaseView<Presenter> {
    fun goToMainScreen()
    fun goToLoginScreen()
  }

}