package com.example.alexandr.trinitydigitaltest

import android.app.Application
import com.example.alexandr.trinitydigitaltest.data.ApiService
import com.example.alexandr.trinitydigitaltest.di.AppComponent
import com.example.alexandr.trinitydigitaltest.di.AppModule
import com.example.alexandr.trinitydigitaltest.di.DaggerAppComponent
import com.example.alexandr.trinitydigitaltest.di.RetrofitModule
import timber.log.Timber

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        buildComponent()
    }

    private fun buildComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .retrofitModule(RetrofitModule(ApiService.BASE_DEBUG_URL))
                .build()
    }
}