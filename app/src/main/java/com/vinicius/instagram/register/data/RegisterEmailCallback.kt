package com.vinicius.instagram.register.data

interface RegisterEmailCallback {
    fun onSuccess()
    fun onFailure(message: String)
    fun onComplete()
}