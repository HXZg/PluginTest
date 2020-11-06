package com.xz.pluginproxy

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import dalvik.system.DexClassLoader
import java.io.File

/**
 * @title com.xz.pluginproxy  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des PluginManager
 * @DATE 2020/11/4  14:55 星期三
 */
object PluginManager {

    private val mPluginLoaders = hashMapOf<String,ClassLoader>()

    fun installPlugin(context: Context,path: String,pluginName: String) {
        // 构建class loader 存储再集合中
        val mBaseClassLoader = context.classLoader

        val dexOutputPath = context.getDir("dex", Context.MODE_PRIVATE).absolutePath
        // 第一个参数 apk文件路径 第二个：dex文件存放路径 第三个 lib(so)文件存放路径
        val dexLoader = DexClassLoader(path,dexOutputPath,null,mBaseClassLoader)

        mPluginLoaders[pluginName] = dexLoader
    }

    fun getPluginClassLoader(pluginName: String) = mPluginLoaders[pluginName]


    fun sendReceiver(context: Context,pluginName: String,className: String,intent: Intent) {
        intent.setComponent(ComponentName("com.xz.hostapp","com.xz.pluginproxy.ProxyReceiver"))
        intent.putExtra(PluginConstant.PLUGIN_NAME,pluginName)
        intent.putExtra(PluginConstant.RECEIVER_NAME,className)
        context.sendBroadcast(intent)
    }

    fun startActivity(context: Context,pluginName: String,activityName: String) {
        val i = Intent(context,ProxyActivity::class.java)
        i.putExtra(PluginConstant.PLUGIN_NAME,pluginName)
        i.putExtra("activity_name",activityName)
        context.startActivity(i)
    }
}