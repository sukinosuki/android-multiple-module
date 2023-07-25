package com.example.multi_modules_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.core.ActivityUtil
import com.example.multi_modules_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding.videoPageButton.setOnClickListener {
//            startActivity(Intent(this, VideoActivity::class.java))
            val params = ActivityUtil.SettingPageParams(1, "title1")

            ActivityUtil.startVideoPage(this, params)
        }

        binding.toSettingPageButton.setOnClickListener {
//            startActivity(Intent(this, SettingActivity::class.java))
            val params = ActivityUtil.SettingPageParams(1, "title1")

            ActivityUtil.startSettingPage(this, params)
        }

        binding.text.text = BuildConfig.ENV
    }
}