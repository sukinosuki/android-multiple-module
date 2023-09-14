package com.example.app_paging3_demo

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

const val TAG = "hanami"

class RepoPagingResource<T: Any>(private val gitHubService: Service) : PagingSource<Int, Repo>() {
    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        try {
            Log.i(TAG, "<RepoPagingResource> [load] params key ${params.key}")
            Log.i(TAG, "<RepoPagingResource> [load] params loadSize ${params.loadSize}")

            val page = params.key ?: 1

            val pageSize = params.loadSize

            delay(1000)

            val response = gitHubService.searchRepos(page, pageSize)


            val prevKey = if (page > 1) page - 1 else null

            val nextKey = if (response.items.isNotEmpty()) page + 1 else null

            if (page == 4) {
//                return LoadResult.Error(Error("自定义的错误"))
                return LoadResult.Page(emptyList(), prevKey, null)
            }

            Log.i(TAG, "<RepoPagingResource> [load] 请求结束")
            return LoadResult.Page(response.items, prevKey, nextKey)
        } catch (e: Exception) {
            Log.e(TAG, "<RepoPagingResource> [load] 请求失败: e ${e.message}")
            return LoadResult.Error(e)
        }
    }
}