package com.apkfuns.virtualapkdemo;

import android.util.Log;

/**
 * Created by pengwei on 2017/6/30.
 */

public class TestLogic {
    private static TestLogic singleton;

    public static TestLogic getInstance() {
        if (singleton == null) {
            synchronized (TestLogic.class) {
                if (singleton == null) {
                    singleton = new TestLogic();
                }
            }
        }
        return singleton;
    }

    public void log(String text) {
        Log.e("TestLogic", "log!!!!!!!!!!!!" + text);
    }
}
