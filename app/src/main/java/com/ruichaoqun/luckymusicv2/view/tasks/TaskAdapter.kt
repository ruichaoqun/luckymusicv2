package com.ruichaoqun.luckymusicv2.view.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ruichaoqun.luckymusicv2.BaseAdapter
import com.ruichaoqun.luckymusicv2.R
import com.ruichaoqun.luckymusicv2.data.Task
import com.ruichaoqun.luckymusicv2.databinding.ItemAdapterTaskBinding


/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/10 9:22
 * @Description:    TaskAdapter
 * @Version:        1.0
 */
class TaskAdapter(): BaseAdapter<Task,ItemAdapterTaskBinding>(data = null){
    override fun createBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): ItemAdapterTaskBinding {
        return ItemAdapterTaskBinding.inflate(layoutInflater,parent,false)
    }

    override fun convert(binding: ItemAdapterTaskBinding,item:Task) {
        binding.task = item
    }
}