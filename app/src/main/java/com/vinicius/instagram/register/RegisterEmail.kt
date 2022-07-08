package com.vinicius.instagram.register

import androidx.annotation.StringRes
import com.vinicius.instagram.common.base.BasePresenter
import com.vinicius.instagram.common.base.BaseView

interface RegisterEmail {

    interface Presenter : BasePresenter {

    }

    interface View: BaseView<Presenter> {
        fun displayEmailFailure(@StringRes emailError: Int?)
    }
}