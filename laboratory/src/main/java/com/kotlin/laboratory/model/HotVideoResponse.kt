package com.kotlin.laboratory.model

data class HotVideoResponse(
    val error_code: Int,
    val reason: String,
    val result: List<HotVideoInfo>
)