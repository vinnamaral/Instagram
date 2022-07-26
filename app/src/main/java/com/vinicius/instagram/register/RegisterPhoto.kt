package com.vinicius.instagram.register

import android.net.Uri
import androidx.annotation.StringRes
import com.vinicius.instagram.common.base.BasePresenter
import com.vinicius.instagram.common.base.BaseView

interface RegisterPhoto {

    interface Presenter : BasePresenter {
        fun updateUser(photoUri: Uri)
    }

    interface View: BaseView<Presenter> {
        fun showProgress(enabled: Boolean)

        fun onUpdateFailure(message: String)

        fun onUpdateSuccess()
    }
}