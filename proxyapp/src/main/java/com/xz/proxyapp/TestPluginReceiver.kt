package com.xz.proxyapp

import android.content.Context
import android.content.Intent
import android.util.Log
import com.xz.pluginproxy.IRemoteReceiver

/**
 * @title com.xz.proxyapp  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des TestPluginReceiver
 * @DATE 2020/11/4  17:52 星期三
 */
class TestPluginReceiver : IRemoteReceiver {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("zzzzzzzzzzzzzzzz","plugin receiver on")
    }
}