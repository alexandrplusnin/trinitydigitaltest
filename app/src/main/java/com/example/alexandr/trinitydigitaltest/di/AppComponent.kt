package com.example.alexandr.trinitydigitaltest.di

import com.example.alexandr.trinitydigitaltest.data.ApiService
import com.example.alexandr.trinitydigitaltest.di.scopes.ApplicationScope
import dagger.Component

@Component(modules = [(AppModule::class), (RetrofitModule::class)])
@ApplicationScope
interface AppComponent {
    fun apiService(): ApiService
}
