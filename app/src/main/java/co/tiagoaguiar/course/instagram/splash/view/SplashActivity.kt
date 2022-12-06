package com.vinicius.instagram.splash.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinicius.instagram.common.base.DependencyInjector
import com.vinicius.instagram.common.extension.animationEnd
import com.vinicius.instagram.databinding.ActivitySplashBinding
import com.vinicius.instagram.login.view.LoginActivity
import com.vinicius.instagram.main.view.MainActivity
import com.vinicius.instagram.splash.Splash
import com.vinicius.instagram.splash.presentation.SplashPresenter

class SplashActivity : AppCompatActivity(), Splash.View {

  private lateinit var binding: ActivitySplashBinding

  override lateinit var presenter: Splash.Presenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivitySplashBinding.inflate(layoutInflater)

    setContentView(binding.root)

    val repository = DependencyInjector.splashRepository()
    presenter = SplashPresenter(this, repository)

    binding.splashImg.animate().apply {
      setListener(animationEnd {
        presenter.authenticated()
      })
      duration = 1000
      alpha(1.0f)
      start()
    }
  }

  override fun onDestroy() {
    presenter.onDestroy()
    super.onDestroy()
  }

  override fun goToMainScreen() {
    binding.splashImg.animate().apply {
      setListener(animationEnd {
        val intent = Intent(baseContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
      })
      duration = 1000
      startDelay = 1000
      alpha(0.0f)
      start()
    }
  }

  override fun goToLoginScreen() {
    binding.splashImg.animate().apply {
      setListener(animationEnd {
        val intent = Intent(baseContext, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
      })
      duration = 1000
      startDelay = 1000
      alpha(0.0f)
      start()
    }
  }

}