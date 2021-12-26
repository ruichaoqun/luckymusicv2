package com.ruichaoqun.base.common.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDialogFragment


abstract class BaseDialogFragment:AppCompatDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutId(),container,false)
        return view
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int
}