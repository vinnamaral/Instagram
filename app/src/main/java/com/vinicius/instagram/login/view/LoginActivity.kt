package com.vinicius.instagram.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vinicius.instagram.common.util.TxtWatcher
import com.vinicius.instagram.databinding.ActivityLoginBinding
import com.vinicius.instagram.login.Login

class LoginActivity : AppCompatActivity(), Login.View {

  private lateinit var binding: ActivityLoginBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityLoginBinding.inflate(layoutInflater)

    setContentView(binding.root)

    with(binding) {
      loginEditEmail.addTextChangedListener(watcher)
      loginEditPassword.addTextChangedListener(watcher)

      loginBtnEnter.setOnClickListener {
        // CHAMAR O PRESENTER !!!!

//        Handler(Looper.getMainLooper()).postDelayed({
//          loginBtnEnter.showProgress(false)
//        }, 2000)
      }
    }
  }

  private val watcher = TxtWatcher {
    binding.loginBtnEnter.isEnabled = it.isNotEmpty()
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