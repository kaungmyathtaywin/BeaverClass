package com.example.beaverclasshelpme

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ClassViewModel(
    private val repository: ClassRepository
): ViewModel() {

    private val _courseData = MutableLiveData<Course?>()
    val courseData: LiveData<Course?> = _courseData

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage


    fun loadClassData(classCode: String, crn: String, term: String) {
        viewModelScope.launch {
            try {
                val response = repository.getClassDetails(classCode, crn, term)
                Log.d("ClassViewModel", "response: $response")
                _courseData.value = response.getOrThrow()
                _errorMessage.value = response.exceptionOrNull()?.message
            } catch (e: Exception) {
                _courseData.value = null
            }
        }
    }
}