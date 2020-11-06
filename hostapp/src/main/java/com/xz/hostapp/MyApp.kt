package com.xz.hostapp

import android.app.Application
import android.content.Context
import com.xz.pluginproxy.AssetsUtils
import com.xz.pluginproxy.PluginManager

/**
 * @title com.xz.hostapp  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des MyApp
 * @DATE 2020/11/4  18:02 星期三
 */
class MyApp : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        AssetsUtils.extractAssets(this,"plugin.apk")

        val file = getFileStreamPath("plugin.apk")
        PluginManager.installPlugin(this,file.absolutePath,"com.xz.proxyapp")
    }

    override fun onCreate() {
        super.onCreate()

    }
}