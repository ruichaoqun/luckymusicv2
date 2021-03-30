package com.ruichaoqun.luckymusicv2.view.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ruichaoqun.luckymusicv2.databinding.LayoutLoadStateFooterBinding

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/26 18:13
 * @Description:    CommonLoadStateAdapter
 * @Version:        1.0
 */
class CommonLoadMoreAdapter(private val retry:()->Unit):LoadStateAdapter<CommonLoadMoreAdapter.CommonLoadStateViewHolder>() {
    private lateinit var mFooterLayout: LinearLayout
    protected lateinit var context: Context

    override fun onBindViewHolder(holder: CommonLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CommonLoadStateViewHolder {
        return CommonLoadStateViewHolder.create(parent,retry)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.context = recyclerView.context
    }


    class CommonLoadStateViewHolder(private val binding:LayoutLoadStateFooterBinding,retry:()->Unit):RecyclerView.ViewHolder(binding.root){
        init {
            binding.btnRetry.also {
                it.setOnClickListener{
                    retry.invoke()
                }
            }
        }

        fun bind(loadState:LoadState){
            if(loadState is LoadState.Error){
                binding.tvError.text = loadState.error.localizedMessage
            }
            binding.tvError.isVisible = loadState is LoadState.Error
            binding.btnRetry.isVisible = loadState is LoadState.Error
            binding.progress.isVisible = loadState is LoadState.Loading
        }

        companion object{
            fun create(parent:ViewGroup,retry:()->Unit):CommonLoadStateViewHolder{
                val binding = LayoutLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return CommonLoadStateViewHolder(binding,retry)
            }
        }
    }
}