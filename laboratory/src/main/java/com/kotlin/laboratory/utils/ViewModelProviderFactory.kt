package com.kotlin.laboratory.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.laboratory.HotVideoViewModel
import com.kotlin.laboratory.database.HotVideoInfoRepo
import com.kotlin.laboratory.retrofit.HotVideoApi

class ViewModelProviderFactory(
    private val hotVideoApi: HotVideoApi,
    private val hotVideoInfoRepo: HotVideoInfoRepo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HotVideoViewModel::class.java)) {
            return HotVideoViewModel(hotVideoApi, hotVideoInfoRepo) as T
        }
        throw IllegalArgumentException("illegal argument exception")
    }
}