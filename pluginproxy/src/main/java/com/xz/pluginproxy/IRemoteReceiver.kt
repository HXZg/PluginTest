package com.xz.pluginproxy

import android.content.Context
import android.content.Intent

/**
 * @title com.xz.pluginproxy  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des IRemoteReceiver
 * @DATE 2020/11/4  15:10 星期三
 */
interface IRemoteReceiver {

    fun onReceive(context: Context?, intent: Intent?)
}