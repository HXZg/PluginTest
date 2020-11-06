package com.xz.pluginproxy

import android.content.Context
import java.io.Closeable
import java.io.FileOutputStream
import java.io.InputStream

/**
 * @title com.xz.pluginreplace.utils  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des AssetsUtils
 * @DATE 2020/11/2  16:52 星期一
 */

object AssetsUtils {

    fun extractAssets(context: Context,apkName: String) {
        var inputs: InputStream? = null
        var os : FileOutputStream? = null

        try {
            inputs = context.assets.open(apkName)
            val outPutFile = context.getFileStreamPath(apkName)
            os = FileOutputStream(outPutFile)
            val buffer = ByteArray(1024)
            var count = inputs.read(buffer)
            while (count > 0) {
                os.write(buffer,0,count)
                count = inputs.read(buffer)
            }
            os.flush()
        }catch (e:Exception) {
            e.printStackTrace()
        }finally {
            closeSilently(inputs)
            closeSilently(os)
        }

    }

    fun closeSilently(closeable: Closeable?) {
        if (closeable == null) return
        try {
            closeable.close()
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }
}