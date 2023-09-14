package com.example.app_paging3_demo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.app_paging3_demo.databinding.RepoItemLayoutBinding

class RepoAdapter : PagingDataAdapter<Repo, RepoAdapter.ViewHolder>(diffCallback) {
    class ViewHolder(val binding: RepoItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    fun interface OnClickListener {
        fun onClick(value: Repo, position: Int)
    }

    var clickListener: OnClickListener? = null

    fun setOnClickListener(l: OnClickListener) {
        clickListener = l
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = getItem(position)
        Log.i(TAG, "<RepoAdapter> [onBindViewHolder]: $position")

        repo?.let {
            with(holder.binding) {
                if (!root.hasOnClickListeners()) {
                    Log.i(TAG, "onBindViewHolder: 没有点击事件, $position")
                    root.setOnClickListener {
                        clickListener?.onClick(repo, position)
                    }
                }

                nameText.text = repo.name
                descriptionText.text = repo.name
                starCountText.text = repo.starCount.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RepoItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        Log.i(TAG, "<RepoAdapter> [onCreateViewHolder]: ")
        return ViewHolder(binding)
    }
}

val diffCallback = object : DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.id == newItem.id
    }
}