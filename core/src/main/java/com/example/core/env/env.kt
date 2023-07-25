package com.example.core.env

import android.util.Log
import com.example.core.BuildConfig

object Env {

    var name = "empty"

    var isDevelopment: Boolean = false
    var env: String = ""

    init {
        val _env = BuildConfig.ENV

        Log.i("HANAMI", "env: $_env")
        Log.i("HANAMI", "build type: ${BuildConfig.BUILD_TYPE}")
//        Log.i("HANAMI", "application id: ${BuildConfig.APPLICATION_ID}")
        Log.i("HANAMI", "debug: ${BuildConfig.DEBUG}")

        isDevelopment = _env == "development"
        env = _env
    }
}