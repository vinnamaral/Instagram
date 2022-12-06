package com.vinicius.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.vinicius.instagram.R
import com.vinicius.instagram.common.base.DependencyInjector
import com.vinicius.instagram.common.util.TxtWatcher
import com.vinicius.instagram.databinding.FragmentRegisterNamePasswordBinding
import com.vinicius.instagram.databinding.FragmentRegisterWelcomeBinding
import com.vinicius.instagram.register.RegisterNameAndPassword
import com.vinicius.instagram.register.presentation.RegisterNameAndPasswordPresenter
import java.lang.IllegalArgumentException

class RegisterWelcomeFragment : Fragment(R.layout.fragment_register_welcome) {

  private var binding: FragmentRegisterWelcomeBinding? = null
  private var fragmentAttachListener: FragmentAttachListener? = null

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding = FragmentRegisterWelcomeBinding.bind(view)

    val name = arguments?.getString(KEY_NAME) ?: throw IllegalArgumentException("name not found")

    binding?.let {
      with(it) {
        registerTxtWelcome.text = getString(R.string.welcome_to_instagram, name)

        registerBtnNext.isEnabled = true
        registerBtnNext.setOnClickListener {
          fragmentAttachListener?.goToPhotoScreen()
        }
      }
    }
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    if (context is FragmentAttachListener) {
      fragmentAttachListener = context
    }
  }

  override fun onDestroy() {
    binding = null
    super.onDestroy()
  }

  companion object {
    const val KEY_NAME = "key_name"
  }

}