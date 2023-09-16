package com.example.widget_group

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.widget_group.databinding.CommonItemLayoutBinding

class CommonItemLayout(context: Context, private val attrs: AttributeSet?) : LinearLayout(context, attrs) {
    lateinit var binding: CommonItemLayoutBinding

    init {
        initView()
    }

    private fun initView() {
        binding = CommonItemLayoutBinding.inflate(LayoutInflater.from(context), this, true)

        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CommonItemLayout)
        val name = typedArray.getString(R.styleable.CommonItemLayout_name)
        val cover = typedArray.getInteger(R.styleable.CommonItemLayout_cover, 0)

        Log.i("hanami", "initView: name $name")
        Log.i("hanami", "initView: cover $cover")

        binding.name.text = name

        typedArray.recycle()
    }
}