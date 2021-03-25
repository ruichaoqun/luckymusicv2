package com.ruichaoqun.luckymusicv2

import android.media.browse.MediaBrowser
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/10 9:52
 * @Description:    BaseAdapter
 * @Version:        1.0
 */
abstract class BaseAdapter<T:Any,VB:ViewBinding>(diffCallback: DiffUtil.ItemCallback<T>
): ListAdapter<T,BaseAdapter.BaseViewHolder<VB>>(diffCallback) {

    class BaseViewHolder<VB:ViewBinding>(val binding:VB):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = createBinding(layoutInflater,parent)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        convert(holder.binding,getItem(position))
    }

    abstract fun createBinding(layoutInflater:LayoutInflater,parent: ViewGroup):VB

    abstract fun convert(binding: VB,item:T)
}