package com.vinicius.instagram.common.base

import com.vinicius.instagram.login.data.FakeDataSource
import com.vinicius.instagram.login.data.LoginRepository
import com.vinicius.instagram.register.data.FakeRegisterDataSource
import com.vinicius.instagram.register.data.RegisterRepository

object DependencyInjector {

    fun loginRepository() : LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository() : RegisterRepository {
        return RegisterRepository(FakeRegisterDataSource())
    }

}