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

    private val _response = MutableLiveData<TokenResponse?>(null)
    val response: LiveData<TokenResponse?> = _response

    fun submitToken(body: JsonObject) {
        viewModelScope.launch {
            val result = repository.addToken(body)

            _response.value = result.getOrNull()
        }
    }
}