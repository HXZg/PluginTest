package com.xz.pluginproxy

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

/**
 * @title com.xz.pluginproxy  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des IRemoteActivity
 * @DATE 2020/11/4  14:22 星期三
 */
interface IRemoteActivity {

    fun onCreate(savedInstanceState: Bundle?)

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroy()

    fun setProxy(proxy: ProxyActivity,pluginName: String)

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

    fun setResult(requestCode: Int)

    fun setContentView(layoutResID: Int)

    fun <T : View?> findViewById(id: Int): T
}