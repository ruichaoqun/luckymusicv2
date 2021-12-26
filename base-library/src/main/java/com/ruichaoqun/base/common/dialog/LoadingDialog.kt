package com.ruichaoqun.base.common.dialog

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ruichaoqun.base.databinding.CommonDialogLoadingBinding
import com.ruichaoqun.base.R

class LoadingDialog:BaseBindDialogFragment<CommonDialogLoadingBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.common_dialog_loading
    }

    fun show(manager: FragmentManager,message:CharSequence, tag: String?) {
        super.show(manager, tag?:this::javaClass.name)
        binding.loadingView.startAnimation()
        binding.tvHint.text = message
    }

    fun showNow(manager: FragmentManager,message:CharSequence, tag: String?) {
        super.showNow(manager, tag?:this::javaClass.name)
        binding.loadingView.startAnimation()
        binding.tvHint.text = message
    }

    override fun dismiss() {
        super.dismiss()
        binding.loadingView.stopAnimation()
    }

    override fun dismissAllowingStateLoss() {
        super.dismissAllowingStateLoss()
        binding.loadingView.stopAnimation()
    }
}