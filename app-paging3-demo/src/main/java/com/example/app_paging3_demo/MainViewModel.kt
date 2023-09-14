package com.example.app_paging3_demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {

    fun getPagingData(): Flow<PagingData<Repo>> {
        // 额外调用了一个 cachedIn() 函数，这是用于将服务器返回的数据在 viewModelScope 这个作用域内进行缓存，假如手机横竖屏发生了旋转导致 Activity 重新创建，Paging 3 就可以直接读取缓存中的数据，而不用重新发起网络请求了。
        val pagingData = Repository.getPagingData()

        return pagingData.flow.cachedIn(viewModelScope)
    }
}