package com.xz.pluginproxy

import android.content.Intent
import android.os.IBinder

/**
 * @title com.xz.pluginproxy  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des BaseService
 * @DATE 2020/11/4  17:53 星期三
 */
abstract class BaseService : IRemoteService {
    override fun onCreate() {

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return 0
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return false
    }

    override fun onDestroy() {

    }
}