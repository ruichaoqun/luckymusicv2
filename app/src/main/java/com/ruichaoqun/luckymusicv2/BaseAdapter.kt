package com.ruichaoqun.luckymusicv2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/10 9:52
 * @Description:    BaseAdapter
 * @Version:        1.0
 */
abstract class BaseAdapter<T:Any,VB:ViewBinding>(
    data: MutableList<T>? = null,
):RecyclerView.Adapter<BaseAdapter.BaseViewHolder<VB>>() {
    private val items:MutableList<T> = data?: mutableListOf()

    fun setNewItems(list: List<T>?){
        if(list == this.items){
            return
        }
        this.items.clear()
        this.items.addAll(list?: mutableListOf())
        notifyDataSetChanged()
    }

    fun addItems(list: MutableList<T>?){
        if(list == this.items){
            return
        }
        list?.let {it->
            this.items.addAll(it)
            notifyItemRangeInserted(this.items.size - it.size, it.size)
        }
    }

    class BaseViewHolder<VB:ViewBinding>(val binding:VB):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = createBinding(layoutInflater,parent)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        convert(holder.binding,items[position])
    }

    abstract fun createBinding(layoutInflater:LayoutInflater,parent: ViewGroup):VB

    abstract fun convert(binding: VB,item:T)


    override fun getItemCount(): Int {
        return items.size
    }
}