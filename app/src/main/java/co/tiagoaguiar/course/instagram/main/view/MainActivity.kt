package com.vinicius.instagram.main.view

import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.vinicius.instagram.R
import com.vinicius.instagram.post.view.AddFragment
import com.vinicius.instagram.common.extension.replaceFragment
import com.vinicius.instagram.databinding.ActivityMainBinding
import com.vinicius.instagram.home.view.HomeFragment
import com.vinicius.instagram.main.LogoutListener
import com.vinicius.instagram.profile.view.ProfileFragment
import com.vinicius.instagram.search.view.SearchFragment
import com.vinicius.instagram.splash.view.SplashActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
  AddFragment.AddListener,
  SearchFragment.SearchListener,
  ProfileFragment.FollowListener,
  LogoutListener {

  private lateinit var binding: ActivityMainBinding

  private lateinit var homeFragment: HomeFragment
  private lateinit var searchFragment: Fragment
  private lateinit var addFragment: Fragment
  private lateinit var profileFragment: ProfileFragment
  private var currentFragment: Fragment? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)

    setContentView(binding.root)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
      when(resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
        Configuration.UI_MODE_NIGHT_YES -> {
          window.statusBarColor = ContextCompat.getColor(this, R.color.black)
          binding.mainImgLogo.imageTintList = ColorStateList.valueOf(Color.WHITE)
        }
        Configuration.UI_MODE_NIGHT_NO -> {
          window.insetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
          )
          window.statusBarColor = ContextCompat.getColor(this, R.color.gray)
        }
      }

    }

    setSupportActionBar(binding.mainToolbar)

    supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_insta_camera)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.title = ""

    homeFragment = HomeFragment()
    searchFragment = SearchFragment()
    addFragment = AddFragment()
    profileFragment = ProfileFragment()

    binding.mainBottomNav.setOnNavigationItemSelectedListener(this)
    binding.mainBottomNav.selectedItemId = R.id.menu_bottom_home
  }

  private fun setScrollToolbarEnabled(enabled: Boolean) {
    val params = binding.mainToolbar.layoutParams as AppBarLayout.LayoutParams
    val coordinatorParams = binding.mainAppbar.layoutParams as CoordinatorLayout.LayoutParams

    if (enabled) {
      params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
      coordinatorParams.behavior = AppBarLayout.Behavior()
    } else {
      params.scrollFlags = 0
      coordinatorParams.behavior = null
    }
    binding.mainAppbar.layoutParams = coordinatorParams
  }

  override fun goToProfile(uuid: String) {
    val fragment = ProfileFragment().apply {
      arguments = Bundle().apply {
        putString(ProfileFragment.KEY_USER_ID, uuid)
      }
    }
    supportFragmentManager.beginTransaction().apply {
      replace(R.id.main_fragment, fragment, fragment.javaClass.simpleName + "detail")
      addToBackStack(null)
      commit()
    }
  }

  override fun followUpdated() {
    homeFragment.presenter.clear()

    if (supportFragmentManager.findFragmentByTag(profileFragment.javaClass.simpleName) != null) {
      profileFragment.presenter.clear()
    }
  }

  override fun logout() {
    if (supportFragmentManager.findFragmentByTag(profileFragment.javaClass.simpleName) != null) {
      profileFragment.presenter.clear()
    }

    homeFragment.presenter.clear()
    homeFragment.presenter.logout()

    val intent = Intent(baseContext, SplashActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    var scrollToolbarEnabled = false

    when (item.itemId) {
      R.id.menu_bottom_home -> {
        if (currentFragment == homeFragment) return false
        currentFragment = homeFragment
      }
      R.id.menu_bottom_search -> {
        if (currentFragment == searchFragment) return false
        currentFragment = searchFragment
        scrollToolbarEnabled = false
      }
      R.id.menu_bottom_add -> {
        if (currentFragment == addFragment) return false
        currentFragment = addFragment
        scrollToolbarEnabled = false
      }
      R.id.menu_bottom_profile -> {
        if (currentFragment == profileFragment) return false
        currentFragment = profileFragment
        scrollToolbarEnabled = true
      }
    }

    setScrollToolbarEnabled(scrollToolbarEnabled)

    currentFragment?.let {
      replaceFragment(R.id.main_fragment, it)
    }

    return true
  }

  override fun onPostCreated() {
    homeFragment.presenter.clear()

    if (supportFragmentManager.findFragmentByTag(profileFragment.javaClass.simpleName) != null) {
      profileFragment.presenter.clear()
    }

    binding.mainBottomNav.selectedItemId = R.id.menu_bottom_home
  }

}