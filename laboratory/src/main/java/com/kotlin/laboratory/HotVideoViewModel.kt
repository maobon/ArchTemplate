package com.kotlin.laboratory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.laboratory.database.HotVideoInfoRepo
import com.kotlin.laboratory.model.HotVideoInfo
import com.kotlin.laboratory.model.HotVideoResponse
import com.kotlin.laboratory.model.ServerResponse
import com.kotlin.laboratory.retrofit.HotVideoApi
import com.kotlin.laboratory.utils.API_KEY
import com.kotlin.laboratory.utils.debug
import kotlinx.coroutines.launch

class HotVideoViewModel(private val hotVideoApi: HotVideoApi, private val repo: HotVideoInfoRepo) :
    ViewModel() {

    private val dataSet: MutableLiveData<ServerResponse<HotVideoResponse>> by lazy {
        // requestServer() // must be async // todo why write here will request server twice ??
        MutableLiveData()
    }

    init {
        requestServer()
    }

    fun requestServer(videoType: String = "hot_video") {

        viewModelScope.launch {
            dataSet.postValue(ServerResponse.loading())

            val params = hashMapOf("key" to API_KEY)
            params["type"] = videoType
            params["size"] = "50"

            try {
                val queryDouYinBillboard = hotVideoApi.queryDouYinBillboard(params)
                if (queryDouYinBillboard.error_code == 0) {
                    dataSet.postValue(ServerResponse.success(queryDouYinBillboard))

                    // cache in database
                    val hotVideoInfos: List<HotVideoInfo> = queryDouYinBillboard.result;
                    hotVideoInfos.forEach { info ->
                        repo.addNewHotVideoInfo(info)
                    }
                } else {
                    dataSet.postValue(ServerResponse.error(queryDouYinBillboard.reason))
                }
            } catch (e: Exception) {
                debug("caonima")
                e.printStackTrace()

                val cachedList: List<HotVideoInfo> = repo.queryHotVideoInfos()
                val response: HotVideoResponse =
                    HotVideoResponse(0, "offline_cached_data", cachedList)

                dataSet.postValue(ServerResponse.success(response))
            }

        }
    }

    fun getDataSet(): LiveData<ServerResponse<HotVideoResponse>> = dataSet

}