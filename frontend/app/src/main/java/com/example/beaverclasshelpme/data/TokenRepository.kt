package com.example.beaverclasshelpme.data

import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TokenRepository(
    private val service: BackendService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun addToken(body: JsonObject): Result<TokenResponse> =
        withContext(ioDispatcher) {
            try {
                val response = service.postFBMToken(body)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(it)
                    } ?: Result.failure(Exception("Empty response body"))
                } else {
                    Result.failure(Exception("HTTP error code: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}