package com.vinicius.instagram.register.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.vinicius.instagram.databinding.FragmentRegisterPhotoBinding
import com.vinicius.instagram.R
import com.vinicius.instagram.common.view.CustomDialog

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo) {

    private var binding: FragmentRegisterPhotoBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterPhotoBinding.bind(view)

        val customDialog = CustomDialog(requireContext())

        /*customDialog.addButon({
            Log.i("Teste", (it as TextView).text.toString())
        }, R.string.photo, R.string.gallery)*/

        customDialog.addButon(R.string.photo, R.string.gallery) {
            when(it.id) {
                R.string.photo -> {
                    Log.i("Teste", "foto")
                    // agora a gente consegue abrir a camera
                }

                R.string.gallery -> {
                    Log.i("Teste", "galeria")
                    // agora a gente consegue abrir a galeria
                }
            }
        }

        customDialog.show()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}