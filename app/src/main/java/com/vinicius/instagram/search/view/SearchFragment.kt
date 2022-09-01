package com.vinicius.instagram.search.view

import android.app.SearchManager
import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinicius.instagram.R
import com.vinicius.instagram.common.base.BaseFragment
import com.vinicius.instagram.common.base.DependencyInjector
import com.vinicius.instagram.common.model.UserAuth
import com.vinicius.instagram.databinding.FragmentSearchBinding
import com.vinicius.instagram.search.Search
import com.vinicius.instagram.search.presenter.SearchPresenter

class SearchFragment : BaseFragment<FragmentSearchBinding, Search.Presenter>(
  R.layout.fragment_search,
  FragmentSearchBinding::bind
), Search.View {

  override lateinit var presenter: Search.Presenter

  private val adapter by lazy { SearchAdapter(onItemClicked) }

  private var searchListener: SearchListener? = null

  override fun onAttach(context: Context) {
    super.onAttach(context)
    if (context is SearchListener) {
      searchListener = context
    }
  }

  override fun setupViews() {
    binding?.searchRv?.layoutManager = LinearLayoutManager(requireContext())
    binding?.searchRv?.adapter = adapter
  }

  override fun setupPresenter() {
    val repository = DependencyInjector.searchRepository()
    presenter = SearchPresenter(this, repository)
  }

  private val onItemClicked: (String) -> Unit = { uuid ->
    // depois do click
    searchListener?.goToProfile(uuid)
  }

  override fun getMenu() = R.menu.menu_search

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)

    val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
    val searchView = (menu.findItem(R.id.menu_search).actionView as SearchView)
    searchView.apply {
      setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
      setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
          return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
          if (newText?.isNotEmpty() == true) {
            presenter.fetchUsers(newText)
            return true
          }
          return false
        }
      })
    }

  }

  override fun showProgress(enabled: Boolean) {
    binding?.searchProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
  }

  override fun displayFullUsers(users: List<UserAuth>) {
    binding?.searchTxtEmpty?.visibility = View.GONE
    binding?.searchRv?.visibility = View.VISIBLE
    adapter.items = users
    adapter.notifyDataSetChanged()
  }

  override fun displayEmptyUsers() {
    binding?.searchTxtEmpty?.visibility = View.VISIBLE
    binding?.searchRv?.visibility = View.GONE
  }

  interface SearchListener {
    fun goToProfile(uuid: String)
  }

}