package com.vinicius.instagram.common.model

import java.util.*

object Database {

    val userAuth = hashSetOf<UserAuth>()

    var sessionAuth: UserAuth? = null

    init {
        userAuth.add(UserAuth(UUID.randomUUID().toString(), "userA@gmail.com","12345678"))
        userAuth.add(UserAuth(UUID.randomUUID().toString(), "userB@gmail.com","87654321"))
    }
}