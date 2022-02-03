package com.kotlin.laboratory.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlin.laboratory.databinding.LayoutItemBinding
import com.kotlin.laboratory.model.HotVideoInfo

class HotVideoListAdapter : RecyclerView.Adapter<HotVideoListAdapter.ViewHolder>() {

    private var videoList: List<HotVideoInfo> = arrayListOf()
    private lateinit var itemClickHandler: (HotVideoInfo) -> Unit

    private lateinit var layoutItemBinding: LayoutItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        layoutItemBinding = LayoutItemBinding.inflate(layoutInflater)
        return ViewHolder(layoutItemBinding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videoInfo = videoList[position]
        holder.bindData(videoInfo)
    }

    override fun getItemCount(): Int = videoList.size

    fun setOnItemClickCallback(clickHandler: (HotVideoInfo) -> Unit) {
        this.itemClickHandler = clickHandler
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(list: List<HotVideoInfo>) {
        this.videoList = list
        notifyDataSetChanged()
    }

    // ViewHolder
    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        init {
            item.setOnClickListener {
                val videoInfo = videoList[adapterPosition]
                itemClickHandler(videoInfo)
            }
        }

        fun bindData(info: HotVideoInfo) {
            layoutItemBinding.tvTitle.text = info.author
            layoutItemBinding.tvContent.text = info.title

            // image
            Glide.with(layoutItemBinding.ivIcon.context)
                .load(info.item_cover)
                .into(layoutItemBinding.ivIcon);

        }
    }

}