package com.vinicius.instagram.common.base

import com.vinicius.instagram.login.data.FakeDataSource
import com.vinicius.instagram.login.data.LoginRepository
import com.vinicius.instagram.register.data.FakeRegisterDataSource
import com.vinicius.instagram.register.data.RegisterRepository
import com.vinicius.instagram.splash.data.FakeLocalDataSource
import com.vinicius.instagram.splash.data.SplashRepository

object DependencyInjector {

    fun splashRepository() : SplashRepository {
        return SplashRepository(FakeLocalDataSource())
    }

    fun loginRepository() : LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository() : RegisterRepository {
        return RegisterRepository(FakeRegisterDataSource())
    }

}