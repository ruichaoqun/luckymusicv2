package com.ruichaoqun.base.common

import androidx.databinding.ViewDataBinding

abstract class BaseBVMActivity<B:ViewDataBinding,VM:BaseViewModel>:BaseBindActivity<B>() {
    protected abstract val viewModel:VM

    override fun initContentView() {
        super.initContentView()
        initInternalObserver()
    }

    private fun initInternalObserver() {
        viewModel.showToast.observe(this){
            showToast(it)
        }
        viewModel.showLoadingView.observe(this){
            showLoadingView(it)
        }
        viewModel.hideLoadingView.observe(this){
            hideLoadingView(it)
        }
        viewModel.showLoadingDialog.observe(this){
            showLoadingDialog(it)
        }
        viewModel.hideLoadingDialog.observe(this){
            hideLoadingDialog(it)
        }
        viewModel.showEmptyView.observe(this){
            showEmptyView(it)
        }
        viewModel.showErrorView.observe(this){
            showErrorView(it)
        }
    }
}