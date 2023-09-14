package com.example.app_room_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.app_room_demo.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


const val TAG = "hanami"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val wordRepository by lazy {
//        WordRepository.getInstance(this)
        WordRepository(this)
    }
    private val wordRepository2 by lazy {
//        WordRepository.getInstance(this)
        WordRepository(this)
    }
    private val wordRepository3 by lazy {
//        WordRepository.getInstance(this)
        WordRepository(this)
    }

    //    val vm by viewModels<MainViewModel>()
    private val vm by lazy {
        MainViewModel(wordRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        initView()
    }

    private fun initView() {

        binding.insertButton.setOnClickListener {
            val word = Word(
                word = "hello",
                chineseMeaning = "你好"
            )
            lifecycleScope.launch {
//                wordDao.insert(word)
//                wordRepository.insert(word)
//            vm.insert(word)

                wordRepository2.insert(word)
            }
        }

        vm.allWords.observe(this) {
            Log.i(TAG, "word : $it")
        }

//        wordDao.getAll().observe(this) {
//            Log.i(TAG, "word : $it")
//        }

//        wordRepository.getAllWords().observe(this@MainActivity) {
//            Log.i(TAG, "word : $it")
//        }
    }
}