package com.vinicius.instagram.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import com.vinicius.instagram.databinding.ActivityLoginBinding
import com.vinicius.instagram.R

class LoginActivity : AppCompatActivity() {

  private lateinit var binding: ActivityLoginBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityLoginBinding.inflate(layoutInflater)

    setContentView(binding.root)

    with(binding) {
      loginEditEmail.addTextChangedListener(watcher)
      loginEditPassword.addTextChangedListener(watcher)

      loginBtnEnter.setOnClickListener {
        loginBtnEnter.showProgress(true)

        loginEditEmailInput.error = "Esse e-mail é inválido"
        loginEditPasswordInput.error = "Senha incorreta"

        Handler(Looper.getMainLooper()).postDelayed({
          loginBtnEnter.showProgress(false)
        }, 2000)
      }
    }
  }

  private val watcher = object : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      findViewById<LoadingButton>(R.id.login_btn_enter).isEnabled = p0.toString().isNotEmpty()
    }

    override fun afterTextChanged(p0: Editable?) {

    }

  }
}