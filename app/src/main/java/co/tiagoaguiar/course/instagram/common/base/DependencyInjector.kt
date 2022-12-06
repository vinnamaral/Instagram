package com.vinicius.instagram.common.base

import android.content.Context
import com.vinicius.instagram.add.data.AddFakeRemoteDataSource
import com.vinicius.instagram.add.data.AddLocalDataSource
import com.vinicius.instagram.add.data.AddRepository
import com.vinicius.instagram.add.data.FireAddDataSource
import com.vinicius.instagram.home.data.FeedMemoryCache
import com.vinicius.instagram.home.data.HomeDataSourceFactory
import com.vinicius.instagram.home.data.HomeRepository
import com.vinicius.instagram.login.data.FireLoginDataSource
import com.vinicius.instagram.login.data.LoginRepository
import com.vinicius.instagram.post.data.PostLocalDataSource
import com.vinicius.instagram.post.data.PostRepository
import com.vinicius.instagram.profile.data.PostListMemoryCache
import com.vinicius.instagram.profile.data.ProfileDataSourceFactory
import com.vinicius.instagram.profile.data.ProfileMemoryCache
import com.vinicius.instagram.profile.data.ProfileRepository
import com.vinicius.instagram.register.data.FireRegisterDataSource
import com.vinicius.instagram.register.data.RegisterRepository
import com.vinicius.instagram.search.data.FireSearchDataSource
import com.vinicius.instagram.search.data.SearchRepository
import com.vinicius.instagram.splash.data.FireSplashDataSource
import com.vinicius.instagram.splash.data.SplashRepository

object DependencyInjector {

  fun splashRepository() : SplashRepository {
    return SplashRepository(FireSplashDataSource())
  }

  fun loginRepository() : LoginRepository {
    return LoginRepository(FireLoginDataSource())
  }

  fun registerEmailRepository() : RegisterRepository {
    return RegisterRepository(FireRegisterDataSource())
  }

  fun searchRepository() : SearchRepository {
    return SearchRepository(FireSearchDataSource())
  }

  fun profileRepository() : ProfileRepository {
    return ProfileRepository(ProfileDataSourceFactory(ProfileMemoryCache, PostListMemoryCache))
  }

  fun homeRepository() : HomeRepository {
    return HomeRepository(HomeDataSourceFactory(FeedMemoryCache))
  }

  fun addRepository() : AddRepository {
    return AddRepository(FireAddDataSource(), AddLocalDataSource())
  }

  fun postRepository(context: Context) : PostRepository {
    return PostRepository(PostLocalDataSource(context))
  }

}