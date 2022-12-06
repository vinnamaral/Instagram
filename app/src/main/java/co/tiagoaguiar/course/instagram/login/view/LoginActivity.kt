package com.vinicius.instagram.login.view

import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowInsetsController
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.vinicius.instagram.R
import com.vinicius.instagram.common.base.DependencyInjector
import com.vinicius.instagram.common.util.TxtWatcher
import com.vinicius.instagram.databinding.ActivityLoginBinding
import com.vinicius.instagram.login.Login
import com.vinicius.instagram.login.data.FakeDataSource
import com.vinicius.instagram.login.data.LoginRepository
import com.vinicius.instagram.login.presentation.LoginPresenter
import com.vinicius.instagram.main.view.MainActivity
import com.vinicius.instagram.register.view.RegisterActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class LoginActivity : AppCompatActivity(), Login.View {

  private lateinit var binding: ActivityLoginBinding

  override lateinit var presenter: Login.Presenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityLoginBinding.inflate(layoutInflater)

    setContentView(binding.root)

    presenter = LoginPresenter(this, DependencyInjector.loginRepository())

    with(binding) {
      when(resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
        Configuration.UI_MODE_NIGHT_YES -> {
          loginImgLogo.imageTintList = ColorStateList.valueOf(Color.WHITE)
        }
        Configuration.UI_MODE_NIGHT_NO -> {
        }
      }


      loginEditEmail.addTextChangedListener(watcher)
      loginEditEmail.addTextChangedListener(TxtWatcher {
        displayEmailFailure(null)
      })

      loginEditPassword.addTextChangedListener(watcher)
      loginEditPassword.addTextChangedListener(TxtWatcher {
        displayPasswordFailure(null)
      })

      loginBtnEnter.setOnClickListener {
        presenter.login(loginEditEmail.text.toString(), loginEditPassword.text.toString())
      }

      loginTxtRegister.setOnClickListener {
        goToRegisterScreen()
      }
    }
  }

  override fun onDestroy() {
    presenter.onDestroy()
    super.onDestroy()
  }

  private val watcher = TxtWatcher {
    binding.loginBtnEnter.isEnabled = binding.loginEditEmail.text.toString().isNotEmpty()
      && binding.loginEditPassword.text.toString().isNotEmpty()
  }

  private fun goToRegisterScreen() {
    startActivity(Intent(this, RegisterActivity::class.java))
  }

  override fun showProgress(enabled: Boolean) {
    binding.loginBtnEnter.showProgress(enabled)
  }

  override fun displayEmailFailure(emailError: Int?) {
    binding.loginEditEmailInput.error = emailError?.let { getString(it) }
  }

  override fun displayPasswordFailure(passwordError: Int?) {
    binding.loginEditPasswordInput.error = passwordError?.let { getString(it) }
  }

  override fun onUserAuthenticated() {
    val intent = Intent(this, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
  }

  override fun onUserUnauthorized(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
  }

}