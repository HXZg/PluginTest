package com.xz.pluginproxy

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log

/**
 * @title com.xz.pluginproxy  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des ServiceManager
 * @DATE 2020/11/4  15:28 星期三
 */
object ServiceManager {

    private val bindServices = hashMapOf<ServiceConnection,Intent>()

    private val mPluginServices = hashMapOf<String,IRemoteService>()

    fun startService(intent: Intent?) : IRemoteService? {
        val pluginName = intent?.getStringExtra(PluginConstant.PLUGIN_NAME)
        val className = intent?.getStringExtra("service_name")
        if (!pluginName.isNullOrEmpty() && !className.isNullOrEmpty()) {
            var service = mPluginServices[className]
            if (service == null) {
                service =
                    PluginManager.getPluginClassLoader(pluginName)?.loadClass(className)
                        ?.newInstance() as IRemoteService  // 此处可以通过反射ActivityThread 调用 handleCreateService方法 创建Service
                service.onCreate()
                mPluginServices[className] = service
            }
            return service
        }
        return null
    }

    fun onBind(intent: Intent?) : IBinder? {
        val service = startService(intent)
        val onBind = service?.onBind(intent)
        Log.i("zzzzzzzzzzzzzzzzzz","$service,,,$onBind")
        return onBind
    }

    fun bindService(context: Context,intent: Intent,connection: ServiceConnection) {
//        if (bindServices[connection] != null) return  // 已经绑定过service
        bindServices[connection] = intent
        context.bindService(intent,connection,Context.BIND_AUTO_CREATE)
    }

    fun stopService(context: Context,className: String) {
        mPluginServices[className]?.onDestroy()
        mPluginServices.remove(className)  // 对应的service 已停止
        if (mPluginServices.isEmpty()) context.stopService(Intent(context,ProxyService::class.java))
    }

    fun unBindService(context: Context,connection: ServiceConnection) : Boolean {
        var result = false
        val intent = bindServices[connection]
        val serviceName = intent?.getStringExtra("service_name")
        if (!serviceName.isNullOrEmpty()) {
            result = mPluginServices[serviceName]?.onUnbind(intent) ?: false
            mPluginServices[serviceName]?.onDestroy()
            mPluginServices.remove(serviceName)
        }
        if (mPluginServices.isEmpty()) context.unbindService(connection)
        return result
    }
}