package com.vinicius.instagram.home.data

import com.vinicius.instagram.common.base.RequestCallback
import com.vinicius.instagram.common.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class FireHomeDataSource : HomeDataSource {

  override fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>) {
    val uid = FirebaseAuth.getInstance().uid ?: throw RuntimeException("Usuário não encontrado")
    FirebaseFirestore.getInstance()
      .collection("/feeds") // [ varios feeds ]
      .document(uid) // [ um usuario (corrente) ]
      .collection("posts") // [ posts dos outros usuario ]
      .orderBy("timestamp", Query.Direction.DESCENDING)
      .get()
      .addOnSuccessListener { res ->
        val feed = mutableListOf<Post>()
        val documents = res.documents
        for (document in documents) {
          val post = document.toObject(Post::class.java)
          post?.let { feed.add(it) }
        }
        callback.onSuccess(feed)
      }
      .addOnFailureListener { exception ->
        callback.onFailure(exception.message ?: "Erro ao carregar o feed")
      }
      .addOnCompleteListener {
        callback.onComplete()
      }
  }

  override fun logout() {
    FirebaseAuth.getInstance().signOut()
  }

}