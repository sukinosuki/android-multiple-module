package com.example.app_paging3_demo

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

object Repository {

    private const val PAGE_SIZE = 10

    val githubService: Service by lazy {
        Service.github
    }

    fun getPagingData(): Pager<Int, Repo> {
        Log.i(TAG, "<Repository> [getPagingData]: start")

        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,// 每页多少个条目；必填
                prefetchDistance = 1,//预加载下一页的距离，滑动到倒数第几个条目就加载下一页，无缝加载（可选）默认值是pageSize
                enablePlaceholders = true,//是否启用条目占位，当条目总数量确定的时候；列表一次性展示所有条目，但是没有数据；在adapter的onBindViewHolder里面绑定数据时候，是空数据，判断是空数据展示对应的占位item；可选，默认开启。
                initialLoadSize = PAGE_SIZE,
            ),
            pagingSourceFactory = {
               val source = RepoPagingResource<Repo>(githubService)

                return@Pager source
            }
        )
    }
}