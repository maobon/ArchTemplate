package com.kotlin.laboratory

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.laboratory.databinding.ActivityMainBinding

abstract class BaseActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        onPostBaseActivityOnCreate(activityMainBinding)
    }

    abstract fun onPostBaseActivityOnCreate(mainBinding: ActivityMainBinding)

    fun showLoadingBar(){
        activityMainBinding.progressBar.apply {
            visibility = View.VISIBLE
        }
    }

    fun dismissLoadingBar(){
        activityMainBinding.progressBar.apply {
            visibility = View.GONE
        }
    }
}