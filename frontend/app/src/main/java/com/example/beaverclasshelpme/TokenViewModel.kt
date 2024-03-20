package com.example.beaverclasshelpme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beaverclasshelpme.data.BackendService
import com.example.beaverclasshelpme.data.TokenRepository
import com.example.beaverclasshelpme.data.TokenResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class TokenViewModel : ViewModel() {
    private val repository = TokenRepository(BackendService.createPOST())

    private val _responseToken = MutableLiveData<TokenResponse?>(null)
    val responseToken: LiveData<TokenResponse?> = _responseToken

    private val _responseClass = MutableLiveData<TokenResponse?>(null)
    val responseClass: LiveData<TokenResponse?> = _responseClass
    fun submitToken(body: JsonObject) {
        viewModelScope.launch {
            val result = repository.addToken(body)

            _responseToken.value = result.getOrNull()
        }
    }

    fun submitClassData(body: JsonObject) {
        viewModelScope.launch {
            val resultData = repository.addClassData(body)

            _responseClass.value = resultData.getOrNull()
        }
    }
}