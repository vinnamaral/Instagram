package com.vinicius.instagram.common.base

import com.vinicius.instagram.login.data.FakeDataSource
import com.vinicius.instagram.login.data.LoginRepository
import com.vinicius.instagram.register.data.FakeRegisterEmailDataSource
import com.vinicius.instagram.register.data.RegisterEmailRepository

object DependencyInjector {

    fun loginRepository() : LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository() : RegisterEmailRepository {
        return RegisterEmailRepository(FakeRegisterEmailDataSource())
    }

}