package com.xz.proxyapp

import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.xz.pluginproxy.BaseService
import com.xz.pluginproxy.IRemoteService

/**
 * @title com.xz.proxyapp  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des TestPluginService
 * @DATE 2020/11/4  17:52 星期三
 */
class TestPluginService : BaseService() {

    override fun onCreate() {
        Log.i("zzzzzzzzzzzzzzzzzz","on create")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("zzzzzzzzzzzzzzzzzzzz","plugin service on start command")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        Log.i("zzzzzzzzzzzzzzzzzz","on destroy")
        super.onDestroy()
    }
}