package com.ruichaoqun.luckymusicv2.utils

import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/30 14:22
 * @Description:    PagingDataAdapterExt
 * @Version:        1.0
 */
fun PagingDataAdapter<*,*>.withRefreshAndFooter(refresh: LoadStateAdapter<*>,
                                           footer: LoadStateAdapter<*>): ConcatAdapter {
    addLoadStateListener { loadStates ->
        refresh.loadState = loadStates.refresh
        footer.loadState = loadStates.append
    }
    return ConcatAdapter(refresh, this, footer)
}