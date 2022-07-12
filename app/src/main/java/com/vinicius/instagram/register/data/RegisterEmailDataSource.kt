package com.vinicius.instagram.register.data

interface RegisterEmailDataSource {
    fun create(email: String, callback: RegisterEmailCallback)
}