package com.vinicius.instagram.camera.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.vinicius.instagram.R

class CameraFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

}