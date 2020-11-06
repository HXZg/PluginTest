package com.xz.pluginreplace.utils

import android.os.Handler
import com.xz.pluginreplace.ATHCallBack

/**
 * @title com.xz.pluginreplace.utils  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des HookATHCallback
 * @DATE 2020/11/3  14:42 星期二
 */
object HookATHCallback {


    fun hookActivityThread() {
        val activityThread =
            RefInvoke.getStaticFieldObject("android.app.ActivityThread", "sCurrentActivityThread")

        val mH = RefInvoke.getFieldObject(activityThread, "mH") as Handler

        RefInvoke.setFieldObject(Handler::class.java,mH,"mCallback",ATHCallBack(mH))
    }

}