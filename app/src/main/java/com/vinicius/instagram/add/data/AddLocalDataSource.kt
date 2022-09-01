package com.vinicius.instagram.add.data

import com.vinicius.instagram.common.model.Database
import com.vinicius.instagram.common.model.UserAuth

class AddLocalDataSource : AddDataSource {

  override fun fetchSession(): UserAuth {
    return Database.sessionAuth ?: throw RuntimeException("Usuário não logado!!")
  }
}