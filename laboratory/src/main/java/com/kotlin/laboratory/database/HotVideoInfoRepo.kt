package com.kotlin.laboratory.database

import android.app.Application
import com.kotlin.laboratory.model.HotVideoInfo

class HotVideoInfoRepo(application: Application) {

    private val hotVideoInfoDao: HotVideoInfoDao

    init {
        val database = HotVideoInfoDatabase.getInstance(application.applicationContext)
        hotVideoInfoDao = database.getHotVideoInfoDao()
    }

    suspend fun queryHotVideoInfos(): List<HotVideoInfo> =
        hotVideoInfoDao.queryVideoInfoList()

    suspend fun addNewHotVideoInfo(info: HotVideoInfo) =
        hotVideoInfoDao.insertVideoInfo(info)
}