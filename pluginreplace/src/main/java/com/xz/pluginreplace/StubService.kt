package com.xz.pluginreplace

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * @title com.xz.pluginreplace  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des StubService
 * @DATE 2020/11/3  14:39 星期二
 */
class StubService : Service() {



    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}