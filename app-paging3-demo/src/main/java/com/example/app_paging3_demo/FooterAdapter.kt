package com.example.app_paging3_demo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app_paging3_demo.databinding.FooterItemLayoutBinding

class FooterAdapter(val retry: () -> Unit) : LoadStateAdapter<FooterAdapter.ViewHolder>() {
    class ViewHolder(val binding: FooterItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    // 有个问题是渲染第一页数据时, footer也会展示
    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
//        loadState.endOfPaginationReached
        Log.i(TAG, "onBindViewHolder: 绑定footer $loadState, count: $itemCount")

//        if (itemCount <= 10) {
//            holder.binding.root.isVisible = false
//            return
//        }
//
//        holder.binding.root.isVisible = true
        holder.binding.progressBar.isVisible = loadState is LoadState.Loading

        holder.binding.retryButton.isVisible = loadState is LoadState.Error

        holder.binding.loadCompletedText.isVisible =
            loadState is LoadState.NotLoading && loadState.endOfPaginationReached
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding =
            FooterItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.retryButton.setOnClickListener {
            retry()
        }

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