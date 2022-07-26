package com.vinicius.instagram.splash.data

interface SplashDataSource {
  fun session(callback: SplashCallback)
}