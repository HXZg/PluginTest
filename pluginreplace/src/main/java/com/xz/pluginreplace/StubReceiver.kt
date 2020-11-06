package com.xz.pluginreplace

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * @title com.xz.pluginreplace  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des StubReceiver
 * @DATE 2020/11/3  15:39 星期二
 *
 * 通过在宿主中静态注册这个广播 使插件的广播 支持静态特性
 */
class StubReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        // 此处分发 插件广播
        val action = intent?.action
        val newAction = PluginManager.pluginReceiverMappings[action]
        Log.i("zzzzzzzzzzzz","$action ,,, $newAction")
        if (newAction != null) {
            context?.sendBroadcast(Intent(newAction))
        }
    }
}