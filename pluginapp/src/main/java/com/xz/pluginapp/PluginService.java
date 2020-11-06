package com.xz.pluginapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.xz.pluginreplace.plugin.IPluginBinder;

/**
 * @author xian_zhong  admin
 * @version 1.0
 * @title com.xz.pluginapp  PluginTest
 * @Des PluginService
 * @DATE 2020/11/3  14:56 星期二
 */
public class PluginService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PluginBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("zzzzzzzzzzz","plugin service on create");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("zzzzzzzzzz","plugin service on destroy");
    }

    class PluginBinder extends Binder implements IPluginBinder {
        @Override
        public int add(int a, int b) {
            return a + b;
        }
    }
}

