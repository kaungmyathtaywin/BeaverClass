package com.example.beaverclasshelpme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.beaverclasshelpme.data.AppDatabase
import com.example.beaverclasshelpme.data.SavedClass
import com.example.beaverclasshelpme.data.SavedClassRepository
import kotlinx.coroutines.launch

class SavedClassViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = SavedClassRepository(
        AppDatabase.getInstance(application).savedClassDao()
    )

    val savedClasses = repository.getAllSavedClasses().asLiveData()

    fun addNewClass(myClass: SavedClass) {
        viewModelScope.launch {
            repository.insertNewClass(myClass)
        }
    }

    fun removeSavedClass(myClass: SavedClass) {
        viewModelScope.launch {
            repository.deleteSavedClass(myClass)
        }
    }

    fun removeAllClasses() {
        viewModelScope.launch {
            repository.deleteAllClasses()
        }
    }
}