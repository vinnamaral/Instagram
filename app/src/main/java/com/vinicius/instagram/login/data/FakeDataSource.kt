package com.vinicius.instagram.login.data

import android.os.Handler
import android.os.Looper

class FakeDataSource : LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            // depois de 2 segundos
            if (email == "a@a.com" && password == "12345678") {
                callback.onSuccess()
            } else {
                callback.onFailure("usuário não encontrado")
            }
            callback.onComplete()
        }, 2000)
    }
}