package com.xz.pluginapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author xian_zhong  admin
 * @version 1.0
 * @title com.xz.pluginapp  PluginTest
 * @Des PluginProvider
 * @DATE 2020/11/3  16:54 星期二
 */
public class PluginProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        Log.i("zzzzzzzzzzzzzz","plugin provide on create");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.i("zzzzzzzzzzzzzzzzz","plugin provide delete : " + uri.toString());
        return 2;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 2;
    }
}
