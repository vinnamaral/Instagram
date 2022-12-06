package com.vinicius.instagram.post.view

import android.net.Uri
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import com.vinicius.instagram.R
import com.vinicius.instagram.common.base.BaseFragment
import com.vinicius.instagram.common.base.DependencyInjector
import com.vinicius.instagram.databinding.FragmentGalleryBinding
import com.vinicius.instagram.post.Post
import com.vinicius.instagram.post.presenter.PostPresenter

class GalleryFragment : BaseFragment<FragmentGalleryBinding, Post.Presenter>(
  R.layout.fragment_gallery,
  FragmentGalleryBinding::bind
), Post.View {

  override lateinit var presenter: Post.Presenter

  private val adapter = PictureAdapter() { uri ->
    binding?.galleryImgSelected?.setImageURI(uri)
    binding?.galleryNested?.smoothScrollTo(0, 0)
    presenter.selectUri(uri)
  }

  override fun setupPresenter() {
    presenter = PostPresenter(this, DependencyInjector.postRepository(requireContext()))
  }

  override fun getMenu(): Int = R.menu.menu_send

  override fun setupViews() {
    binding?.galleryRv?.layoutManager = GridLayoutManager(requireContext(), 3)
    binding?.galleryRv?.adapter = adapter

    presenter.fetchPictures()
  }

  override fun showProgress(enabled: Boolean) {
    binding?.galleryProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
  }

  override fun displayFullPictures(posts: List<Uri>) {
    binding?.galleryTxtEmpty?.visibility = View.GONE
    binding?.galleryRv?.visibility = View.VISIBLE
    adapter.items = posts
    adapter.notifyDataSetChanged()
    binding?.galleryImgSelected?.setImageURI(posts.first())
    binding?.galleryNested?.smoothScrollTo(0, 0)
    presenter.selectUri(posts.first())
  }

  override fun displayEmptyPictures() {
    binding?.galleryTxtEmpty?.visibility = View.VISIBLE
    binding?.galleryRv?.visibility = View.GONE
  }

  override fun displayRequestFailure(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId) {
      R.id.action_send -> {
        setFragmentResult("takePhotoKey", bundleOf("uri" to presenter.getSelectedUri()))
      }
    }

    return super.onOptionsItemSelected(item)
  }

}