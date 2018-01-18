package com.apkfuns.robustsample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadDex();
    }

    private void loadDex() {
        try {
            File dexFile = new File(getFilesDir(), "DexUtil_dex.jar");
            OutputStream os = new FileOutputStream(dexFile);
            InputStream is = getAssets().open("DexUtil_dex.jar");
            int read;
            byte[] buffer = new byte[1024];
            while ((read = is.read(buffer)) > 0) {
                os.write(buffer, 0, read);
            }
            os.close();
            is.close();
            Log.w(TAG, "copy Finish: " + (dexFile.exists()));
            DexClassLoader dexClassLoader = new DexClassLoader(dexFile.getPath(), dexFile.getParentFile().toString(),
                    null, getClassLoader());
            try {
                Class cls = dexClassLoader.loadClass("com.apkfuns.hotfixsample.DexUtil");
                Method toastMethod = cls.getMethod("showToast", Context.class, String.class);
                toastMethod.invoke(null, this, "show from DexUtil_dex");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
