package com.xz.pluginproxy

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log

/**
 * @title com.xz.pluginproxy  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des ProxyActivity
 * @DATE 2020/11/4  14:40 星期三  占坑activity  具体逻辑分发给 插件activity 执行
 */
@SuppressLint("MissingSuperCall")
class ProxyActivity : Activity() {

    protected var pluginActivity: IRemoteActivity? = null
    get() {
        if (field != null) return field
        else {
            field = loadPluginActivity()
        }
        return field
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)  // 插件activity 决定调用时机
        pluginActivity?.onCreate(savedInstanceState)
    }

    fun superOnCreate(savedInstanceState: Bundle?) {  // 需要插件activity调用 所以改为public
        super.onCreate(savedInstanceState)
    }

    private fun loadPluginActivity() : IRemoteActivity {
        val pluginName = intent?.getStringExtra(PluginConstant.PLUGIN_NAME) ?: ""
        val activityName = intent?.getStringExtra("activity_name")
        val a = PluginManager.getPluginClassLoader(pluginName)?.loadClass(activityName)?.newInstance() as IRemoteActivity
        a.setProxy(this,pluginName)
        return a
    }

    override fun onStart() {
        pluginActivity?.onStart()
    }

    override fun onResume() {
        pluginActivity?.onResume()
    }

    override fun onPause() {
        pluginActivity?.onPause()
    }

    override fun onStop() {
        pluginActivity?.onStop()
    }

    override fun onDestroy() {
        pluginActivity?.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        pluginActivity?.onActivityResult(requestCode, resultCode, data)
    }

    fun superOnStart() = super.onStart()

    fun superOnResume() = super.onResume()

    fun superOnPause() = super.onResume()

    fun superOnStop() = super.onStop()

    fun superOnDestroy() = super.onDestroy()

    fun superOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?) =
        super.onActivityResult(requestCode, resultCode, data)
}