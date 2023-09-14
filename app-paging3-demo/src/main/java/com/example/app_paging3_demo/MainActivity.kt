package com.example.app_paging3_demo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_paging3_demo.databinding.ActivityMainBinding
import com.example.core.BaseActivity
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    val repoAdapter by lazy {
        RepoAdapter()
    }
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        initView()
    }

    private fun initView() {

        with(binding.recyclerView) {
            val concatAdapter =
                repoAdapter.withLoadStateHeaderAndFooter(
                    HeaderAdapter(),
                    FooterAdapter {
                        repoAdapter.retry()
                    }
                )
//            val concatAdapter = repoAdapter.withLoadStateFooter(FooterAdapter{
//                repoAdapter.retry()
//            })

            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = concatAdapter
        }

        val job = lifecycleScope.launch {
            viewModel.getPagingData().collect { data ->
                Log.i(TAG, "initView: data流发生变化 $data")

                repoAdapter.submitData(data)
            }
        }

        repoAdapter.setOnClickListener { value, position ->
            Log.i(TAG, "initView: 点击了 $position")
            repoAdapter.snapshot().toMutableList().removeAt(position + 1)
            repoAdapter.notifyItemRemoved(position + 1)

        }
//        job.cancel()

        repoAdapter.addLoadStateListener { it ->
            Log.i(TAG, "<MainActivity> initView: load state 发生变化 ${it.refresh}")

            when (it.refresh) {
                is LoadState.NotLoading -> {
                    Log.i(TAG, "<MainActivity> initView: load state 发生变化: NotLoading")
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.recyclerView.visibility = View.VISIBLE
                }

                is LoadState.Loading -> {
                    Log.i(TAG, "<MainActivity> initView: load state 发生变化: Loading")
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.INVISIBLE
                }

                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    Log.i(
                        TAG,
                        "<MainActivity> initView: load state 发生变化: Error, err: ${state.error}"
                    )

                    binding.progressBar.visibility = View.INVISIBLE

                    Log.i(TAG, "initView: 加载失败 ")
                    Toast.makeText(this@MainActivity, "Load Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}