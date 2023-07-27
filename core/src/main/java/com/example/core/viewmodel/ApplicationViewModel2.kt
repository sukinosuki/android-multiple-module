package com.example.core.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope // androidx.activity:activity-ktx | androidx.fragment:fragment-ktx
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//class ApplicationViewModel2(application: Application) : AndroidViewModel(application) {
class ApplicationViewModel2(application: Application) : AndroidViewModel(application) {

    val count = MutableLiveData<Int>()
    val isLogin = MutableLiveData(false)

    init {
        val sp = application.getSharedPreferences("data2", Context.MODE_PRIVATE)
        val _count  = sp.getInt("count", 0)
        Log.i("hanami", "onCreate: sp获取到了 count $_count")
        count.postValue(_count)
    }

    fun add() {
        viewModelScope.launch(Dispatchers.Main) {
            delay(1 *1000)
            val value = count.value ?: 0

            count.postValue(value + 1)
        }
    }

    fun login() {
//        isLogin.value = true
        isLogin.postValue(true)
    }
}