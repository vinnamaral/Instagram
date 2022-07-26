package com.vinicius.instagram.splash.presentation

import com.vinicius.instagram.splash.Splash
import com.vinicius.instagram.splash.data.SplashCallback
import com.vinicius.instagram.splash.data.SplashRepository

class SplashPresenter(
  private var view: Splash.View?,
  private val repository: SplashRepository
) : Splash.Presenter {

  override fun authenticated() {
    repository.session(object : SplashCallback {
      override fun onSuccess() {
        view?.goToMainScreen()
      }

      override fun onFailure() {
        view?.goToLoginScreen()
      }
    })

  }

  override fun onDestroy() {
    view = null
  }

}