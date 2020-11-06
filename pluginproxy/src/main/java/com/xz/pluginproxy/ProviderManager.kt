package com.xz.pluginproxy

import android.net.Uri

/**
 * @title com.xz.pluginproxy  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des ProviderManager
 * @DATE 2020/11/4  15:29 星期三
 */
object ProviderManager {

    private val pluginProviders = hashMapOf<String,IRemoteProvider>()

    fun getPluginProvider(uri: Uri) : IRemoteProvider? { // content://host/com.xz.plugin/MainProvider/end/delete/2
        val hostAuth = uri.authority
        val newUri = uri.toString().replace("$hostAuth/", "")
        val name = newUri.replace("content://", "") // com.xz.plugin/MainProvider/end/delete/2

        val list = name.split("/")
        if (list.size >= 2) {
            val className = "${list[0]}.${list[1]}"
            var provider = pluginProviders[className]
            if (provider == null) {
                provider = try {
                    PluginManager.getPluginClassLoader(list[0])?.loadClass(className)?.newInstance() as IRemoteProvider?
                }catch (e: Exception) {
                    null
                }
                if (provider != null) {
                    pluginProviders[className] = provider
                }
            }
            return provider
        }
        return null
    }
}