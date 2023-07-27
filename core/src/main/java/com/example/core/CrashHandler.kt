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
                Looper.loop()
            } catch (e1: Throwable) {
                handleFileException(e1)
            }
        }
    }

    override fun uncaughtException(thread: Thread, exception: Throwable) {
        handleMainThread(exception)
//        Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread, exception)

//        val stackTrace = StringWriter()
//        exception.printStackTrace(PrintWriter(stackTrace))
//
//        errorMessage.append(stackTrace.toString())
//
//        softwareInfo.append("SDK: ")
//        softwareInfo.append(Build.VERSION.SDK_INT)
//        softwareInfo.append(newLine)
//        softwareInfo.append("Release: ")
//        softwareInfo.append(Build.VERSION.RELEASE)
//        softwareInfo.append(newLine)
//        softwareInfo.append("Incremental: ")
//        softwareInfo.append(Build.VERSION.INCREMENTAL)
//        softwareInfo.append(newLine)
//
//        dateInfo.append(Calendar.getInstance().time)
//        dateInfo.append(newLine)
//
//        Log.d("hanami Error" , errorMessage.toString())
//        Log.d("hanami Software" , softwareInfo.toString())
//        Log.d("hanami Date" , dateInfo.toString())

//        val intent = Intent(myContext , CrashActivity::class.java)
//        intent.putExtra("Error" , errorMessage.toString())
//        intent.putExtra("Software" , softwareInfo.toString())
//        intent.putExtra("Date" , dateInfo.toString())
//
//        myContext.startActivity(intent)

//        android.os.Process.killProcess(android.os.Process.myPid());
//        exitProcess(2);
    }
}