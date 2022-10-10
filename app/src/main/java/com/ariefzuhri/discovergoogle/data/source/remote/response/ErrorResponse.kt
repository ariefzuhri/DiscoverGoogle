package com.ariefzuhri.discovergoogle.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(

    @Json(name = "status")
    val status: String? = null,

    @Json(name = "code")
    val code: String? = null,

    @Json(name = "message")
    val message: String? = null,
)