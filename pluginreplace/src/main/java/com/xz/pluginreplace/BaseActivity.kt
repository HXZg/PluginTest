package com.xz.pluginreplace

import android.app.Activity
import android.content.res.AssetManager
import android.content.res.Resources

/**
 * @title com.xz.pluginreplace  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des BaseActivity
 * @DATE 2020/11/3  11:44 星期二
 */
open class BaseActivity : Activity(){

    override fun getResources(): Resources {
        return PluginManager.mResources
//        return super.getResources()
    }

    override fun getTheme(): Resources.Theme? {
        return PluginManager.getTheme(this)
    }

    override fun getAssets(): AssetManager {
        return PluginManager.assets
//        return super.getAssets()
    }

    fun getSuperTheme() : Resources.Theme = super.getTheme()
}