package com.ruichaoqun.luckymusicv2.view.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.ruichaoqun.luckymusicv2.databinding.CommonLoadingBinding
import com.ruichaoqun.luckymusicv2.databinding.LayoutLoadStateFooterBinding
import com.ruichaoqun.luckymusicv2.utils.CommonViewBindingViewHolder

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/30 14:37
 * @Description:    CommonRefreshAdapter
 * @Version:        1.0
 */
class CommonRefreshAdapter(private val errorHint:String?,
                           private val retry:()->Unit?) : LoadStateAdapter<CommonViewBindingViewHolder<CommonLoadingBinding>>() {
    override fun onBindViewHolder(
        holder: CommonViewBindingViewHolder<CommonLoadingBinding>,
        loadState: LoadState
    ) {
        holder.binding.progressBar.isVisible = loadState is LoadState.Loading
        holder.binding.tvHint.isVisible = loadState is LoadState.Loading
        holder.binding.ivError.isVisible = loadState is LoadState.Error
        holder.binding.tvErrorHint.isVisible = loadState is LoadState.Error
        holder.binding.tvErrorHint.text = errorHint
        holder.binding.btnRetry.isVisible = loadState is LoadState.Error
        holder.binding.btnRetry.setOnClickListener {
            retry.invoke()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CommonViewBindingViewHolder<CommonLoadingBinding> {
        val binding = CommonLoadingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommonViewBindingViewHolder(binding)
    }
}