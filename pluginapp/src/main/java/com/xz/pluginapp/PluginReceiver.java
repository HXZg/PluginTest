package com.xz.pluginapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author xian_zhong  admin
 * @version 1.0
 * @title com.xz.pluginapp  PluginTest
 * @Des PluginReceiver
 * @DATE 2020/11/3  15:46 星期二
 */
public class PluginReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("zzzzzzzzzzzz","plugin receiver action:::" + intent.getAction());
    }
}
