package com.kotlin.laboratory.model

data class ServerResponse<T>(
    val status: Status,
    val message: String?,
    val response: T?
) {

    // in Java: static method create this instance
    companion object {

        fun <T> loading(): ServerResponse<T> = ServerResponse(Status.LOADING, null, null)

        fun <T> error(message: String): ServerResponse<T> =
            ServerResponse(Status.ERROR, message, null)

        fun <T> success(response: T): ServerResponse<T> =
            ServerResponse(Status.SUCCESS, null, response)
    }

}
