package com.ruichaoqun.base.common

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.ruichaoqun.base.common.dialog.LoadingDialog

abstract class BaseActivity:AppCompatActivity() {
    private val loadingDialog:LoadingDialog by lazy { LoadingDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()
        initialize(savedInstanceState)
    }

    protected open fun initContentView() {
        setContentView(getLayoutId())
    }

    @LayoutRes
    protected abstract fun getLayoutId():  Int


    protected abstract fun initialize(savedInstanceState: Bundle?)

    fun showToast(msg:CharSequence?){
        msg?.let { Toast.makeText(this,it,Toast.LENGTH_SHORT).show() }
    }

    fun showLoadingDialog(message:String){
        if(!isFinishing && !loadingDialog.isAdded){
            loadingDialog.show(supportFragmentManager,message,null)
        }
    }

    fun hideLoadingDialog(any: Any?){
        if(loadingDialog.isAdded && !isFinishing){
            loadingDialog.dismissAllowingStateLoss()
        }
    }

    abstract fun showLoadingView(any: Any?)

    abstract fun hideLoadingView(any: Any?)

    abstract fun showEmptyView(isShow:Boolean)

    abstract fun showErrorView(isShow: Boolean)
}