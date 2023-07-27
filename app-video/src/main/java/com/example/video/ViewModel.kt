package com.example.video

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel:ViewModel() {

    val visible = MutableLiveData(true)
}