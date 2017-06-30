package com.apkfuns.virtualapkdemo;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.didi.virtualapk.PluginManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String pluginPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                .concat("/pluginDemo-beijing-debug.apk");
        File plugin = new File(pluginPath);
        try {
            PluginManager.getInstance(this).loadPlugin(plugin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startPlugin(View view) {
        Intent intent = new Intent();
        intent.setClassName("com.didi.virtualapk.demo", "com.didi.virtualapk.demo.MainActivity");
        startActivity(intent);
    }
}
