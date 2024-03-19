package com.example.beaverclasshelpme

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BackendService {

    companion object {
        private const val BASE_URL = "http://149.28.13.48/api/"

        fun create(): BackendService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(BackendService::class.java)
        }

    }
}