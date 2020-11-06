package com.xz.pluginreplace

import android.app.Instrumentation
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.pm.ProviderInfo
import android.os.Bundle
import android.util.Log
import com.xz.pluginreplace.utils.RefInvoke
import dalvik.system.DexClassLoader
import dalvik.system.DexFile
import java.io.File


/**
 * @title com.xz.pluginreplace  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des DexMergeHelper
 * @DATE 2020/11/2  14:02 星期一
 *
 * 合并插件 dex
 */
object DexMergeHelper {

    fun mergeDex(cl: ClassLoader,apkFile: File,optDexFile: File) {
        val pathListObj =
            RefInvoke.getFieldObject(DexClassLoader::class.java.superclass, cl, "pathList")

        // 获取 PathList: Element[] dexElements
        val dexElements =
            RefInvoke.getFieldObject(pathListObj, "dexElements") as Array<Any>

        // Element 类型
        val elementClass = dexElements.javaClass.componentType

        // 创建一个数组, 用来替换原始的数组
        val newElements =
            java.lang.reflect.Array.newInstance(elementClass, dexElements.size + 1) as Array<Any>

        // 构造插件Element(File file, boolean isDirectory, File zip, DexFile dexFile) 这个构造函数
        val p1 = arrayOf(
            DexFile::class.java,
            File::class.java
        )
        val v1 = arrayOf(
            DexFile.loadDex(apkFile.canonicalPath, optDexFile.absolutePath, 0),
            apkFile
        )
        val o = RefInvoke.createObject(elementClass, p1, v1)

        val toAddElementArray = arrayOf(o)
        // 把原始的elements复制进去
        System.arraycopy(dexElements, 0, newElements, 0, dexElements.size)
        // 插件的那个element复制进去
        System.arraycopy(
            toAddElementArray,
            0,
            newElements,
            dexElements.size,
            toAddElementArray.size
        )

        // 替换
        RefInvoke.setFieldObject(pathListObj, "dexElements", newElements)

    }


    /**
     * 解析插件Apk文件中的 <receiver>, 并存储起来
     *
     * @param apkFile
     * @throws Exception
    </receiver> */
    fun preLoadReceiver(context: Context, apkFile: File) {
        // 首先调用parsePackage获取到apk对象对应的Package对象
        val packageParser = RefInvoke.createObject("android.content.pm.PackageParser")
        val p1 = arrayOf<Class<*>?>(
            File::class.java,
            Int::class.javaPrimitiveType
        )
        val v1 =
            arrayOf<Any>(apkFile, PackageManager.GET_RECEIVERS)
        val packageObj =
            RefInvoke.invokeInstanceMethod(packageParser, "parsePackage", p1, v1)
        val packageName =
            RefInvoke.getFieldObject(packageObj, "packageName") as String

        // 读取Package对象里面的receivers字段,注意这是一个 List<Activity> (没错,底层把<receiver>当作<activity>处理)
        // 接下来要做的就是根据这个List<Activity> 获取到Receiver对应的 ActivityInfo (依然是把receiver信息用activity处理了)
        val receivers =
            RefInvoke.getFieldObject(packageObj, "receivers") as List<*>
        try {
            for (receiver in receivers) {
                val metadata = RefInvoke.getFieldObject(
                    "android.content.pm.PackageParser\$Component", receiver, "metaData"
                ) as Bundle?
                val oldAction = metadata?.getString("oldAction")

                // 解析出 receiver以及对应的 intentFilter
                val filters = RefInvoke.getFieldObject(
                    "android.content.pm.PackageParser\$Component", receiver, "intents"
                ) as List<IntentFilter>

                // 把解析出来的每一个静态Receiver都注册为动态的
                for (intentFilter in filters) {
                    val receiverInfo =
                        RefInvoke.getFieldObject(receiver, "info") as ActivityInfo
                    // 无法通过Class.forName 加载 插件中的类  未合并dex
                    val broadcastReceiver =
                        RefInvoke.createObject(PluginManager.mNowClassLoader.loadClass(receiverInfo.name)) as BroadcastReceiver
                    context.registerReceiver(broadcastReceiver, intentFilter)
                    val newAction = intentFilter.getAction(0)
                    Log.i("zzzzzzzzzzzzzzzzzzzzz","$broadcastReceiver ,, $intentFilter  ,, $newAction ,, $oldAction")
                    if (!oldAction.isNullOrEmpty()) {  // 若插件未定义metadata 则只能做为动态注册广播
                        PluginManager.pluginReceiverMappings[oldAction] = newAction
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("zzzzzzzzzzzzzzzzz",e.message ?: "not exception")
        }
    }

    fun changeATInstrumentation() {
        val activityThread =
            RefInvoke.getStaticFieldObject("android.app.ActivityThread", "sCurrentActivityThread")

        val mInstrumentation = RefInvoke.getFieldObject(activityThread, "mInstrumentation") as Instrumentation
        val newInstrumentation = MyInstrumentation(mInstrumentation)
//        Log.e("zzzzzzzzzzzzzz","${activityThread.javaClass} ,,,, ${activityThread}")
//        RefInvoke.setSuperFieldObject(newInstrumentation.javaClass,newInstrumentation,"mThread",activityThread)
        RefInvoke.setFieldObject(activityThread,"mInstrumentation",newInstrumentation)
    }

    /**
     * 解析Apk文件中的 <provider>, 并存储起来
     * 主要是调用PackageParser类的generateProviderInfo方法
     *
     * @param apkFile 插件对应的apk文件
     * @throws Exception 解析出错或者反射调用出错, 均会抛出异常
    </provider> */
    @Throws(java.lang.Exception::class)
    fun parseProviders(apkFile: File): List<ProviderInfo> {

        //获取PackageParser对象实例
        val packageParserClass =
            Class.forName("android.content.pm.PackageParser")
        val packageParser = packageParserClass.newInstance()

        // 首先调用parsePackage获取到apk对象对应的Package对象
        val p1 = arrayOf<Class<*>?>(
            File::class.java,
            Int::class.javaPrimitiveType
        )
        val v1 =
            arrayOf<Any>(apkFile, PackageManager.GET_PROVIDERS)
        val packageObj =
            RefInvoke.invokeInstanceMethod(packageParser, "parsePackage", p1, v1)

        // 读取Package对象里面的services字段
        // 接下来要做的就是根据这个List<Provider> 获取到Provider对应的ProviderInfo
        val providers =
            RefInvoke.getFieldObject(packageObj, "providers") as List<*>

        // 调用generateProviderInfo 方法, 把PackageParser.Provider转换成ProviderInfo

        //准备generateProviderInfo方法所需要的参数
        val `packageParser$ProviderClass` =
            Class.forName("android.content.pm.PackageParser\$Provider")
        val packageUserStateClass =
            Class.forName("android.content.pm.PackageUserState")
        val defaultUserState = packageUserStateClass.newInstance()
        val userId =
            RefInvoke.invokeStaticMethod("android.os.UserHandle", "getCallingUserId") as Int
        val p2 = arrayOf(
            `packageParser$ProviderClass`,
            Int::class.javaPrimitiveType, packageUserStateClass,
            Int::class.javaPrimitiveType
        )
        val ret: MutableList<ProviderInfo> = ArrayList()
        // 解析出intent对应的Provider组件
        for (provider in providers) {
            val v2 =
                arrayOf(provider, 0, defaultUserState, userId)
            val info = RefInvoke.invokeInstanceMethod(
                packageParser,
                "generateProviderInfo",
                p2,
                v2
            ) as ProviderInfo
            ret.add(info)
        }
        return ret
    }

    /**
     * 在进程内部安装provider, 也就是调用 ActivityThread.installContentProviders方法
     *
     * @param context you know
     * @param apkFile
     * @throws Exception
     */
    @Throws(java.lang.Exception::class)
    fun installProviders(
        context: Context,
        apkFile: File
    ) {
        val providerInfos = parseProviders(apkFile)
        for (providerInfo in providerInfos) {
            providerInfo.applicationInfo.packageName = context.packageName
        }
        val currentActivityThread =
            RefInvoke.getStaticFieldObject("android.app.ActivityThread", "sCurrentActivityThread")
        val p1 = arrayOf(
            Context::class.java,
            MutableList::class.java
        )
        val v1 = arrayOf(context, providerInfos)
        Log.i("zzzzzzzzzzzzz","${v1.size} ,, ${providerInfos.size}")
        RefInvoke.invokeInstanceMethod(currentActivityThread, "installContentProviders", p1, v1)
    }
}