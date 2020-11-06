package com.xz.pluginproxy

/**
 * @title com.xz.pluginproxy  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des ReceiverManager
 * @DATE 2020/11/4  15:29 星期三
 */
object ReceiverManager {

    private val pluginReceivers = hashMapOf<String,IRemoteReceiver>()

    fun getPluginReceiver(pluginName: String,className: String) : IRemoteReceiver? {
        var receiver = pluginReceivers[className]
        if (receiver == null) {
            receiver = try {
                PluginManager.getPluginClassLoader(pluginName)?.loadClass(className)?.newInstance() as IRemoteReceiver?
            }catch (e: ClassNotFoundException) {
                null
            }
            if (receiver != null) {
                pluginReceivers[className] = receiver
            }
        }
        return receiver
    }
}