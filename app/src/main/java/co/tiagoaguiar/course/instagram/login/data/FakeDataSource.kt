package com.vinicius.instagram.login.data

import android.os.Handler
import android.os.Looper
import com.vinicius.instagram.common.model.Database

class FakeDataSource : LoginDataSource {

  override fun login(email: String, password: String, callback: LoginCallback) {
    Handler(Looper.getMainLooper()).postDelayed({

      val userAuth = Database.usersAuth.firstOrNull { email == it.email }

      when {
        userAuth == null -> {
          callback.onFailure("Usuário não encontrado")
        }
        userAuth.password != password -> {
          callback.onFailure("Senha está incorreta")
        }
        else -> {
          Database.sessionAuth = userAuth
          callback.onSuccess()
        }
      }

      callback.onComplete()
    }, 2000)
  }

}