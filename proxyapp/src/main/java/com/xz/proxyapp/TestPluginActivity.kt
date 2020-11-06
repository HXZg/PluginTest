package com.xz.proxyapp

import android.os.Bundle
import android.util.Log
import com.xz.pluginproxy.BaseActivity

/**
 * @title com.xz.proxyapp  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des TestPluginActivity
 * @DATE 2020/11/4  17:51 星期三
 */
class TestPluginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("zzzzzzzzzzzzzzzzzzzz","plugin on Create")
    }

    override fun onResume() {
        super.onResume()
        Log.i("zzzzzzzzzzzzzz","plugin on resume")
    }
}