package com.ruichaoqun.luckymusicv2.view.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.ruichaoqun.luckymusicv2.databinding.ActivityPagingBinding
import com.ruichaoqun.luckymusicv2.utils.withRefreshAndFooter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PagingActivity : AppCompatActivity() {
    private val viewModel by viewModels<PagingViewModel>()
    private lateinit var binding:ActivityPagingBinding
    private val mAdapter:HomeAdapter = HomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.refreshLayout.setOnRefreshListener {
            mAdapter.refresh()
        }
//        mAdapter.addLoadStateListener {
//            Log.w("AAAAAAA","source-->${it.source}")
//            binding.refreshLayout.isRefreshing = it.refresh is LoadState.Loading
//        }
        binding.recyclerView.adapter = mAdapter.withRefreshAndFooter(
            refresh = CommonRefreshAdapter(null){
                mAdapter.retry()
            },
            footer = CommonLoadMoreAdapter{
                mAdapter.retry()
            }
        )
        lifecycleScope.launch {
            viewModel.listData.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }
}