package com.ruichaoqun.luckymusicv2.view.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ruichaoqun.luckymusicv2.BasePagingDataAdapter
import com.ruichaoqun.luckymusicv2.data.HomeListResponse
import com.ruichaoqun.luckymusicv2.databinding.ItemAdapterHomeBinding

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/26 17:00
 * @Description:    HomeAdapter
 * @Version:        1.0
 */
class HomeAdapter : BasePagingDataAdapter<HomeListResponse.Data.Result, ItemAdapterHomeBinding>(
    { t1, t2 -> t1.value?.id == t2.value?.id }
) {
    override fun createBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): ItemAdapterHomeBinding {
        return ItemAdapterHomeBinding.inflate(layoutInflater, parent, false)
    }

    override fun convert(binding: ItemAdapterHomeBinding, item: HomeListResponse.Data.Result) {
        binding.item = item
    }

}