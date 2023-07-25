package com.example.video.debug

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.core.app.ActivityCompat

class Test {

    fun toast(ctx: Context){
        Toast.makeText(ctx, "hello", Toast.LENGTH_SHORT).show()
    }
}