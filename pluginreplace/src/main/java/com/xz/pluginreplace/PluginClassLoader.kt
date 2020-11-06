package com.xz.pluginreplace

import dalvik.system.DexClassLoader
import dalvik.system.PathClassLoader

/**
 * @title com.xz.pluginreplace  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des PluginClassLoader
 * @DATE 2020/11/3  14:10 星期二
 */
class PluginClassLoader(dexPath: String,parent: ClassLoader) : PathClassLoader(dexPath,parent) {

    private val mClassLoaderList = arrayListOf<DexClassLoader>()

    fun addPluginClassLoader(dexLoader: DexClassLoader) {
        mClassLoaderList.add(dexLoader)
    }

    override fun loadClass(name: String?, resolve: Boolean): Class<*> {
        var clazz : Class<*>? = null
        clazz = tryClass(parent,name)
        if (clazz != null) {
            return clazz
        }
        if (mClassLoaderList.isNotEmpty()) {
            mClassLoaderList.forEach {
                clazz = tryClass(it,name)
                if (clazz != null) {
                    return clazz!!
                }
            }
        }
        throw ClassNotFoundException("$name in load $this")
    }

    fun tryClass(loader: ClassLoader,name: String?) : Class<*>? {
        return try {
            loader.loadClass(name)
        }catch (e: ClassNotFoundException) {
            null
        }
    }
}