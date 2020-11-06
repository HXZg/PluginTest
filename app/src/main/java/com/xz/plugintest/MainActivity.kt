package com.xz.plugintest

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.xz.pluginreplace.DexMergeHelper
import com.xz.pluginreplace.PluginManager
import com.xz.pluginreplace.plugin.IPluginBinder
import com.xz.pluginreplace.utils.AssetsUtils
import com.xz.pluginreplace.utils.HookATHCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)

    }

    val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            if (service is IPluginBinder) {
                Log.i("zzzzzzzzzzzzzzz","service add ${service.add(2,3)}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("zzzzzzzzzzzzz","main activity ${resources.getColor(R.color.colorAccent)}")

        tv_test.setOnClickListener {
            PluginManager.bindService(this,"com.xz.pluginapp.PluginService",connection)
        }

        btn_stop.setOnClickListener {
            unbindService(connection)
//            PluginManager.stopService(this,"com.xz.pluginapp.PluginService")
        }

        btn_start.setOnClickListener {
//            PluginManager.startActivity(this,"com.xz.pluginapp.MainActivity")

            contentResolver.delete(Uri.parse("content://host/test/delete/2"),"where",null)
        }
    }


}
