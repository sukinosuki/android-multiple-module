package com.example.widget_group

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.widget_group.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {

            setContentView(root)
        }

        initView()
    }

    private fun initView() {

        binding.setButton.setOnClickListener {
            binding.commonItemLayout.binding.name.text = "shiroko 22"
            binding.commonItemLayout.binding.cover.setImageResource(R.drawable.shiroko)
        }
    }
}