package com.example.alexandr.trinitydigitaltest.di

import com.example.alexandr.trinitydigitaltest.ui.user.UserListFragment
import com.example.alexandr.trinitydigitaltest.di.scopes.FragmentScope
import dagger.Component

@Component(modules = [(UserListModule::class)], dependencies = [(AppComponent::class)])
@FragmentScope
interface UserListComponent {
    fun inject(fragment: UserListFragment)
}