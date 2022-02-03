package com.kotlin.laboratory.retrofit

import com.kotlin.laboratory.model.HotVideoResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface HotVideoApi {

    @GET("fapig/douyin/billboard")
    suspend fun queryDouYinBillboard(@QueryMap params: HashMap<String, String>): HotVideoResponse

}