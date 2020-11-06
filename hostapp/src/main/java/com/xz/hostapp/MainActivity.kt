package com.xz.hostapp

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.xz.pluginproxy.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i("zzzzzzzzzzzzzz","$service")
            (service as IPluginBinder).add(3,4)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start_activity.setOnClickListener {
            PluginManager.startActivity(this,"com.xz.proxyapp","com.xz.proxyapp.TestPluginActivity")
        }

        btn_start_service.setOnClickListener {
            val i = Intent(this,ProxyService::class.java)
            i.putExtra(PluginConstant.PLUGIN_NAME,"com.xz.proxyapp")
            i.putExtra("service_name","com.xz.proxyapp.TestBindService")
//            startService(i)

            ServiceManager.bindService(this,i,connection)
        }

        btn_stop_service.setOnClickListener {
//            ServiceManager.stopService(this,"com.xz.proxyapp.TestPluginService")

            ServiceManager.unBindService(this,connection)
        }

        btn_send_receiver.setOnClickListener {
            /*PluginManager.sendReceiver(this,"com.xz.proxyapp","com.xz.proxyapp.TestPluginReceiver",
                Intent("proxy")
            )*/

            ServiceManager.stopService(this,"com.xz.proxyapp.TestPluginService")
        }

        btn_content_provider.setOnClickListener {
//            val i = contentResolver.delete(Uri.parse("content://host/com.xz.proxyapp/TestProvider/delete/2"),"where",null)
//            Log.i("zzzzzzzzzzzzz","content provider $i")

            val i = Intent(this,ProxyService::class.java)
            i.putExtra(PluginConstant.PLUGIN_NAME,"com.xz.proxyapp")
            i.putExtra("service_name","com.xz.proxyapp.TestPluginService")

            startService(i)
            /*ServiceManager.bindService(this,i, object : ServiceConnection {
                override fun onServiceDisconnected(name: ComponentName?) {

                }

                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    Log.i("zzzzzzzzzzzzzzz","on connection  $service")
                }
            })*/
        }
    }
}
