package com.example.app_request_permission

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.ContentObserver
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.app_request_permission.databinding.ActivityMainBinding

val TAG = "hanami"

// java.lang.RuntimeException: Unable to start activity ComponentInfo{com.example.app_request_permission/com.example.app_request_permission.MainActivity}:
// java.lang.SecurityException: Failed to find provider null for user 0; expected to find a valid ContentProvider for this authority
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val REQUEST_CODE_CAMERA_PERMISSION = 1
    val smsObserver = SmsObserver(this, Handler(Looper.getMainLooper()))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        initView()

        observeSms()

    }

    private fun observeSms() {

    }

    override fun onDestroy() {
        super.onDestroy()
        contentResolver.unregisterContentObserver(smsObserver)
    }

    class SmsObserver(val context: Context, handler: Handler) : ContentObserver(handler) {
        @SuppressLint("Range")
        override fun onChange(selfChange: Boolean, uri: Uri?) {
            super.onChange(selfChange, uri)
            // onChange会调用多次，收到一条短信会调用再次onChange
            // uri == content://sms/raw/20
            // uri == content://sms/inbox/20
            // 安卓7.0以上的系统，点击标记为已读，也会调用一次
            // uri == content://sms
            // 收到一条短信都是uri后面都会有确定的一个数字，对应数据库的id，比如上面的20

            Log.i(TAG, "onChange: uri $uri")
            if (uri == null) return

            if (uri.toString().contains("content://sms/raw") || uri.toString()
                    .equals("content://sms")
            ) return

            val cursor = this.context.contentResolver.query(
                uri,
                arrayOf("address", "body", "date"),
                null,
                null,
                "date DESC"
            )

            cursor?.let {
                if (cursor.moveToNext()) {
                    val address = cursor.getString(cursor.getColumnIndex("address"))
                    val content = cursor.getString(cursor.getColumnIndex("body"))
                    Log.i(TAG, "onChange: address: $address, content: $content")

                    Toast.makeText(this.context, "收到短信: $address", Toast.LENGTH_SHORT).show()
                }
                cursor.close()
            }
        }
    }

    val mResultLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->

        // content://com.android.providers.media.documents/document/image%3A34072
        Log.i(TAG, "uri: $uri")
    }

    // 普通的Activity跳转
    val mResultLauncher2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // content://com.android.providers.media.documents/document/image%3A34072
        Log.i(TAG, "result.resultCode: ${result.resultCode}")
//        Log.i(TAG, "result.resultCode: ${}")
        val intent = result.data
        intent?.let {
            val uri = intent.data
            Log.i(TAG, "uri: $uri")
        }
    }
    //请求精确定位权限
    val mResultLauncher3 = registerForActivityResult(ActivityResultContracts.RequestPermission()){ it->
        Log.i(TAG, "申请权限: $it")
    }
//        .launch(Manifest.permission.ACCESS_FINE_LOCATION)

    private fun initView() {

        binding.requestCameraButton.setOnClickListener {
            val cameraPermission =
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            val recordPermission =
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)

            val readSmsPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS)

            if (cameraPermission != PackageManager.PERMISSION_GRANTED || recordPermission != PackageManager.PERMISSION_GRANTED || readSmsPermission != PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "initView: 申请权限开始")
                ActivityCompat.requestPermissions(
                    this,//activity上下文
                    arrayOf(
                        android.Manifest.permission.CAMERA,
                        android.Manifest.permission.RECORD_AUDIO,
                        android.Manifest.permission.READ_SMS
                    ), // 申请的权限
                    REQUEST_CODE_CAMERA_PERMISSION //回调code
                )
            } else {
                // 给指定内容注册内容观察器, 一旦发生数据变化, 就触发观察器的onChange方法
                val uri = Uri.parse("content://sms")

                contentResolver.registerContentObserver(uri, true, smsObserver)

                Toast.makeText(this, "已授权", Toast.LENGTH_SHORT).show()
            }
        }

        binding.chooseImageButton.setOnClickListener {
//            val intent = Intent(Intent.ACTION_GET_CONTENT)
//            intent.setType("image/*")
//            mResultLauncher2.launch(intent)

//            mResultLauncher.launch("image/*")

            mResultLauncher3.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.i(TAG, "申请权限回调: requestCode: $requestCode")
        Log.i(TAG, "申请权限回调: grantResults size: ${grantResults.size}")
        Log.i(TAG, "申请权限回调: permissions size: ${permissions.size}")
        grantResults.forEach {
            Log.i(TAG, "申请权限回调: grant result: $it")
        }

        permissions.forEach {
            Log.i(TAG, "申请权限回调: permission: $it")
        }
        val isAllGrant = grantResults.all {
            it == PackageManager.PERMISSION_GRANTED
        }
        if (isAllGrant) {
            Toast.makeText(this, "全部授权成功", Toast.LENGTH_SHORT).show()
        }

        // 判断code
        // 判断是否授权
//        if (requestCode == REQUEST_CODE_CAMERA_PERMISSION && grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
        if (requestCode == REQUEST_CODE_CAMERA_PERMISSION && grantResults.isNotEmpty() && isAllGrant) {
            // 执行逻辑
            Toast.makeText(this, "授权成功11", Toast.LENGTH_SHORT).show()
            // 给指定内容注册内容观察器, 一旦发生数据变化, 就触发观察器的onChange方法
            val uri = Uri.parse("content://sms")

            contentResolver.registerContentObserver(uri, true, smsObserver)
        } else {
            // 第一次拒绝
            // 第二次拒绝并且不再询问
            // 之后再点击就不再弹出授权弹窗了
            Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show()
            val builder = AlertDialog.Builder(this)
            with(builder) {
                setTitle("提示")
                setMessage("需要授权")
            }

            builder.setNegativeButton("取消") { dialog, which ->
                Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show()
            }
            builder.setCancelable(true)
            builder.setPositiveButton("去设置") { dialog, which ->
                Toast.makeText(this@MainActivity, "确定", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                val intent = Intent().apply {
                    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    data = Uri.fromParts("package", packageName, null)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }

                startActivity(intent)
            }

            val dialog = builder.create()

            dialog.show()
        }
    }
}