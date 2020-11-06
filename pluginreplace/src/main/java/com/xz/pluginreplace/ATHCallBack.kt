package com.xz.pluginreplace

import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Handler
import android.os.Message
import android.util.Log
import com.xz.pluginreplace.utils.RefInvoke

/**
 * @title com.xz.pluginreplace  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des ATHCallBack
 * @DATE 2020/11/3  14:44 星期二
 */
class ATHCallBack(private val mH: Handler) : Handler.Callback {

    override fun handleMessage(msg: Message): Boolean {
        if (msg.what == 114) {
            handleCreateService(msg)
        }
        mH.handleMessage(msg)
        return true
    }

    private fun handleCreateService(msg: Message) {
        val serviceInfo = RefInvoke.getFieldObject(msg.obj, "info") as ServiceInfo
        if (serviceInfo.name == StubService::class.java.name) {
            serviceInfo.name = "com.xz.pluginapp.PluginService"
        }
    }
}