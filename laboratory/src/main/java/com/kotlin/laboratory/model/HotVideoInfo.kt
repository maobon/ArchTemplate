package com.kotlin.laboratory.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_info")
data class HotVideoInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String,
    val comment_count: Int,
    val digg_count: Int,
    val hot_value: Int,
    val hot_words: String,
    val item_cover: String,
    val play_count: Int,
    val share_url: String,
    val title: String
)

//data class HotVideoInfo(
//    val author: String,
//    val comment_count: Int,
//    val digg_count: Int,
//    val hot_value: Int,
//    val hot_words: String,
//    val item_cover: String,
//    val play_count: Int,
//    val share_url: String,
//    val title: String
//)