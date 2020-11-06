package com.xz.pluginproxy

import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

/**
 * @title com.xz.pluginproxy  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des IRemoteProvider
 * @DATE 2020/11/4  15:18 星期三
 */
interface IRemoteProvider {

    fun insert(uri: Uri, values: ContentValues?): Uri?

    fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor?

    fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int

    fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int
}