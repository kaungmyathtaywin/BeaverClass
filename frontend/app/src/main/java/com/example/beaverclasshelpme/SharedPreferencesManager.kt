package com.example.beaverclasshelpme

import android.content.Context

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

    fun saveData(key: String, value: String) {
        sharedPreferences.edit().apply {
            putString(key, value)
            apply()
        }
    }

    fun getData(key: String): String? = sharedPreferences.getString(key, null)

    fun clearData() {
        sharedPreferences.edit().clear().apply()
    }
}