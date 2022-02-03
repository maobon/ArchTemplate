package com.kotlin.laboratory.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kotlin.laboratory.model.HotVideoInfo

@Dao
interface HotVideoInfoDao {

    @Query("SELECT * FROM video_info")
    suspend fun queryVideoInfoList(): List<HotVideoInfo>

    @Insert
    suspend fun insertVideoInfo(hotVideoInfo: HotVideoInfo)

}