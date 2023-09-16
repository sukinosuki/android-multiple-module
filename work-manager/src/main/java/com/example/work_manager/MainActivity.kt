package com.example.work_manager

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.work_manager.databinding.ActivityMainBinding

const val INPUT_DATA_KEY = "input_data_key"
const val OUTPUT_DATA_KEY = "output_data_key"
const val WORK_NAME_A = "work a"
const val WORK_NAME_B = "work b"
const val SHARED_PREFERENCES_NAME = "shp_name"

//val WORK_A_DATA_KEY = "work a key"
//val WORK_B_DATA_KEY = "work b key"

class MainActivity : AppCompatActivity() {

    private lateinit var sp: SharedPreferences

    private lateinit var binding: ActivityMainBinding

    private val workManager by lazy {
        WorkManager.getInstance(this)
    }

    private val onSharedPreferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            Log.i("hanami", "sp 改变 key: $key")
            updateView()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        sp = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        sp.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
//        onSharedPreferenceChangeListener.onSharedPreferenceChanged(sp, WORK_NAME_A)
//        onSharedPreferenceChangeListener.onSharedPreferenceChanged(sp, WORK_NAME_B)

        binding.refreshButton.setOnClickListener {
//            val workAExecuteNum = sp.getInt(WORK_NAME_A, 0)
//            val workBExecuteNum = sp.getInt(WORK_NAME_B, 0)

            updateView()
//            binding.workAExecutedCount.text = workAExecuteNum.toString()
//            binding.workBExecutedCount.text = workBExecuteNum.toString()
        }

        updateView()
        binding.newWork.setOnClickListener {
            // tip: ctrl + alt + m 提取成一个方法
            // 限制约束条件
            val workRequestA = newOneTimeWorkRequest(WORK_NAME_A)
            val workRequestB = newOneTimeWorkRequest(WORK_NAME_B)

            workManager.beginWith(workRequestA).then(workRequestB).enqueue()
            workManager.getWorkInfoByIdLiveData(workRequestA.id).observe(this) { it ->
                if (it.state == WorkInfo.State.SUCCEEDED) {
//                    binding.workAExecutedCount.text = sp.getInt(WORK_NAME_A, 0).toString()
                }
            }
            workManager.getWorkInfoByIdLiveData(workRequestB.id).observe(this) { it ->
                if (it.state == WorkInfo.State.SUCCEEDED) {
//                    binding.workBExecutedCount.text = sp.getInt(WORK_NAME_B, 0).toString()
                }
            }

//            Log.i("hanami", "onCreate: workRequest.id: ${workRequest.id}")
//
//            workManager.enqueue(workRequest)
//
//            workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this) { it ->
//                Log.i(
//                    "hanami",
//                    "onCreate: workinfo: id: ${it.id}, tags: ${it.tags}, progress: ${it.progress}, state: ${it.state}"
//                )
//
//                if (it.state == WorkInfo.State.SUCCEEDED) {
//                    Log.i("hanami", "onCreate: work executive success, output data: ${it.outputData}")
//                }
//            }
        }
    }

    private fun updateView() {
        binding.workAExecutedCount.text = sp.getInt(WORK_NAME_A, 0).toString()
        binding.workBExecutedCount.text = sp.getInt(WORK_NAME_B, 0).toString()
    }

    private fun newOneTimeWorkRequest(workName: String): OneTimeWorkRequest {
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setConstraints(constraints)
            .setInputData(workDataOf(INPUT_DATA_KEY to workName))
            .build()

        return workRequest
    }
}
