package com.example.alexandr.trinitydigitaltest.di

import android.app.Application
import android.content.Context
import com.example.alexandr.trinitydigitaltest.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val mApplication: Application) {

    @Provides
    @ApplicationScope
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationScope
    fun provideContext(): Context {
        return mApplication
    }
}
