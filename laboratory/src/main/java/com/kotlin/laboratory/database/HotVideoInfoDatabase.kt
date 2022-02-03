package com.kotlin.laboratory.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kotlin.laboratory.model.HotVideoInfo

@Database(entities = [HotVideoInfo::class], version = 1, exportSchema = false)
abstract class HotVideoInfoDatabase : RoomDatabase() {

    abstract fun getHotVideoInfoDao(): HotVideoInfoDao

    companion object {

        private var INSTANCE: HotVideoInfoDatabase? = null

        fun getInstance(context: Context): HotVideoInfoDatabase {
            val temp = INSTANCE
            if (temp != null) return temp

            synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context,
                        HotVideoInfoDatabase::class.java,
                        "hot_video_info.db"
                    ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}