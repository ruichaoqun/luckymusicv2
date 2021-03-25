package com.ruichaoqun.luckymusicv2.utils

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/25 14:00
 * @Description:    Bindings
 * @Version:        1.0
 */
@BindingAdapter("completedTask")
fun setStyle(textView: TextView, enabled: Boolean?) {
    if (enabled == true) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}