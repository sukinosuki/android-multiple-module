package com.example.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CoreViewModel : ViewModel() {

    val name = MutableLiveData("empty")

    fun changeName(_name: String) {
        name.postValue(_name)
    }
}