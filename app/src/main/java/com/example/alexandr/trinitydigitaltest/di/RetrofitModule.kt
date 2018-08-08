package com.example.alexandr.trinitydigitaltest.di

import android.content.Context
import com.example.alexandr.trinitydigitaltest.data.ApiService
import com.example.alexandr.trinitydigitaltest.data.ErrorHandlingInterceptor
import com.example.alexandr.trinitydigitaltest.di.scopes.ApplicationScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule(private val mBaseUrl: String) {

    companion object {
        const val DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm"
    }

    @Provides
    @ApplicationScope
    internal fun provideGson(): Gson {
        return GsonBuilder().setDateFormat(DATE_TIME_FORMAT).create()
    }

    @Provides
    @ApplicationScope
    internal fun provideOkHttpClient(context: Context): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(ErrorHandlingInterceptor())
                .addInterceptor(loggingInterceptor)
                .build()
    }

    @Provides
    @ApplicationScope
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @ApplicationScope
    internal fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
