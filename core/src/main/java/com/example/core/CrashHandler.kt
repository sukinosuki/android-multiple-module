package com.example.core

import android.content.Context
import android.os.Looper
import android.util.Log
import android.widget.Toast

class CrashHandler(val context: Context) : Thread.UncaughtExceptionHandler {

    private fun handleFileException(e: Throwable) {
        Log.e("hanami", "===")
        Log.e("hanami", "handleFileException: $e")

//        e.printStackTrace()

        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
        e.stackTrace.forEach { it ->
            Log.e("hanami", "handleFileException: $it")
        }
        Log.e("hanami", "===", )
    }

    private fun handleMainThread(e: Throwable) {
        while (true) {
            try {
                Log.i("hanami", "handleMainThread: $e")
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
                Looper.loop()
            } catch (e1: Throwable) {
                handleFileException(e1)
            }
        }
    }

    override fun uncaughtException(thread: Thread, exception: Throwable) {
        handleMainThread(exception)
    }
}