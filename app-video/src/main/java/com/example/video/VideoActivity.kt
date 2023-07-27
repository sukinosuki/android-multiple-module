package com.example.video

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.core.ActivityUtil
import com.example.core.BaseActivity
import com.example.core.BuildConfig
import com.example.core.dataStore
import com.example.core.env.Env
import com.example.core.viewmodel.BaseApplication
import com.example.core.viewmodel.CoreViewModel
import com.example.video.databinding.ActivityVideoBinding
import com.example.video.debug.Test
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class VideoActivity : BaseActivity() {
    private lateinit var binding: ActivityVideoBinding

    private val coreViewModel by lazy {
//        ViewModelProvider(this)[CoreViewModel::class.java]
        CoreViewModel()
    }
    val EXAMPLE_COUNTER = intPreferencesKey("counter")

    val viewModel by viewModels<ViewModel>()
    suspend fun increaseCounter() {
        applicationContext.dataStore.edit { settings ->
            val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
            settings[EXAMPLE_COUNTER] = currentCounterValue + 1
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_video)
        binding = ActivityVideoBinding.inflate(layoutInflater).also {
            it.vm = viewModel
        }

        com.example.core.BuildConfig.BUILD_TYPE

        Log.i("hanami", "video onCreate: BuildConfig.IS_PROD , ${BuildConfig.IS_PROD}")
        setContentView(binding.root)
        val test = Test()

        test.toast(this)

        binding.toSettingPageButton.setOnClickListener {
            ActivityUtil.startSettingPage(this, ActivityUtil.SettingPageParams(3, "title3"))
        }
        if (Env.isDevelopment) {
            binding.textView.text = Env.isDevelopment.toString()
//            binding.name.text = Env.name
        }

        Toast.makeText(this, Env.name, Toast.LENGTH_SHORT).show()

        binding.changeNameBtn.setOnClickListener {
//            Env.name = "video page"
            coreViewModel.changeName("video page")
            BaseApplication.instance.applicationViewModel2.add()
        }

        coreViewModel.name.observe(this) {
            binding.name.text = it
        }

        val exampleCounterFlow = applicationContext.dataStore.data.map { preferences ->
            preferences[EXAMPLE_COUNTER] ?: 0
        }

        lifecycleScope.launch {
            // 每次counter变化都会观察到
            exampleCounterFlow.collect { it ->
                Log.i("hanami", "counter: $it")
            }
        }

        lifecycleScope.launch {
            // first只获取第一次
            applicationContext.dataStore.data.first().also {
                val counter = it[EXAMPLE_COUNTER]
                Log.i("hanami", "first $counter")


            }
        }

        binding.addCounterButton.setOnClickListener {
            lifecycleScope.launch {
                applicationContext.dataStore.edit { settings ->
                    val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
                    Log.i("hanami", "currentCounterValue $currentCounterValue")
                    settings[EXAMPLE_COUNTER] = currentCounterValue + 1

//                    settings.remove(EXAMPLE_COUNTER) // 删除key
//                    settings.clear() // 清除所有
//                    settings.contains(EXAMPLE_COUNTER) // 是否包含
                }
            }
        }

        vm.count.observe(this) {
            Log.i("hanami", "video page count变化 $it")
            it?.let {
                val sp = getSharedPreferences("data2", Context.MODE_PRIVATE)
                val editor = sp.edit()
                editor.putInt("count", it)
                editor.commit()

                binding.count.text = it.toString()
            }
        }

        binding.addCountButton.setOnClickListener {

            val username: String? = null
            Log.i("hanami", "onCreate: ${username!!.length}")
        }

    }
}