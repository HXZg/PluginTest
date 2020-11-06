package com.xz.pluginreplace

import android.app.Activity
import android.app.Instrumentation
import android.content.ComponentName
import android.content.Intent

/**
 * @title com.xz.pluginreplace  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des MyInstrumentation
 * @DATE 2020/11/2  9:22 星期一
 */
class MyInstrumentation(private val mInstrumentation: Instrumentation) : Instrumentation() {

    override fun newActivity(cl: ClassLoader?, className: String?, intent: Intent?): Activity {
        if (intent != null && intent.component?.className == StubActivity::class.java.name) {
            val cn = intent.getStringExtra(PluginConstant.REAL_ACTIVITY)
            if (cn != null) {
                return mInstrumentation.newActivity(cl, cn, intent)
            }
        }
        return mInstrumentation.newActivity(cl, className, intent)
    }

}