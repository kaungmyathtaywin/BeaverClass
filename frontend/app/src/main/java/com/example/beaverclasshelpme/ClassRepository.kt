package com.example.beaverclasshelpme

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ClassRepository(private val apiService: BackendService, private val ioDispatcher: CoroutineDispatcher) {
    suspend fun getClassDetails(classCode: String, crn: String, term: String): Result<Course> =
        withContext(ioDispatcher) {
            try {
                val response = apiService.getClassDetails(classCode, crn, term)
                if (response.isSuccessful) {
                    Result.success(response.body() ?: throw Exception("No data"))
                } else {
                    Result.failure(Exception(response.errorBody()?.string() ?: "Unknown error"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}