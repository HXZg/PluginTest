package com.xz.pluginapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xz.pluginreplace.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = findViewById(R.id.tv_test_plugn);
//        tv.setText("plugin app");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setTextColor(getResources().getColor(R.color.black_color));
            }
        });
        Log.i("zzzzzzzzzzzzzzzz","plugin main activity" + getString(R.string.app_name) + getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("zzzzzzzzzzzzzzzz","plugin resume activity");
    }
}
