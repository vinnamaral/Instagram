package com.vinicius.instagram.post.presenter

import android.net.Uri
import com.vinicius.instagram.post.Post
import com.vinicius.instagram.post.data.PostRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PostPresenter(
  private var view: Post.View?,
  private val repository: PostRepository
) : Post.Presenter, CoroutineScope {

  private var uri: Uri? = null

  private val job = Job()
  override val coroutineContext: CoroutineContext = job + Dispatchers.IO

  override fun fetchPictures() {
    // AQUI acontece a chamada na thread MAIN (UI)
    view?.showProgress(true)

    launch {
      // AQUI Dentro do launch, acontece a chamada paralela (corotina IO)
      val pictures = repository.fetchPictures()

      withContext(Dispatchers.Main) {
        // AQUI DENTRO executa de volta na MainThread
        if (pictures.isEmpty()) {
          view?.displayEmptyPictures()
        } else {
          view?.displayFullPictures(pictures)
        }
        view?.showProgress(false)
      }

    }
  }

  override fun selectUri(uri: Uri) {
    this.uri = uri
  }

  override fun getSelectedUri(): Uri? {
    return uri
  }

  override fun onDestroy() {
    job.cancel()
    view = null
  }

}