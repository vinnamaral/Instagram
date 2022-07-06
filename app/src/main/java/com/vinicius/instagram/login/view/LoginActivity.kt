package com.vinicius.instagram.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.vinicius.instagram.common.util.TxtWatcher
import com.vinicius.instagram.databinding.ActivityLoginBinding
import com.vinicius.instagram.login.Login
import com.vinicius.instagram.login.presentation.LoginPresenter

class LoginActivity : AppCompatActivity(), Login.View {

  private lateinit var binding: ActivityLoginBinding

  override lateinit var presenter: Login.Presenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityLoginBinding.inflate(layoutInflater)

    setContentView(binding.root)

    presenter = LoginPresenter(this)

    with(binding) {
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

//        Handler(Looper.getMainLooper()).postDelayed({
//          loginBtnEnter.showProgress(false)
//        }, 2000)
      }
    }
  }

  override fun onDestroy() {
    presenter.onDestroy()
    super.onDestroy()
  }

  private val watcher = TxtWatcher {
    binding.loginBtnEnter.isEnabled = binding.loginEditEmail.text.toString().isNotEmpty() &&
            binding.loginEditPassword.text.toString().isNotEmpty()
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
    // IR PARA A TELA PRINCIPAL
  }

  override fun onUserUnauthorized() {
    // MOSTRAR UM ALERTA
  }

}