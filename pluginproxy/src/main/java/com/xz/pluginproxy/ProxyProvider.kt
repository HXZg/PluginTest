package com.xz.pluginproxy

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

/**
 * @title com.xz.pluginproxy  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des ProxyProvider
 * @DATE 2020/11/4  15:15 星期三
 *
 * 占坑 provider  与插件provider 是 一对多的关系
 */
class ProxyProvider : ContentProvider() {
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return getPluginUri(uri)?.insert(uri, values)
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return getPluginUri(uri)?.query(uri, projection, selection, selectionArgs, sortOrder)
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return getPluginUri(uri)?.update(uri, values, selection, selectionArgs) ?: 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return getPluginUri(uri)?.delete(uri, selection, selectionArgs) ?: 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    private fun getPluginUri(uri: Uri) : IRemoteProvider? = ProviderManager.getPluginProvider(uri)
}