package com.ruichaoqun.luckymusicv2.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * TODO: document your custom view class.
 */
class TestViewGroup : FrameLayout {
    var mListener: OnRefreshListener? = null

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {

    }

    fun setOnRefreshListener(listener:OnRefreshListener):TestViewGroup{
        this.mListener = listener
        return this
    }

    interface OnRefreshListener {
        /**
         * Called when a swipe gesture triggers a refresh.
         */
        fun onRefresh()
    }

}
