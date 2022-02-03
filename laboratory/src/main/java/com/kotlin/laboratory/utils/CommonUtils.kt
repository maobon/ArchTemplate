package com.kotlin.laboratory.utils

import android.util.Log

const val TAG = "log"

const val API_KEY = "0301e67ab7ae03e55e40d6951d9e5027"

fun debug(string: String) {
    Log.d(TAG, string)
}