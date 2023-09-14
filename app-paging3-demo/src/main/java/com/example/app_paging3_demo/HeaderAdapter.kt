package com.example.app_paging3_demo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app_paging3_demo.databinding.HeaderItemLayoutBinding

class HeaderAdapter : LoadStateAdapter<HeaderAdapter.ViewHolder>() {

    class ViewHolder(val binding: HeaderItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        //

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding =
            HeaderItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        // 默认只有Loading | Error时展示footer, 当数据加载完成时, footer会消失
//        return super.displayLoadStateAsItem(loadState)

//        return loadState is LoadState.Loading || loadState is LoadState.Error || loadState is LoadState.NotLoading
        // 返回true表示Loading | Error 或其它状态时都展示 footer
        return true
    }
}