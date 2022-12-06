package com.vinicius.instagram.add.data

import android.net.Uri
import com.vinicius.instagram.common.base.RequestCallback
import com.vinicius.instagram.common.model.UserAuth

interface AddDataSource {

  fun createPost(userUUID: String, uri: Uri, caption: String, callback: RequestCallback<Boolean>) { throw UnsupportedOperationException() }

  fun fetchSession() : String { throw UnsupportedOperationException() }

}