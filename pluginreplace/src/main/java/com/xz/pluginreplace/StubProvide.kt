package com.xz.pluginreplace

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log

/**
 * @title com.xz.pluginreplace  PluginTest
 * @author xian_zhong  admin
 * @version 1.0
 * @Des StubProvide
 * @DATE 2020/11/3  17:15 星期二
 */
class StubProvide : ContentProvider() {
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

    override fun onCreate(): Boolean {
        Log.i("zzzzzzzzzzzzzzzz","stub provider on create")
        return true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return context?.contentResolver?.update(getRealUri(uri),values,selection,selectionArgs) ?: 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return context?.contentResolver?.delete(getRealUri(uri),selection,selectionArgs) ?: 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    fun getRealUri(raw: Uri) : Uri {
        val authority = raw.authority

        Log.i("zzzzzzzzzzzzzz",authority ?: raw.toString())

        val newUri = raw.toString().replace("$authority/", "")

        return Uri.parse(newUri)
    }
}