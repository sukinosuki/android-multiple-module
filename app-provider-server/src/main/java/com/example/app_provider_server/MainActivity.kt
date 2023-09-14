package com.example.app_provider_server

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ContactsContract.CommonDataKinds.Phone.CONTENT_URI
    }
}