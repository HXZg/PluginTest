package com.xz.proxyapp

import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.xz.pluginproxy.IRemoteProvider

/**
 * @title com.xz.proxyapp  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des TestProvider
 * @DATE 2020/11/4  17:56 星期三
 */
class TestProvider : IRemoteProvider {
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 2
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 3
    }


}