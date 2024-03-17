package com.example.beaverclasshelpme

data class CourseDetail(
    val crn: String,
    val campus: String,
    val meets: String,
    val maxEnrl: Int,
    val actualEnrl: Int
)