package com.example.beaverclasshelpme.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedClass(
    @PrimaryKey
    val crn: String,
    val className: String,
    val classCode: String
)
