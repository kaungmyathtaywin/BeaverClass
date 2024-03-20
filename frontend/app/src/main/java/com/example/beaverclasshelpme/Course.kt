package com.example.beaverclasshelpme

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Course(
    val classCode: String,
    val classTitle: String,
    val crn: String,
    val maxEntrl: Int,
    val actualEnrl: Int,
    val meets: String,
    val campus: String
)