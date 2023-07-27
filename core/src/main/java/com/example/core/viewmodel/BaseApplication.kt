package com.example.core.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.example.core.CrashHandler

class BaseApplication : Application(), ViewModelStoreOwner {
//class BaseApplication : ViewModelStoreOwner {
//    val applicationViewModel2 by lazy {
////        ViewModelProvider(BaseApplication.instance).get(ApplicationViewModel::class.java)
////        ApplicationViewModel2(this)
////            val sp = getSharedPreferences("data",Context.MODE_PRIVATE)
////        val count  = sp.getInt("count", 0)
////        Log.i("hanami", "onCreate: sp获取到了 count $count")
////        applicationViewModel2.count.postValue(count)
//        ApplicationViewModel2()
//    }
    val applicationViewModel2 by lazy {
        ApplicationViewModel2(this)
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



        Thread.setDefaultUncaughtExceptionHandler(CrashHandler(this))

        instance = this
    }

//    override fun getViewModelStore(): ViewModelStore {
//
//        return appViewModelStore
//    }

    override val viewModelStore: ViewModelStore
        get() = appViewModelStore
}