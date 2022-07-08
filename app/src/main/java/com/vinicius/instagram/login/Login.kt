package com.vinicius.instagram.login

import androidx.annotation.StringRes
import com.vinicius.instagram.common.base.BasePresenter
import com.vinicius.instagram.common.base.BaseView

interface Login {

    // camada presenter
    interface Presenter : BasePresenter {
        fun login(email: String, password: String)
    }

    // camada view
    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(@StringRes emailError: Int?)
        fun displayPasswordFailure(@StringRes passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized(message: String)
    }

}