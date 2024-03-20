package com.example.beaverclasshelpme.data

import com.example.beaverclasshelpme.Course
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BackendService {
    @GET("class")
    suspend fun getClassDetails(
        @Query("classCode") classCode: String,
        @Query("crn") crn: String,
        @Query("term") term: String
    ): Response<Course>

    @POST("fcm_token")
    suspend fun postFBMToken(
        @Body body: JsonObject
    ): Response<TokenResponse>

    @POST("cart")
    suspend fun postClassData(
        @Body body: JsonObject
    ): Response<TokenResponse>

    companion object {
        private const val BASE_URL = "https://mobile.thaiosu.com/api/"

        fun create(): BackendService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(BackendService::class.java)
        }

        fun createPOST(): BackendService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BackendService::class.java)
        }
    }
}