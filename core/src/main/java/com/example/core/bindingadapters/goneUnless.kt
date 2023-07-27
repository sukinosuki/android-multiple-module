package com.example.core.bindingadapters

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.core.MaxLinesToggleClickListener

object AppBindingAdapters {

    // 报错 java.lang.RuntimeException: Unable to start activity ComponentInfo{com.example.video/com.example.video.VideoActivity}:
    // java.lang.IllegalStateException: Required DataBindingComponent is null in class ActivityVideoBindingImpl.
    // A BindingAdapter in com.example.core.bindingadapters.AppBindingAdapters is not static and requires an object to use, retrieved from the DataBindingComponent.
    // If you don't use an inflation method taking a DataBindingComponent, use DataBindingUtil.setDefaultComponent or make all BindingAdapter methods static.
    // 需要声明@JvmStatic注解
    @JvmStatic
    @BindingAdapter("app:goneUnless")
    fun goneUnless(view: View, visible: Boolean) {

        Log.i("hanami", "goneUnless: visible: $visible")

        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("app:format-gender")
    fun formatGender(textView: TextView, value: Int) {

        val text = when (value) {
            1 -> {
                "Male"
            }

            2 -> {
                "Female"
            }

            else -> {
                "Keep Secret"
            }

        }

        textView.text = text
    }

    @JvmStatic
    @BindingAdapter("maxLinesToggle")
    fun maxLinesClickListener(view: TextView, collapsedMaxLines: Int) {
        view.maxLines = collapsedMaxLines

        view.setOnClickListener(MaxLinesToggleClickListener(collapsedMaxLines))
    }
}
