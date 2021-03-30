package com.ruichaoqun.luckymusicv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.ruichaoqun.luckymusicv2.data.HomeListResponse
import com.ruichaoqun.luckymusicv2.view.paging.LoadMoreDataBean
import java.lang.RuntimeException

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/26 17:05
 * @Description:    BasePagingDataAdapter
 * @Version:        1.0
 */
abstract class BasePagingDataAdapter<T:Any,VB: ViewBinding>(areItemsTheSame:(oldItem: LoadMoreDataBean<T>,
                                                                             newItem: LoadMoreDataBean<T>)->Boolean
): PagingDataAdapter<LoadMoreDataBean<T>, BasePagingDataAdapter.BaseViewHolder<VB>>(
    object : DiffUtil.ItemCallback<LoadMoreDataBean<T>>(){
        override fun areItemsTheSame(
            oldItem: LoadMoreDataBean<T>,
            newItem: LoadMoreDataBean<T>
        ): Boolean {
            return areItemsTheSame(oldItem,newItem)
        }

        override fun areContentsTheSame(
            oldItem: LoadMoreDataBean<T>,
            newItem: LoadMoreDataBean<T>
        ): Boolean {
            return oldItem == newItem
        }
    }
) {
    private var hasMoreData:Boolean = true

    class BaseViewHolder<VB: ViewBinding>(val binding:VB?,val view:View ?= null): RecyclerView.ViewHolder(
        binding?.root ?: view?: throw RuntimeException("必须传binding或者view一种")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        if(viewType == -1){
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_no_more_data,parent,false)
            return BaseViewHolder(null,view)
        }
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = createBinding(layoutInflater,parent)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        val item = getItem(position)
        item?.value?.let {
            convert(holder.binding!!,it)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if(hasMoreData) 0 else 1
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        if(item?.value == null){
            return -1
        }
        return 0
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val manager = recyclerView.layoutManager
        if (manager is GridLayoutManager) {
            val defSpanSizeLookup = manager.spanSizeLookup
            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val type = getItemViewType(position)
                    if (type == -1) {
                        return 1
                    }
                    return manager.spanCount
                }
            }
        }
    }

    abstract fun createBinding(layoutInflater: LayoutInflater, parent: ViewGroup):VB

    abstract fun convert(binding: VB,item:T)

}

