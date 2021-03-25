package com.ruichaoqun.luckymusicv2.view.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
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
class TaskAdapter(private val viewModel:TasksViewModel): BaseAdapter<Task,ItemAdapterTaskBinding>(
    TaskDiffCallback()
){
    override fun createBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): ItemAdapterTaskBinding {
        return ItemAdapterTaskBinding.inflate(layoutInflater,parent,false)
    }

    override fun convert(binding: ItemAdapterTaskBinding,item:Task) {
        binding.task = item
        binding.vm = viewModel
    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}