package com.example.core.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ApplicationViewModel(application: Application) : AndroidViewModel(application) {

    val count = MutableLiveData(0)
    val isLogin = MutableLiveData(false)

    fun add() {
        val value = count.value?.plus(1) ?: 1

        count.value = value
    }

    fun login() {
//        isLogin.value = true
        isLogin.postValue(true)
    }
}