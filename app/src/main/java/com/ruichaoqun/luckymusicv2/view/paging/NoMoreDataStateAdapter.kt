package com.ruichaoqun.luckymusicv2.view.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.recyclerview.widget.RecyclerView
import com.ruichaoqun.luckymusicv2.databinding.ItemNoMoreDataBinding


class NoMoreDataStateAdapter : RecyclerView.Adapter<NoMoreDataStateAdapter.NoMoreDataStateViewHolder>() {
    private var loadStates: LoadStates = LoadStates(refresh = LoadState.NotLoading(endOfPaginationReached = false),prepend = LoadState.NotLoading(endOfPaginationReached = false)
        ,append = LoadState.NotLoading(endOfPaginationReached = false))

    private var realItemCount:Int = 0

    fun checkNoMoreData(loadStates: LoadStates,itemCount:Int){
        if(this.loadStates != loadStates) {
            val oldItem = displayLoadStateAsItem(this.loadStates,realItemCount)
            val newItem = displayLoadStateAsItem(loadStates,itemCount)
            realItemCount = itemCount
            this.loadStates = loadStates
            if (oldItem && !newItem) {
                notifyItemRemoved(0)
            } else if (newItem && !oldItem) {
                notifyItemInserted(0)
            } else if (oldItem && newItem) {
                notifyItemChanged(0)
            }
        }
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoMoreDataStateViewHolder {
        return NoMoreDataStateViewHolder.create(parent)
    }

    final override fun onBindViewHolder(holder: NoMoreDataStateViewHolder, position: Int) {
    }

    final override fun getItemViewType(position: Int): Int = getStateViewType(loadStates)

    final override fun getItemCount(): Int = if (displayLoadStateAsItem(loadStates,realItemCount)) 1 else 0

    fun getStateViewType(loadState: LoadStates): Int = -1000001

    fun displayLoadStateAsItem(loadState: LoadStates,count:Int): Boolean {
        return count != 0 && loadState.append is LoadState.NotLoading && loadState.append.endOfPaginationReached
    }

    class NoMoreDataStateViewHolder(private val binding: ItemNoMoreDataBinding):RecyclerView.ViewHolder(binding.root){
        companion object{
            fun create(parent:ViewGroup):NoMoreDataStateViewHolder{
                val binding = ItemNoMoreDataBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return NoMoreDataStateViewHolder(binding)
            }
        }
    }
}
