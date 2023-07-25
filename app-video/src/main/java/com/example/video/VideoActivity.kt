package com.example.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.core.ActivityUtil
import com.example.core.BaseActivity
import com.example.core.env.Env
import com.example.core.viewmodel.BaseApplication
import com.example.core.viewmodel.CoreViewModel
import com.example.video.databinding.ActivityVideoBinding
import com.example.video.debug.Test

class VideoActivity : BaseActivity() {
    private lateinit var binding: ActivityVideoBinding

    private val coreViewModel by lazy {
//        ViewModelProvider(this)[CoreViewModel::class.java]
        CoreViewModel()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_video)
        binding = ActivityVideoBinding.inflate(layoutInflater)


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

        BaseApplication.instance.applicationViewModel2.count.observe(this) {
            binding.count.text = it.toString()
        }

        binding.addCountButton.setOnClickListener {
            vm.add()
        }
        vm.count.observe(this) { it ->
            binding.count.text = it.toString()
        }

    }

}