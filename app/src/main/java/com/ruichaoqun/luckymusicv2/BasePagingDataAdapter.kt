package com.ruichaoqun.luckymusicv2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewbinding.ViewBinding
import java.lang.RuntimeException

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/26 17:05
 * @Description:    BasePagingDataAdapter
 * @Version:        1.0
 */
abstract class BasePagingDataAdapter<T:Any,VB: ViewBinding>(areItemsTheSame:(oldItem: T,
                                                                             newItem: T)->Boolean):
    PagingDataAdapter<T, BasePagingDataAdapter.BaseViewHolder<VB>>(
    object : DiffUtil.ItemCallback<T>(){
        override fun areItemsTheSame(
            oldItem: T,
            newItem: T
        ): Boolean {
            return areItemsTheSame(oldItem,newItem)
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: T,
            newItem: T
        ): Boolean {
            return oldItem == newItem
        }
    }
) {

    class BaseViewHolder<VB: ViewBinding>(val binding:VB?,val view:View ?= null): RecyclerView.ViewHolder(
        binding?.root ?: view?: throw RuntimeException("必须传binding或者view一种")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = createBinding(layoutInflater,parent)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        getItem(position)?.let {
            holder.binding?.let { it1 -> convert(it1,it) }
        }
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<VB>) {
        super.onViewAttachedToWindow(holder)
        val type = holder.itemViewType
        if(type == 1000001){
            setFullSpan(holder)
        }
    }

    protected open fun setFullSpan(holder: RecyclerView.ViewHolder) {
        val layoutParams = holder.itemView.layoutParams
        if (layoutParams is StaggeredGridLayoutManager.LayoutParams) {
            layoutParams.isFullSpan = true
        }
    }


    abstract fun createBinding(layoutInflater: LayoutInflater, parent: ViewGroup):VB

    abstract fun convert(binding: VB,item:T)
}

