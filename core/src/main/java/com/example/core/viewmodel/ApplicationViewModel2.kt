package com.example.core.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//class ApplicationViewModel2(application: Application) : AndroidViewModel(application) {
class ApplicationViewModel2 : ViewModel() {

    val count = MutableLiveData(0)
    val isLogin = MutableLiveData(false)

    fun add() {
        val value = count.value ?: 0

        count.postValue(value + 1)
    }

    fun login() {
//        isLogin.value = true
        isLogin.postValue(true)
    }
}