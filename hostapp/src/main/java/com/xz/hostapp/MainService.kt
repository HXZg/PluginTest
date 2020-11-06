package com.xz.hostapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * @title com.xz.hostapp  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des MainService
 * @DATE 2020/11/5  9:25 星期四
 */
class MainService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        Log.i("zzzzzzzzzzzzzzzz","main service on bind")
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("zzzzzzzzzzz","main service on unbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.i("zzzzzzzzzzzzzzzz","main service on destroy")
        super.onDestroy()
    }
}