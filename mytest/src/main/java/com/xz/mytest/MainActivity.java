package com.xz.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ContentResolver resolver;

    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent i = new Intent("test_one");
        i.setComponent(new ComponentName("com.xz.plugintest","com.xz.pluginreplace.StubReceiver"));
        sendBroadcast(i);*/


        uri = Uri.parse("content://host/test/delete/2");
        resolver = getContentResolver();


       findViewById(R.id.tv_hello).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               resolver.delete(uri,"where",null);
           }
       });
    }
}
