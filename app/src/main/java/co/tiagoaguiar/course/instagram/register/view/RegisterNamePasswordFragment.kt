package com.vinicius.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.vinicius.instagram.R
import com.vinicius.instagram.common.base.DependencyInjector
import com.vinicius.instagram.common.util.TxtWatcher
import com.vinicius.instagram.databinding.FragmentRegisterNamePasswordBinding
import com.vinicius.instagram.register.RegisterNameAndPassword
import com.vinicius.instagram.register.presentation.RegisterNameAndPasswordPresenter
import java.lang.IllegalArgumentException

class RegisterNamePasswordFragment : Fragment(R.layout.fragment_register_name_password),
  RegisterNameAndPassword.View {

  private var binding: FragmentRegisterNamePasswordBinding? = null
  private var fragmentAttachListener: FragmentAttachListener? = null

  override lateinit var presenter: RegisterNameAndPassword.Presenter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding = FragmentRegisterNamePasswordBinding.bind(view)

    val repository = DependencyInjector.registerEmailRepository()
    presenter = RegisterNameAndPasswordPresenter(this, repository)

    val email = arguments?.getString(KEY_EMAIL) ?: throw IllegalArgumentException("email not found")

    binding?.let {
      with(it) {
        registerTxtLogin.setOnClickListener {
          activity?.finish()
        }

        registerNameBtnNext.setOnClickListener {
          presenter.create(
            email,
            registerEditName.text.toString(),
            registerEditPassword.text.toString(),
            registerEditConfirm.text.toString()
          )
        }

        registerEditName.addTextChangedListener(watcher)
        registerEditPassword.addTextChangedListener(watcher)
        registerEditConfirm.addTextChangedListener(watcher)

        registerEditName.addTextChangedListener(TxtWatcher {
          displayNameFailure(null)
        })
        registerEditPassword.addTextChangedListener(TxtWatcher {
          displayPasswordFailure(null)
        })
        registerEditConfirm.addTextChangedListener(TxtWatcher {
          displayPasswordFailure(null)
        })
      }
    }
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    if (context is FragmentAttachListener) {
      fragmentAttachListener = context
    }
  }

  override fun showProgress(enabled: Boolean) {
    binding?.registerNameBtnNext?.showProgress(enabled)
  }

  override fun displayNameFailure(nameError: Int?) {
    binding?.registerEditNameInput?.error = nameError?.let { getString(it) }
  }

  override fun displayPasswordFailure(passError: Int?) {
    binding?.registerEditPasswordInput?.error = passError?.let { getString(it) }
  }

  override fun onCreateFailure(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
  }

  override fun onCreateSuccess(name: String) {
    fragmentAttachListener?.goToWelcomeScreen(name)
  }

  private val watcher = TxtWatcher {
    binding?.registerNameBtnNext?.isEnabled = binding?.registerEditName?.text.toString().isNotEmpty()
      && binding?.registerEditPassword?.text.toString().isNotEmpty()
      && binding?.registerEditConfirm?.text.toString().isNotEmpty()
  }


  override fun onDestroy() {
    binding = null
    presenter.onDestroy()
    super.onDestroy()
  }

  companion object {
    const val KEY_EMAIL = "key_email"
  }

}