package com.ruichaoqun.luckymusicv2

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/9 14:15
 * @Description:    App
 * @Version:        1.0
 */
@HiltAndroidApp
class App:Application() {

    override fun onCreate() {
        super.onCreate()
    }
}