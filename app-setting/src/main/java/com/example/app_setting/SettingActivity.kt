package com.example.app_setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.app_setting.databinding.ActivitySettingBinding
import com.example.core.ActivityUtil
import com.example.core.BaseActivity
import com.example.core.BuildConfig
import com.example.core.env.Env
import com.example.core.viewmodel.BaseApplication
import com.example.core.viewmodel.CoreViewModel

class SettingActivity : BaseActivity() {
    private lateinit var binding: ActivitySettingBinding

    private val coreViewModel by lazy {
//        ViewModelProvider(this)[CoreViewModel::class.java]
        CoreViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_setting)

        binding = ActivitySettingBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        val id = intent.getIntExtra("id", -1)
        val title = intent.getStringExtra("title")
        val title2 = intent.getStringExtra("title2")
        Log.i("hanami", "onCreate: id $id")
        Log.i("hanami", "onCreate: title $title")
        Log.i("hanami", "onCreate: title2 $title2")

        Log.i("hanami", "setting onCreate: BuildConfig.IS_PROD , ${BuildConfig.IS_PROD}")

//        Log.i("hanami", "env: ${BuildConfig.ENV}")
        Log.i("hanami", "debug: ${BuildConfig.DEBUG}")
        Log.i("hanami", "build type: ${BuildConfig.BUILD_TYPE}")
//        Log.i("hanami", "version code: ${BuildConfig.VERSION_CODE}")
//        Log.i("hanami", "version name: ${BuildConfig.VERSION_NAME}")
//
//        binding.text.text = BuildConfig.ENV

//        binding.name.text = Env.name
        binding.text.text = Env.env

        binding.changeNameBtn.setOnClickListener {
//            Env.name = "setting page"
            coreViewModel.changeName("setting page")

            BaseApplication.instance.applicationViewModel2.add()
        }

        coreViewModel.name.observe(this) {
            binding.name.text = it
        }
        BaseApplication.instance.applicationViewModel2.count.observe(this) {
            binding.count.text = it.toString()
        }

        binding.toVideoPageButton.setOnClickListener {
            ActivityUtil.startVideoPage(
                this,
                ActivityUtil.SettingPageParams(2, "from setting page")
            )
        }

        binding.addCountButton.setOnClickListener {
            vm.add()
        }
        vm.count.observe(this) { it ->
            binding.countText.text = it.toString()
        }
    }
}