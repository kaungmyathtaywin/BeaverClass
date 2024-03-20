package com.example.beaverclasshelpme.data

import com.squareup.moshi.Json

data class TokenBody(
    val email: String,
    val fcm_token: String
)