package com.xz.pluginproxy

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * @title com.xz.pluginproxy  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des ProxyReceiver
 * @DATE 2020/11/4  15:09 星期三
 *
 * 宿主占坑receiver 与 插件receiver 是一对多的关系
 */
class ProxyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("zzzzzzzzzzzzz","proxy receiver")
        val receiverName = intent?.getStringExtra(PluginConstant.RECEIVER_NAME)
        val pluginName = intent?.getStringExtra(PluginConstant.PLUGIN_NAME)
        if (!receiverName.isNullOrEmpty() && !pluginName.isNullOrEmpty()) {
            ReceiverManager.getPluginReceiver(pluginName,receiverName)?.onReceive(context,intent)
        }
    }
}