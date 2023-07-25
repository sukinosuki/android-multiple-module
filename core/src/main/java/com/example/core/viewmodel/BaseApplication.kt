package com.example.core.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class BaseApplication : Application(), ViewModelStoreOwner {
//class BaseApplication : ViewModelStoreOwner {
    val applicationViewModel2 by lazy {
//        ViewModelProvider(BaseApplication.instance).get(ApplicationViewModel::class.java)
//        ApplicationViewModel2(this)
        ApplicationViewModel2()
    }

    private val appViewModelStore: ViewModelStore by lazy {
        ViewModelStore()
    }

    companion object {
        lateinit var instance: BaseApplication
//
//        //        lateinit var instance: BaseApplication
//        fun getInstance(): BaseApplication {
//            if (_instance == null) {
//                _instance = BaseApplication()
//            }
//
//            return _instance!!
//        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("HANAMI", "onCreate: core BaseApplication onCreate")
        instance = this
    }

    override fun getViewModelStore(): ViewModelStore {

        return appViewModelStore
    }
}