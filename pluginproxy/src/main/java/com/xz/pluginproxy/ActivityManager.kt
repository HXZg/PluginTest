package com.xz.pluginproxy

/**
 * @title com.xz.pluginproxy  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des ActivityManager
 * @DATE 2020/11/4  15:29 星期三
 *
 * 管理所有插件 的 activity
 */
object ActivityManager {

    private val atyList = arrayListOf<BaseActivity>() // 模拟回退栈

    fun addActivity(activity: BaseActivity) {
        atyList.add(activity)
    }

    fun removeActivity(activity: BaseActivity) {
        atyList.remove(activity)
    }

    fun launcher(activity: BaseActivity) {  // 模拟launch mode 的回退栈
        if (atyList.isEmpty()) return  // 回退栈中没有activity 则直接加入集合
        if (activity.launchMode == 0) return  // 标准模式
        if (activity.launchMode == 1) { // single top
            val last = atyList.last()
            if (last.javaClass.name == activity.javaClass.name) {
                removeActivity(last)
            }
        }
        for (i in atyList.size - 1 downTo 0) {
            if (atyList[i].javaClass.name == activity.javaClass.name) {
                if (activity.launchMode == 2) { // single task
                    for (j in i until atyList.size) {
                        atyList.removeAt(j)
                    }
                } else if (activity.launchMode == 3) { // single instance
                    atyList.removeAt(i)
                }
                break
            }
        }
    }
}