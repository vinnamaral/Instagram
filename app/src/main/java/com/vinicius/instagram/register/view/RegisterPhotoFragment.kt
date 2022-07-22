package com.vinicius.instagram.register.view

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.vinicius.instagram.databinding.FragmentRegisterPhotoBinding
import com.vinicius.instagram.R
import com.vinicius.instagram.common.view.CropperImageFragment
import com.vinicius.instagram.common.view.CustomDialog

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo) {

    private var binding: FragmentRegisterPhotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("cropKey") { requestKey, bundle ->
            val uri = bundle.getParcelable<Uri>(CropperImageFragment.KEY_URI)
            onCropImageResult(uri)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterPhotoBinding.bind(view)

        binding?.let {
            with(it) {
                registerBtnJump.setOnClickListener {
                    fragmentAttachListener?.goToMainScreen()
                }

                registerBtnNext.isEnabled = true
                registerBtnNext.setOnClickListener {
                    openDialog()
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

    private fun openDialog() {
        val customDialog = CustomDialog(requireContext())

        /*customDialog.addButon({
            Log.i("Teste", (it as TextView).text.toString())
        }, R.string.photo, R.string.gallery)*/

        customDialog.addButton(R.string.photo, R.string.gallery) {
            when(it.id) {
                R.string.photo -> {
                    fragmentAttachListener?.goToCameraScreen()
                }
                R.string.gallery -> {
                    fragmentAttachListener?.goToGalleryScreen()
                }
            }
        }
        customDialog.show()
    }

    private fun onCropImageResult(uri: Uri?) {
        if (uri != null) {
            val bitmap = if (Build.VERSION.SDK_INT >= 28) {
                val source = ImageDecoder.createSource(requireContext().contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
            }
            binding?.registerImgProfile?.setImageBitmap(bitmap)
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}