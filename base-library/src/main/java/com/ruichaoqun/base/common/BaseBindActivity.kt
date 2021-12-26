package com.ruichaoqun.base.common

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindActivity<B: ViewDataBinding>:BaseActivity() {
    protected lateinit var binding:B
        private set

    override fun initContentView() {
        binding = DataBindingUtil.setContentView(this,getLayoutId())
        binding.lifecycleOwner = this
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }
}