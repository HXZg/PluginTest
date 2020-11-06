package com.xz.pluginproxy

import android.content.Intent
import android.os.IBinder

/**
 * @title com.xz.pluginproxy  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des IRemoteService
 * @DATE 2020/11/4  15:04 星期三
 */
interface IRemoteService {

    fun onCreate()

    fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int

    fun onBind(intent: Intent?): IBinder?

    fun onUnbind(intent: Intent?): Boolean

    fun onDestroy()
}