package com.example.core

import android.content.Context
import android.content.Intent
import android.util.Log


object ActivityUtil {

    data class SettingPageParams(
        val id: Int,
        val title: String
    )

    const val SETTING_PAGE = "com.example.app_setting.SettingActivity";
    const val VIDEO_PAGE = "com.example.video.VideoActivity";

    fun start(c: Context, intent: Intent) {
//        val anyClass = Class.forName(clazz)
        try {
            c.startActivity(intent)
        } catch (e: Exception) {
            Log.e("hanami", "start: 跳转错误 ${e.message}")
            e.printStackTrace()
        }
    }

    fun startSettingPage(c: Context, params: SettingPageParams) {

        try {
            val clazz = Class.forName(SETTING_PAGE)
//        val obj = clazz.newInstance()
            val intent = Intent(c, clazz)
            intent.putExtra("id", params.id)
            intent.putExtra("title", params.title)

            c.startActivity(intent)
        } catch (e: Exception) {
            Log.e("hanami", "start: 跳转错误 ${e.message} ${e.localizedMessage}")
            Log.e("hanami", "start: 跳转错误 $e")
            e.printStackTrace()
        }
    }

    fun startVideoPage(c: Context, params: SettingPageParams) {

        try {
            val clazz = Class.forName(VIDEO_PAGE)
//        val obj = clazz.newInstance()


            val intent = Intent(c, clazz)
            intent.putExtra("id", params.id)
            intent.putExtra("title", params.title)

//        c.startActivity(intent)
            start(c, intent)
        } catch (e: Exception) {
            Log.e("hanami", "start: 跳转错误 ${e.message} ${e.localizedMessage}")
            Log.e("hanami", "start: 跳转错误 $e")
            e.printStackTrace()
        }
    }
}