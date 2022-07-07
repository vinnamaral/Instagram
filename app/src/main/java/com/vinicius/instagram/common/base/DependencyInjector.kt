package com.vinicius.instagram.common.base

import com.vinicius.instagram.login.data.FakeDataSource
import com.vinicius.instagram.login.data.LoginRepository

object DependencyInjector {
    fun loginRepository() : LoginRepository {
        return LoginRepository(FakeDataSource())
    }
}