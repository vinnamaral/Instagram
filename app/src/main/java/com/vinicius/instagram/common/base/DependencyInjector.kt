package com.vinicius.instagram.common.base

import android.content.Context
import com.vinicius.instagram.add.data.AddFakeRemoteDataSource
import com.vinicius.instagram.add.data.AddLocalDataSource
import com.vinicius.instagram.add.data.AddRepository
import com.vinicius.instagram.home.data.FeedMemoryCache
import com.vinicius.instagram.home.data.HomeDataSourceFactory
import com.vinicius.instagram.home.data.HomeRepository
import com.vinicius.instagram.login.data.FakeDataSource
import com.vinicius.instagram.login.data.LoginRepository
import com.vinicius.instagram.post.data.PostLocalDataSource
import com.vinicius.instagram.post.data.PostRepository
import com.vinicius.instagram.profile.data.PostListMemoryCache
import com.vinicius.instagram.profile.data.ProfileDataSourceFactory
import com.vinicius.instagram.profile.data.ProfileMemoryCache
import com.vinicius.instagram.profile.data.ProfileRepository
import com.vinicius.instagram.register.data.FakeRegisterDataSource
import com.vinicius.instagram.register.data.RegisterRepository
import com.vinicius.instagram.search.data.SearchFakeRemoteDataSource
import com.vinicius.instagram.search.data.SearchRepository
import com.vinicius.instagram.splash.data.FakeLocalDataSource
import com.vinicius.instagram.splash.data.SplashRepository

object DependencyInjector {

  fun splashRepository() : SplashRepository {
    return SplashRepository(FakeLocalDataSource())
  }

  fun loginRepository() : LoginRepository {
    return LoginRepository(FakeDataSource())
  }

  fun registerEmailRepository() : RegisterRepository {
    return RegisterRepository(FakeRegisterDataSource())
  }

  fun searchRepository() : SearchRepository {
    return SearchRepository(SearchFakeRemoteDataSource())
  }

  fun profileRepository() : ProfileRepository {
    return ProfileRepository(ProfileDataSourceFactory(ProfileMemoryCache, PostListMemoryCache))
  }

  fun homeRepository() : HomeRepository {
    return HomeRepository(HomeDataSourceFactory(FeedMemoryCache))
  }

  fun addRepository() : AddRepository {
    return AddRepository(AddFakeRemoteDataSource(), AddLocalDataSource())
  }

  fun postRepository(context: Context) : PostRepository {
    return PostRepository(PostLocalDataSource(context))
  }

}