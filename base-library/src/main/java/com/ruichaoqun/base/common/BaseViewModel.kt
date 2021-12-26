package com.ruichaoqun.base.common

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruichaoqun.base.R

class BaseViewModel(var application: Application):ViewModel() {
    private var _showLoadingView = MutableLiveData<Any?>()
    val showLoadingView:LiveData<Any?> = _showLoadingView

    private var _hideLoadingView = MutableLiveData<Any?>()
    val hideLoadingView:LiveData<Any?> = _hideLoadingView

    private var _showLoadingDialog = MutableLiveData<String>()
    val showLoadingDialog:LiveData<String> = _showLoadingDialog

    private var _hideLoadingDialog = MutableLiveData<Any?>()
    val hideLoadingDialog:LiveData<Any?> = _hideLoadingDialog

    private var _showToast = MutableLiveData<String?>()
    val showToast:LiveData<String?> = _showToast

    private var _showErrorView = MutableLiveData<Boolean>()
    val showErrorView:LiveData<Boolean> = _showErrorView

    private var _showEmptyView = MutableLiveData<Boolean>()
    val showEmptyView:LiveData<Boolean> = _showEmptyView

    /**
     * 展示loading View，各页面负责独立实现
     */
    protected fun showLoadingView(){
        _showLoadingView.postValue(null)
    }

    /**
     * 隐藏loading View
     */
    protected fun hideLoadingView(){
        _hideLoadingView.postValue(null)
    }

    /**
     * 展示loading弹窗，基于AppCompatDialogFragment实现
     */
    protected fun showLoadingDialog(message:String ?= null,@StringRes messageRes:Int ?= null){
        var msg = application.resources.getString(R.string.common_loading)
        if(!message.isNullOrEmpty()){
            msg = message
        }
        messageRes?.let {
            msg = application.resources.getString(it)
        }
        _showLoadingDialog.postValue(msg)
    }

    /**
     * 隐藏loading弹窗
     */
    protected fun hideLoadingDialog(){
        _hideLoadingDialog.postValue(null)
    }

    /**
     * 展示toast弹窗
     */
    protected fun showToast(message:String ?= null,@StringRes messageRes:Int ?= null){
        var msg:String ?= null
        if(!message.isNullOrEmpty()){
            msg = message
        }
        messageRes?.let {
            msg = application.resources.getString(it)
        }
        msg?.let {
            _showToast.postValue(msg)
        }
    }

    /**
     * 展示空数据页面
     */
    protected fun showEmptyView(isShow:Boolean){
        _showEmptyView.postValue(isShow)
    }

    /**
     * 展示网络错误页面
     */
    protected fun showErrorView(isShow:Boolean){
        _showErrorView.postValue(isShow)
    }
}