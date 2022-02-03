package com.kotlin.laboratory

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.laboratory.adapter.HotVideoListAdapter
import com.kotlin.laboratory.database.HotVideoInfoRepo
import com.kotlin.laboratory.databinding.ActivityMainBinding
import com.kotlin.laboratory.model.HotVideoInfo
import com.kotlin.laboratory.model.Status
import com.kotlin.laboratory.retrofit.RetrofitBuilder
import com.kotlin.laboratory.utils.ViewModelProviderFactory

class MainActivity : BaseActivity() {

    private lateinit var hotVideoViewModel: HotVideoViewModel
    private lateinit var adapter: HotVideoListAdapter

    private lateinit var repo: HotVideoInfoRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repo = HotVideoInfoRepo(application)

        initViews()
        initViewModels()

        addItemClickEvent()
        // addPullToRefreshEvent()
    }

    override fun onPostBaseActivityOnCreate(mainBinding: ActivityMainBinding) {
        setContentView(activityMainBinding.root)
    }

    private fun addPullToRefreshEvent() {
        activityMainBinding.refreshLayout.setOnRefreshListener {
            hotVideoViewModel.requestServer()
            activityMainBinding.refreshLayout.isRefreshing = false
        }
    }

    private fun addItemClickEvent() {
        adapter.setOnItemClickCallback {
            Toast.makeText(this@MainActivity, it.title, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViews() {
        val hotVideoListAdapter = HotVideoListAdapter()
        this.adapter = hotVideoListAdapter

        activityMainBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = hotVideoListAdapter

            val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(itemDecoration)
        }
    }

    private fun initViewModels() {
        val viewModelProviderFactory =
            ViewModelProviderFactory(RetrofitBuilder.createHotVideoApi(), repo)

        hotVideoViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(HotVideoViewModel::class.java)

        hotVideoViewModel.getDataSet()
            .observe(this) { resp ->
                when (resp.status) {
                    Status.LOADING -> {
                        showLoadingBar()
                    }
                    Status.SUCCESS -> {
                        dismissLoadingBar()
                        val videos: List<HotVideoInfo>? = resp.response?.result
                        if (videos != null) {
                            // refresh ui
                            adapter.refreshList(videos)
                        }
                    }
                    Status.ERROR -> {
                        dismissLoadingBar()
                    }
                }
            }
    }
}