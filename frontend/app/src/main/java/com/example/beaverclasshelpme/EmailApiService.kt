package com.example.beaverclasshelpme

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface EmailApiService {
    @Headers("Content-Type: application/json")
    @POST("api/draft")
    suspend fun fetchDraftEmail(@Body request:EmailDataRequest): EmailResponse
    companion object{
        fun create(): EmailApiService{
            return Retrofit.Builder()
                .baseUrl("https://mobile.thaiosu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EmailApiService::class.java)
        }
    }
}