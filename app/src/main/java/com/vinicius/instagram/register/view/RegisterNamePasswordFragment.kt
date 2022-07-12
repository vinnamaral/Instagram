package com.vinicius.instagram.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vinicius.instagram.R
import com.vinicius.instagram.databinding.FragmentRegisterNamePasswordBinding
import android.util.Log

class RegisterNamePasswordFragment : Fragment(R.layout.fragment_register_name_password) {

    private var binding: FragmentRegisterNamePasswordBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterNamePasswordBinding.bind(view)

        val email = arguments?.getString(KEY_EMAIL)

        Log.i("Teste", email.toString())
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        const val KEY_EMAIL = "key_email"
    }

}