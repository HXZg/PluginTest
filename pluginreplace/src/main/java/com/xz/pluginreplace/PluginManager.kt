package com.xz.pluginreplace

import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.res.AssetManager
import android.content.res.Resources
import android.content.res.Resources.Theme
import com.xz.pluginreplace.utils.RefInvoke
import dalvik.system.DexClassLoader

/**
 * @title com.xz.pluginreplace  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des PluginManager
 * @DATE 2020/11/2  9:27 星期一
 */
object PluginManager {

    lateinit var mResources: Resources
    lateinit var assets: AssetManager

    lateinit var mNowClassLoader : ClassLoader

    val pluginReceiverMappings = hashMapOf<String,String>()

    /**
     * 根据apk 路径构建class loader
     */
    fun installPlugin(context: Context,path: String) {  // 替换系统的classloader
        val mBaseClassLoader = context.classLoader
        val pluginClassLoader = PluginClassLoader(context.packageCodePath,context.classLoader)

        val dexOutputPath = context.getDir("dex", Context.MODE_PRIVATE).absolutePath
        val dexLoader = DexClassLoader(path,dexOutputPath,null,mBaseClassLoader)
        pluginClassLoader.addPluginClassLoader(dexLoader)

        val packageInfo = RefInvoke.getFieldObject(context, "mPackageInfo")
        RefInvoke.setFieldObject(packageInfo,"mClassLoader",pluginClassLoader)
        Thread.currentThread().contextClassLoader = pluginClassLoader

        mNowClassLoader = pluginClassLoader
    }

    fun loadResources(context: Context,path: String) {
//        RefInvoke.invokeInstanceMethod(assets,"addAssetPath",String::class.java,context.packageResourcePath)
        assets = RefInvoke.createObject(AssetManager::class.java) as AssetManager
        RefInvoke.invokeInstanceMethod(assets,"addAssetPath",String::class.java,path)
        mResources = Resources(assets,context.resources.displayMetrics,
            context.resources.configuration)

//        RefInvoke.setFieldObject(context,"mResources",mResources)
//        val packageInfo = RefInvoke.getFieldObject(context, "mPackageInfo")
//        RefInvoke.setFieldObject(packageInfo,"mResources",mResources)  // 存在资源冲突问题
    }

    fun startActivity(context: Context,className: String) {
        val i = Intent(context,StubActivity::class.java)
        i.putExtra(PluginConstant.REAL_ACTIVITY,className)
        context.startActivity(i)
    }

    fun startService(context: Context,className: String) {
        val i = Intent(context,StubService::class.java)
        context.startService(i)
    }

    fun bindService(context: Context,className: String,connection: ServiceConnection) {
        val i = Intent(context,StubService::class.java)
        context.bindService(i,connection,Context.BIND_AUTO_CREATE)
    }

    fun stopService(context: Context,className: String) {
        context.stopService(Intent(context,StubService::class.java))
    }

    fun getTheme(baseActivity: BaseActivity): Theme? {
        val localResources: Resources = mResources
        //中兴的rom中会将zeusBaseActivity.getBaseContext()的一个父类中添加mResources和mTheme，导致设置不全，这里全部设置

        RefInvoke.setFieldAllClass(
            baseActivity.baseContext,
            "mResources",
            localResources
        )
        RefInvoke.setFieldAllClass(baseActivity.baseContext, "mTheme", null)

        //AppCompatActivity包含了一个Resouces，这里设置为null让其再次生成一遍
        RefInvoke.setFieldObject(baseActivity,"mResources",null)
        //原始的theme指向的Resources是老的Resources，无法访问新插件，这里设置为null，
        // 系统会再次使用新的Resouces来生成一次theme，新的theme才能访问新的插件资源
        RefInvoke.setFieldObject(baseActivity, "mTheme", null)
        return baseActivity.getSuperTheme()
    }


}