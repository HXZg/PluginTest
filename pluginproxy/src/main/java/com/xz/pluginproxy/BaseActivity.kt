package com.xz.pluginproxy

import android.content.Intent
import android.os.Bundle
import android.view.View

/**
 * @title com.xz.pluginproxy  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des BaseActivity
 * @DATE 2020/11/4  14:23 星期三
 *
 * 插件的activity 当成一个普通类处理 无需继承activity
 * 若想插件开发与普通app开发一样 可以考虑在编译时期通过字节码修改插件继承的Activity的名字
 * 参考Shadow
 */
open class BaseActivity : IRemoteActivity {

    protected lateinit var that: ProxyActivity
    private lateinit var pluginName: String  // 对应插件 名称
    var launchMode : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        that.superOnCreate(savedInstanceState)  // 执行父类onCreate 方法的时间 应该由具体的插件activity决定
    }

    override fun onStart() {
        that.superOnStart()
    }

    override fun onResume() {
        that.superOnResume()
    }

    override fun onPause() {
        that.superOnPause()
    }

    override fun onStop() {
        that.superOnStop()
    }

    override fun onDestroy() {
        that.superOnDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        that.superOnActivityResult(requestCode, resultCode, data)
    }

    override fun setProxy(proxy: ProxyActivity, pluginName: String) {
        this.that = proxy
        this.pluginName = pluginName
    }

    override fun setContentView(layoutResID: Int) {
        that.setContentView(layoutResID)
    }

    override fun <T : View?> findViewById(id: Int): T {
        return that.findViewById<T>(id)
    }

    override fun setResult(requestCode: Int) {
        that.setResult(requestCode)
    }

    fun finish() {
        that.finish()
    }
}