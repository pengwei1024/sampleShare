package com.apkfuns.socheckerdemo;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context singleton;

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    public static Context getContext() {
        return singleton;
    }
}
