package com.example.beaverclasshelpme

import com.google.gson.Gson
import java.lang.Exception

class EmailRepository(private val emailApiService: EmailApiService){
    suspend fun fetchDraftEmail(requestData:EmailDataRequest):Result<EmailResponse>{
        return try {
            val response = emailApiService.fetchDraftEmail(requestData)
            Result.success(response)
        } catch (exception:Exception){
            Result.failure(exception)
        }
    }
}