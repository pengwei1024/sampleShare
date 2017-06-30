package com.baidu.hi.logic;

import android.util.Log;

/**
 * Created by pengwei on 2017/6/30.
 */

public class LoginLogic {
    private static LoginLogic singleton;

    public static LoginLogic getInstance() {
        if (singleton == null) {
            synchronized (LoginLogic.class) {
                if (singleton == null) {
                    singleton = new LoginLogic();
                }
            }
        }
        return singleton;
    }

    private LoginLogic() {

    }

    public void show() {
        Log.e(getClass().getName(), "#######");
    }
}
