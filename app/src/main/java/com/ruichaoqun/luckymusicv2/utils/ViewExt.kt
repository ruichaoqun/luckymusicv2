package com.ruichaoqun.luckymusicv2.utils

import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ruichaoqun.luckymusicv2.R

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/25 13:47
 * @Description:    ViewExt
 * @Version:        1.0
 */
fun SwipeRefreshLayout.initSchemeColors(){
    setColorSchemeColors(
        ContextCompat.getColor(context, R.color.colorPrimary),
        ContextCompat.getColor(context, R.color.colorAccent),
        ContextCompat.getColor(context, R.color.colorPrimaryDark)
    )
}