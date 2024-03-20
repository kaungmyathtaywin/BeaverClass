package com.example.beaverclasshelpme

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DraftEmailViewModel(private val emailRepository: EmailRepository):ViewModel() {
    private val _emailText = MutableLiveData<String>()
    val emailText: LiveData<String> = _emailText

    fun fetchDraftEmail(emailRequest: EmailDataRequest) {
        viewModelScope.launch {
            val requestData = EmailDataRequest(
                name = emailRequest.name,
                email = emailRequest.email,
                professorName = emailRequest.professorName,
                className = emailRequest.className,
                classId = emailRequest.classId
            )
            emailRepository.fetchDraftEmail(requestData).onSuccess { response ->
                _emailText.postValue(response.text)
            }.onFailure { exception ->
                // Handle failure, e.g., update the UI to show an error message
            }
        }
    }

}