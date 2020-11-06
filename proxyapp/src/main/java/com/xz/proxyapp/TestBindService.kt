package com.xz.proxyapp

import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.xz.pluginproxy.BaseService
import com.xz.pluginproxy.IPluginBinder

/**
 * @title com.xz.proxyapp  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des TestBindService
 * @DATE 2020/11/4  17:55 星期三
 */
class TestBindService : BaseService() {

    override fun onCreate() {
        Log.i("zzzzzzzzzzzzzzzzzz","on bind create")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("zzzzzzzzzzzzzzzzzzzz","bind plugin service on start command")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.i("zzzzzzzzzzzzz","on bind")
        return PluginBinder()
    }

    class PluginBinder : IPluginBinder,Binder() {
        override fun add(a: Int, b: Int) {
            Log.i("zzzzzzzzzzzzzzz","$a ,,, $b")
        }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("zzzzzzzzzzzzzzzzzz","on unbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.i("zzzzzzzzzzzzzzzzzz","on bind destroy")
        super.onDestroy()
    }
}