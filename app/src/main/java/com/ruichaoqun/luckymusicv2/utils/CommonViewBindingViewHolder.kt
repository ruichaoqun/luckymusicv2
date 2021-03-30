package com.ruichaoqun.luckymusicv2.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/30 14:50
 * @Description:    CommonViewBindingViewHolder
 * @Version:        1.0
 */
class CommonViewBindingViewHolder<T: ViewBinding>(val binding: T):
    RecyclerView.ViewHolder(binding.root)