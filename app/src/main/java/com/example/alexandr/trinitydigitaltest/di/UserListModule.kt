package com.example.alexandr.trinitydigitaltest.di

import android.app.Activity
import android.content.Context
import com.example.alexandr.trinitydigitaltest.data.ApiService
import com.example.alexandr.trinitydigitaltest.ui.user.UserListPresenter
import com.example.alexandr.trinitydigitaltest.ui.user.UserListView
import com.example.alexandr.trinitydigitaltest.di.scopes.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class UserListModule(private val activity: Activity, private val view: UserListView) {
    @FragmentScope
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @FragmentScope
    @Provides
    fun provideView(): UserListView {
        return view
    }

    @FragmentScope
    @Provides
    fun providePresenter(view: UserListView, apiService: ApiService): UserListPresenter {
        return UserListPresenter(view, apiService)
    }
}