package com.example.work_manager

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        val inputValue = inputData.getString(INPUT_DATA_KEY)
        Log.i("hanami", "doWork: start, inputValue: $inputValue")

        Thread.sleep(1000)

        val sp =
            applicationContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

        val num = sp.getInt(inputValue, 0)

        sp.edit().putInt(inputValue, num + 1).apply()

        return Result.success(workDataOf(OUTPUT_DATA_KEY to "$inputValue: Work res"))
    }

}