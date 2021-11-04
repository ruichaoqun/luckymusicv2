package com.ruichaoqun.luckymusicv2.view.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.recyclerview.widget.RecyclerView
import com.ruichaoqun.luckymusicv2.R
import com.ruichaoqun.luckymusicv2.databinding.LayoutNoDataBinding

abstract class NoDataStateAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    private var loadStates: LoadStates = LoadStates(refresh = LoadState.NotLoading(endOfPaginationReached = false),prepend = LoadState.NotLoading(endOfPaginationReached = false)
        ,append = LoadState.NotLoading(endOfPaginationReached = false))

    private var realItemCount:Int = 0

    fun checkNoData(loadStates: LoadStates, itemCount:Int){
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

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return onCreateViewHolder(parent, loadStates)
    }

    final override fun onBindViewHolder(holder: VH, position: Int) {
        onBindViewHolder(holder, loadStates)
    }

    final override fun getItemViewType(position: Int): Int = getStateViewType(loadStates)

    final override fun getItemCount(): Int = if (displayLoadStateAsItem(loadStates,realItemCount)) 1 else 0

    abstract fun onCreateViewHolder(parent: ViewGroup, loadState: LoadStates): VH


    abstract fun onBindViewHolder(holder: VH, loadState: LoadStates)


    open fun getStateViewType(loadState: LoadStates): Int = -1000001


    open fun displayLoadStateAsItem(loadState: LoadStates,count:Int): Boolean {
        return count == 0 && loadState.append is LoadState.NotLoading && loadState.append.endOfPaginationReached && loadState.prepend is LoadState.NotLoading && loadState.append.endOfPaginationReached
    }

    companion object{
        var DEFAULT = DefaultNoDataStateAdapter()
    }

    class DefaultNoDataStateAdapter(
        private var imageRes:Int = R.mipmap.image_empty_default,
        @StringRes var  hint:Int = R.string.no_data,
        var hintString:String ?= null,
        @StringRes var  button:Int ?= null,
        var buttonString: String ?= null,
        var retry:(()->Unit) ?= null
    ):NoDataStateAdapter<DefaultNoDataStateAdapter.NoDataStateViewHolder>(){

        override fun onCreateViewHolder(
            parent: ViewGroup,
            loadState: LoadStates
        ): NoDataStateViewHolder {
            val binding = LayoutNoDataBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return NoDataStateViewHolder(parent.context,binding, imageRes,hint,hintString,button,buttonString,retry)
        }

        override fun onBindViewHolder(holder: NoDataStateViewHolder, loadState: LoadStates) {
            holder.bind()
        }

        class NoDataStateViewHolder(var context:Context,private val binding: LayoutNoDataBinding,
                                    var imageRes:Int,
                                    @StringRes var hint:Int,
                                    var hintString:String?,
                                    @StringRes var button:Int?,
                                    var buttonString: String?,
                                    var retry:(()->Unit)?)
            :RecyclerView.ViewHolder(binding.root){

            fun bind(){
                binding.ivNoData.setImageResource(imageRes)
                if(!hintString.isNullOrEmpty()){
                    binding.tvHint.text = hintString
                }else{
                    binding.tvHint.text = context.getString(hint)
                }
                if(!buttonString.isNullOrEmpty()){
                    binding.tvNavigation.visibility = View.VISIBLE
                    binding.tvNavigation.text = buttonString
                }else if(button != null){
                    binding.tvNavigation.visibility = View.VISIBLE
                    binding.tvNavigation.text = context.getString(button!!)
                }else{
                    binding.tvNavigation.visibility = View.GONE
                }
                binding.tvNavigation.setOnClickListener {
                    retry?.invoke()
                }
            }
        }
    }
}
