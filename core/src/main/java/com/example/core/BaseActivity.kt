package com.example.core

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
//import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.core.viewmodel.ApplicationViewModel
import com.example.core.viewmodel.ApplicationViewModel2
import com.example.core.viewmodel.BaseApplication

//import android.os.Bundle
//
//class CoreActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_core)
//    }
//}

open class BaseActivity : AppCompatActivity() {

    val vm by lazy {
        BaseApplication.instance.applicationViewModel2
    }

//    val vm2 by viewModels<ApplicationViewModel2>()

//
//    val vm2 by lazy {
//        ApplicationViewModel()
//    }
//
//    val vm3 by lazy {
//        ViewModelProvider(this).get(ApplicationViewModel::class.java)
//    }
}