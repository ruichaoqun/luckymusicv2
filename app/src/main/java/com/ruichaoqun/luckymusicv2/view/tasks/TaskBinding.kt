package com.ruichaoqun.luckymusicv2.view.tasks

import androidx.databinding.InverseBindingAdapter
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/24 16:30
 * @Description:    TaskBinding
 * @Version:        1.0
 */

@InverseBindingAdapter(attribute = "refreshing",event = "refreshingAttrChanged")
fun isRefreshing(refreshLayout:SmartRefreshLayout):Boolean{
    return refreshLayout.isRefreshing
}

