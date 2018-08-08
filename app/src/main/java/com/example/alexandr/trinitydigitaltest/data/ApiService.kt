package com.example.alexandr.trinitydigitaltest.data

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val BASE_DEBUG_URL = "https://a11d.firebaseio.com/"
    }

    @GET("users.json")
    fun getUserList(): Observable<Response<List<UserModel>>>
}