package com.ruichaoqun.luckymusicv2.utils

import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import com.ruichaoqun.luckymusicv2.view.paging.NoDataStateAdapter
import com.ruichaoqun.luckymusicv2.view.paging.NoMoreDataStateAdapter

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/30 14:22
 * @Description:    PagingDataAdapterExt
 * @Version:        1.0
 */
fun PagingDataAdapter<*,*>.withRefreshAndFooter(refresh: LoadStateAdapter<*>,
                                           footer: LoadStateAdapter<*>): ConcatAdapter {
    var noData = NoDataStateAdapter.DEFAULT
    var noMoreData = NoMoreDataStateAdapter()
    addLoadStateListener { loadStates ->
        refresh.loadState = loadStates.refresh
        footer.loadState = loadStates.append
        noData.checkNoData(loadStates.source,itemCount)
        noMoreData.checkNoMoreData(loadStates.source,itemCount)
    }
    return ConcatAdapter(refresh,noData, this, footer,noMoreData)
}