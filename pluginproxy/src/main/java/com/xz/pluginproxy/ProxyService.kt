package com.xz.pluginproxy

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * @title com.xz.pluginproxy  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des ProxyService
 * @DATE 2020/11/4  15:07 星期三
 *
 * 与 插件service 是 一对多的关系
 */
class ProxyService : Service() {

    override fun onCreate() {
        Log.i("zzzzzzzzzzzzzzz","proxy service on create")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("zzzzzzzzzzzzzzz","proxy service start command")
        return ServiceManager.startService(intent)?.onStartCommand(intent, flags, startId) ?: 0
    }

    override fun onBind(intent: Intent?): IBinder? {
        val binder =  ServiceManager.onBind(intent)
        Log.i("zzzzzzzzzzzzzzz","proxy service on bind ,,, $binder")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("zzzzzzzzzzzzzzz","proxy service un bind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.i("zzzzzzzzzzzzzzz","proxy service on destroy")
        super.onDestroy()
    }
}