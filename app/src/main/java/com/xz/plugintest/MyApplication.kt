package com.xz.plugintest

import android.app.Application
import android.content.Context
import com.xz.pluginreplace.DexMergeHelper
import com.xz.pluginreplace.PluginManager
import com.xz.pluginreplace.utils.AssetsUtils
import com.xz.pluginreplace.utils.HookATHCallback

/**
 * @title com.xz.plugintest  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des MyApplication
 * @DATE 2020/11/3  15:48 星期二
 */
class MyApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        AssetsUtils.extractAssets(this,"plugin.apk")

        val file = getFileStreamPath("plugin.apk")
//        val optFile = getFileStreamPath("plugin.dex")

        PluginManager.installPlugin(baseContext,file.absolutePath)  // 构建class loader 替换系统的
        DexMergeHelper.preLoadReceiver(this,file)  // 动态注册插件广播
        DexMergeHelper.installProviders(this,file)  // 安装 content provide
//        DexMergeHelper.mergeDex(classLoader,file,optFile)  // 合并dex 的解决方案
        PluginManager.loadResources(baseContext,file.absolutePath)  // 加载插件资源
        HookATHCallback.hookActivityThread()   // service 启动 需要 hook
        DexMergeHelper.changeATInstrumentation()  // activity 创建 需要修改 instrumentation
    }

    override fun onCreate() {
        super.onCreate()
    }
}